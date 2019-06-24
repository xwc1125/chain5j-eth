package com.xwc1125.chain5j.abi;

import com.xwc1125.chain5j.abi.datatypes.*;
import com.xwc1125.chain5j.abi.datatypes.generated.Bytes32;
import com.xwc1125.chain5j.crypto.Hash;
import com.xwc1125.chain5j.utils.Numeric;
import com.xwc1125.chain5j.utils.StringUtils;
import com.xwc1125.chain5j.utils.Strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Decodes values returned by function or event calls.
 */
public class FunctionInputDecoder {

    private static final Logger log = LoggerFactory.getLogger(FunctionInputDecoder.class);
    public static final String UNKNOWN = "unknown";

    private FunctionInputDecoder() {
    }

    /**
     * Decode ABI encoded return values from smart contract function call.
     *
     * @param rawInput        ABI encoded input
     * @param inputParameters list of return types as {@link TypeReference}
     * @return {@link List} of values returned by function, {@link Collections#emptyList()} if
     * invalid response
     */
    public static InputData decode(
            String rawInput, String method, List<TypeReference<?>> inputParameters) {
        String methodSign = rawInput.substring(0, 10);
        String input = rawInput.substring(10);

        if (Strings.isEmpty(input)) {
            return new InputData("", Collections.emptyList());
        } else {
            return build(methodSign, input, method, inputParameters);
        }
    }

    /**
     * <p>Decodes an indexed parameter associated with an event. Indexed parameters are individually
     * encoded, unlike non-indexed parameters which are encoded as per ABI-encoded function
     * parameters and return values.</p>
     *
     * <p>If any of the following types are indexed, the Keccak-256 hashes of the values are
     * returned instead. These are returned as a bytes32 value.</p>
     *
     * <ul>
     * <li>Arrays</li>
     * <li>Strings</li>
     * <li>Bytes</li>
     * </ul>
     *
     * <p>See the
     * <a href="http://solidity.readthedocs.io/en/latest/contracts.html#events">
     * Solidity documentation</a> for further information.</p>
     *
     * @param rawInput      ABI encoded input
     * @param typeReference of expected result type
     * @param <T>           type of TypeReference
     * @return the decode value
     */
    @SuppressWarnings("unchecked")
    public static <T extends Type> Type decodeIndexedValue(
            String rawInput, TypeReference<T> typeReference) {
        String input = Numeric.cleanHexPrefix(rawInput);

        try {
            Class<T> type = typeReference.getClassType();

            if (Bytes.class.isAssignableFrom(type)) {
                return TypeDecoder.decodeBytes(input, (Class<Bytes>) Class.forName(type.getName()));
            } else if (Array.class.isAssignableFrom(type)
                    || BytesType.class.isAssignableFrom(type)
                    || Utf8String.class.isAssignableFrom(type)) {
                return TypeDecoder.decodeBytes(input, Bytes32.class);
            } else {
                return TypeDecoder.decode(input, type);
            }
        } catch (ClassNotFoundException e) {
            throw new UnsupportedOperationException("Invalid class reference provided", e);
        }
    }

    private static InputData build(
            String methodSign, String input, String methodName, List<TypeReference<?>> inputParameters) {
        List<Type> results = new ArrayList<>(inputParameters.size());
        StringBuilder resultMethod = new StringBuilder();
        if (StringUtils.isNotEmpty(methodName)) {
            resultMethod.append(methodName);
        } else {
            resultMethod.append(UNKNOWN);
        }
        resultMethod.append("(");
        int offset = 0;
        for (TypeReference<?> typeReference : inputParameters) {
            try {
                @SuppressWarnings("unchecked")
                Class<Type> type = (Class<Type>) typeReference.getClassType();

                int hexStringDataOffset = getDataOffset(input, offset, type);

                Type result;
                if (DynamicArray.class.isAssignableFrom(type)) {
                    result = TypeDecoder.decodeDynamicArray(
                            input, hexStringDataOffset, typeReference);
                    offset += TypeDecoder.MAX_BYTE_LENGTH_FOR_HEX_STRING;
                    resultMethod.append(result.getTypeAsString()).append(",");

                } else if (typeReference instanceof TypeReference.StaticArrayTypeReference) {
                    int length = ((TypeReference.StaticArrayTypeReference) typeReference).getSize();
                    result = TypeDecoder.decodeStaticArray(
                            input, hexStringDataOffset, typeReference, length);
                    offset += length * TypeDecoder.MAX_BYTE_LENGTH_FOR_HEX_STRING;
                    resultMethod.append(result.getTypeAsString()).append(",");

                } else if (StaticArray.class.isAssignableFrom(type)) {
                    int length = Integer.parseInt(type.getSimpleName()
                            .substring(StaticArray.class.getSimpleName().length()));
                    result = TypeDecoder.decodeStaticArray(
                            input, hexStringDataOffset, typeReference, length);
                    offset += length * TypeDecoder.MAX_BYTE_LENGTH_FOR_HEX_STRING;
                    resultMethod.append(result.getTypeAsString()).append(",");

                } else {
                    result = TypeDecoder.decode(input, hexStringDataOffset, type);
                    offset += TypeDecoder.MAX_BYTE_LENGTH_FOR_HEX_STRING;
                    resultMethod.append(result.getTypeAsString()).append(",");
                }
                results.add(result);
            } catch (ClassNotFoundException e) {
                throw new UnsupportedOperationException("Invalid class reference provided", e);
            }
        }

        resultMethod.deleteCharAt(resultMethod.length() - 1);
        resultMethod.append(")");
        String methodSignature = resultMethod.toString();
        if (!methodSignature.startsWith(UNKNOWN)) {
            String methodId = buildMethodId(methodSignature);
            if (!methodSign.startsWith(methodId)) {
                log.error("methodSignature is not the same");
                methodSignature = "error:" + methodSignature;
            }
        }
        return new InputData(methodSignature, results);
    }

    public static String buildMethodId(String methodSignature) {
        byte[] input = methodSignature.getBytes();
        byte[] hash = Hash.sha3(input);
        return Numeric.toHexString(hash).substring(0, 10);
    }

    private static <T extends Type> int getDataOffset(String input, int offset, Class<T> type) {
        if (DynamicBytes.class.isAssignableFrom(type)
                || Utf8String.class.isAssignableFrom(type)
                || DynamicArray.class.isAssignableFrom(type)) {
            return TypeDecoder.decodeUintAsInt(input, offset) << 1;
        } else {
            return offset;
        }
    }

    public static class InputData {
        String method;
        List<Type> inputParameters;

        public InputData(String method, List<Type> inputParameters) {
            this.method = method;
            this.inputParameters = inputParameters;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public List<Type> getInputParameters() {
            return inputParameters;
        }

        public void setInputParameters(List<Type> inputParameters) {
            this.inputParameters = inputParameters;
        }
    }
}
