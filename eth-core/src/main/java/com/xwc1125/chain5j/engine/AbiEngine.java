package com.xwc1125.chain5j.engine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xwc1125.chain5j.abi.FunctionInputDecoder;
import com.xwc1125.chain5j.abi.TypeDecoder;
import com.xwc1125.chain5j.abi.TypeReference;
import com.xwc1125.chain5j.abi.Utils;
import com.xwc1125.chain5j.abi.datatypes.*;
import com.xwc1125.chain5j.abi.datatypes.generated.AbiTypes;
import com.xwc1125.chain5j.crypto.Hash;
import com.xwc1125.chain5j.engine.abi.MethodInfo;
import com.xwc1125.chain5j.protocol.ObjectMapperFactory;
import com.xwc1125.chain5j.protocol.core.methods.response.AbiDefinition;
import com.xwc1125.chain5j.utils.Numeric;
import com.xwc1125.chain5j.utils.StringUtils;
import com.xwc1125.chain5j.utils.Strings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: xwc1125
 * @Date: 2019/11/4 20:23
 * @Copyright Copyright@2019
 */
public class AbiEngine {

    /**
     * Description: 解析abi得到methodInfo
     * </p>
     *
     * @param abiList
     * @return java.util.List<com.xwc1125.chain5j.engine.abi.MethodInfo>
     * @Author: xwc1125
     * @Date: 2019-11-05 10:11:09
     */
    public static List<MethodInfo> getMethodInfos(List<AbiDefinition> abiList) {
        List<MethodInfo> methodInfoList = new ArrayList<>();
        for (AbiDefinition abi1 : abiList) {
            // 方法或参数名
            String name = abi1.getName();
            // function，event，constructor，fallback
            String type1 = abi1.getType();
            // view,nonpayable,payable,null
            String stateMutability = abi1.getStateMutability();
            if (type1.equals("function")) {
                if (StringUtils.isNotEmpty(stateMutability)) {
                    List<AbiDefinition.NamedType> inputs = abi1.getInputs();
                    List<AbiDefinition.NamedType> outputs = abi1.getOutputs();
                    MethodInfo methodInfo = buildMethodInfo(name, inputs, outputs);
                    methodInfo.setStateMutability(stateMutability);
                    methodInfoList.add(methodInfo);
                }
            }
        }
        return methodInfoList;
    }

    /**
     * Description: 解析Input
     * </p>
     *
     * @param input
     * @param abiList
     * @return com.xwc1125.chain5j.abi.FunctionInputDecoder.InputData
     * @Author: xwc1125
     * @Date: 2019-11-05 10:18:52
     */
    @Deprecated
    public static FunctionInputDecoder.InputData parseInput2(String input, List<AbiDefinition> abiList) throws Exception {
        FunctionInputDecoder.InputData inputData = null;
        String method = input.substring(0, 10);
        for (AbiDefinition abi1 : abiList) {
            // 方法或参数名
            String name = abi1.getName();
            // function，event，constructor，fallback
            String type1 = abi1.getType();
            // view,nonpayable,payable,null
            String stateMutability = abi1.getStateMutability();
            if (type1.equals("function")) {
                if (StringUtils.isNotEmpty(stateMutability)) {
                    List<AbiDefinition.NamedType> inputs = abi1.getInputs();
                    List<AbiDefinition.NamedType> outputs = abi1.getOutputs();
                    MethodInfo methodInfo = buildMethodInfo(name, inputs, outputs);
                    methodInfo.setStateMutability(stateMutability);
                    if (methodInfo.getMethodId().equals(method)) {
                        // 开始可以解析
                        System.out.println("开始可以解析");
                        inputData = FunctionInputDecoder.decode(input, methodInfo.getName(), methodInfo.getInputParameters());
                        break;
                    }
                }
            }
        }
        return inputData;
    }

    /**
     * Description: 获取方法签名拼接串，如transfer(address,uint256)
     * </p>
     *
     * @param methodName
     * @param parameters
     * @return java.lang.String
     * @Author: xwc1125
     * @Date: 2019-11-05 10:19:38
     */
    public static String buildMethodSignature(String methodName, List<AbiDefinition.NamedType> parameters) {
        StringBuilder result = new StringBuilder();
        result.append(methodName);
        result.append("(");
        String params = parameters.stream()
                .map(AbiDefinition.NamedType::getType)
                .collect(Collectors.joining(","));
        result.append(params);
        result.append(")");
        return result.toString();
    }

    /**
     * Description: 根据方法名获取MethodInfo
     * </p>
     *
     * @param methodName
     * @param inputs
     * @param outputs
     * @return com.xwc1125.chain5j.engine.abi.MethodInfo
     * @Author: xwc1125
     * @Date: 2019-11-05 10:22:04
     */
    public static MethodInfo buildMethodInfo(String methodName, List<AbiDefinition.NamedType> inputs, List<AbiDefinition.NamedType> outputs) {
        MethodInfo methodInfo = new MethodInfo();
        methodInfo.setName(methodName);
        StringBuilder result = new StringBuilder();
        result.append(methodName);
        result.append("(");
        try {
            for (AbiDefinition.NamedType entity : inputs) {
                String type = entity.getType();
                result.append(type).append(",");
                TypeReference<?> typeReference1 = AbiTypes.getTypeReference(type);
                methodInfo.getInputParameters().add(typeReference1);
            }
            for (AbiDefinition.NamedType entity : outputs) {
                String type = entity.getType();
                TypeReference<?> typeReference1 = AbiTypes.getTypeReference(type);
                methodInfo.getOutputParameters().add(typeReference1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String substring = result.toString().substring(0, result.length() - 1);
        substring = substring + ")";
        methodInfo.setMethodSignature(substring);
        String methodId = buildMethodId(substring);
        methodInfo.setMethodId(methodId);
        return methodInfo;
    }

    /**
     * Description: 根据方法拼接串hash得到方法id
     * </p>
     *
     * @param methodSignature 如transfer(address,uint256)
     * @return java.lang.String
     * @Author: xwc1125
     * @Date: 2019-11-05 10:20:30
     */
    public static String buildMethodId(String methodSignature) {
        byte[] input = methodSignature.getBytes();
        byte[] hash = Hash.sha3(input);
        return Numeric.toHexString(hash).substring(0, 10);
    }

    // =================解析2========
    private static final String regex = "(\\w+)(?:\\[(.*?)\\])(?:\\[(.*?)\\])?";
    private static final Pattern pattern = Pattern.compile(regex);
    public static final String UNKNOWN = "unknown";

    /**
     * 组合类
     */
    static class TypeInfo {
        private Class<? extends Type> baseType;// 基础类
        private Class<? extends Type> rawType;// 外层类

        public TypeInfo() {
        }

        public Class<? extends Type> getBaseType() {
            return baseType;
        }

        public void setBaseType(Class<? extends Type> baseType) {
            this.baseType = baseType;
        }

        public Class<? extends Type> getRawType() {
            return rawType;
        }

        public void setRawType(Class<? extends Type> rawType) {
            this.rawType = rawType;
        }
    }

    /**
     * Description: 加载ABI
     * </p>
     *
     * @param abi
     * @return java.util.List<com.xwc1125.chain5j.protocol.core.methods.response.AbiDefinition>
     * @Author: xwc1125
     * @Date: 2019-11-05 16:01:45
     */
    public static List<AbiDefinition> loadContractDefinition(String abi) throws IOException {
        ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
        AbiDefinition[] abiDefinition = objectMapper.readValue(abi, AbiDefinition[].class);
        return Arrays.asList(abiDefinition);
    }

    /**
     * Description: 获取静态的 TypeReference
     * </p>
     *
     * @param type
     * @return java.lang.Class<?>
     * @Author: xwc1125
     * @Date: 2019-11-05 15:29:51
     */
    private static Class<? extends Type> getStaticArrayTypeReferenceClass(String type) {
        try {
            return (Class<? extends Type>) Class.forName("com.xwc1125.chain5j.abi.datatypes.generated.StaticArray" + type);
        } catch (ClassNotFoundException e) {
            // Unfortunately we can't encode it's length as a type if it's > 32.
            return StaticArray.class;
        }
    }

    /**
     * Description: 过滤掉 storage 和memory
     * </p>
     *
     * @param type
     * @return java.lang.String
     * @Author: xwc1125
     * @Date: 2019-11-05 15:26:18
     */
    private static String trimStorageDeclaration(String type) {
        if (type.endsWith(" storage") || type.endsWith(" memory")) {
            return type.split(" ")[0];
        } else {
            return type;
        }
    }

    /**
     * Description: 解析参数类型
     * </p>
     *
     * @param typeDeclaration
     * @return com.squareup.javapoet.TypeName
     * @Author: xwc1125
     * @Date: 2019-11-05 15:21:31
     */
    static TypeInfo buildTypeInfo(String typeDeclaration) {
        String type = trimStorageDeclaration(typeDeclaration);
        Matcher matcher = pattern.matcher(type);
        TypeInfo typeInfo = new TypeInfo();
        if (matcher.find()) {
            Class<? extends Type> baseType = AbiTypes.getType(matcher.group(1));
            typeInfo.baseType = baseType;
            String firstArrayDimension = matcher.group(2);
            String secondArrayDimension = matcher.group(3);

            if ("".equals(firstArrayDimension)) {
                typeInfo.rawType = DynamicArray.class;
            } else {
                // 映射获取
                Class<? extends Type> rawType = getStaticArrayTypeReferenceClass(firstArrayDimension);
                typeInfo.rawType = rawType;
            }

            if (secondArrayDimension != null) {
                if ("".equals(secondArrayDimension)) {
                    typeInfo.setRawType(DynamicArray.class);
                } else {
                    Class<? extends Type> rawType = getStaticArrayTypeReferenceClass(secondArrayDimension);
                    typeInfo.setRawType(rawType);
                }
            }

            return typeInfo;
        } else {
            Class<? extends Type> cls = AbiTypes.getType(type);
            typeInfo.setBaseType(cls);
            return typeInfo;
        }
    }

    public static FunctionInputDecoder.InputData parseInput(String rawInput, List<AbiDefinition> functionDefinitions) throws Exception {
        String methodSign = rawInput.substring(0, 10);

        FunctionInputDecoder.InputData inputData = null;
        for (AbiDefinition functionDefinition : functionDefinitions) {
            if (functionDefinition.getType().equals("function")) {
                String funcName = functionDefinition.getName();
                String type1 = functionDefinition.getType();

                StringBuilder methodResult = new StringBuilder();
                methodResult.append(funcName);
                methodResult.append("(");

                for (AbiDefinition.NamedType namedType : functionDefinition.getInputs()) {
                    String type = namedType.getType();
                    methodResult.append(type).append(",");
                }

                String substring = methodResult.toString().substring(0, methodResult.length() - 1);
                substring = substring + ")";
                String methodId = AbiEngine.buildMethodId(substring);
                if (methodSign.equals(methodId)) {
                    List<TypeInfo> typeInfos = buildTypeInfos(functionDefinition.getInputs());
                    // 开始解析
                    System.out.println("开始可以解析");
                    inputData = decode(rawInput, funcName, typeInfos);
                    break;
                }
            }
        }
        return inputData;
    }

    public static FunctionInputDecoder.InputData decode(
            String rawInput, String method, List<TypeInfo> inputParameters) throws Exception {
        String methodSign = rawInput.substring(0, 10);
        String input = rawInput.substring(10);

        if (Strings.isEmpty(input)) {
            return new FunctionInputDecoder.InputData("", Collections.emptyList());
        } else {
            return build(methodSign, input, method, inputParameters);
        }
    }

    private static FunctionInputDecoder.InputData build(String methodSign, String input, String methodName, List<TypeInfo> typeInfos) throws Exception {
        List<Type> results = new ArrayList<>(typeInfos.size());
        StringBuilder resultMethod = new StringBuilder();
        if (StringUtils.isNotEmpty(methodName)) {
            resultMethod.append(methodName);
        } else {
            resultMethod.append(UNKNOWN);
        }
        resultMethod.append("(");
        int offset = 0;
        for (TypeInfo typeInfo : typeInfos) {
            try {
                Class<? extends Type> rawType = typeInfo.rawType;
                Class<? extends Type> baseType = typeInfo.baseType;
                TypeReference typeReference = TypeReference.create(baseType);
                if (rawType != null) {
                    typeReference = TypeReference.create(rawType);
                }

                Class<Type> type = (Class<Type>) typeReference.getClassType();
                int hexStringDataOffset = getDataOffset(input, offset, type);
                Type result;
                if (DynamicArray.class.isAssignableFrom(type)) {
                    result = decodeDynamicArray(
                            input, hexStringDataOffset, typeReference, baseType);
                    offset += MAX_BYTE_LENGTH_FOR_HEX_STRING;
                    resultMethod.append(result.getTypeAsString()).append(",");

                } else if (typeReference instanceof TypeReference.StaticArrayTypeReference) {
                    int length = ((TypeReference.StaticArrayTypeReference) typeReference).getSize();
                    result = decodeStaticArray(
                            input, hexStringDataOffset, typeReference, baseType, length);
                    offset += length * MAX_BYTE_LENGTH_FOR_HEX_STRING;
                    resultMethod.append(result.getTypeAsString()).append(",");

                } else if (StaticArray.class.isAssignableFrom(type)) {
                    int length = Integer.parseInt(type.getSimpleName()
                            .substring(StaticArray.class.getSimpleName().length()));
                    result = decodeStaticArray(
                            input, hexStringDataOffset, typeReference, baseType, length);
                    offset += length * MAX_BYTE_LENGTH_FOR_HEX_STRING;
                    resultMethod.append(result.getTypeAsString()).append(",");

                } else {
                    result = TypeDecoder.decode(input, hexStringDataOffset, type);
                    offset += MAX_BYTE_LENGTH_FOR_HEX_STRING;
                    resultMethod.append(result.getTypeAsString()).append(",");
                }
                results.add(result);
            } catch (Exception e) {
                throw e;
            }
        }
        resultMethod.deleteCharAt(resultMethod.length() - 1);
        resultMethod.append(")");
        String methodSignature = resultMethod.toString();

        if (!methodSignature.startsWith(UNKNOWN)) {
            String methodId = AbiEngine.buildMethodId(methodSignature);
            if (!methodSign.startsWith(methodId)) {
                throw new Exception("methodSignature is not the same");
            }
        }
        return new FunctionInputDecoder.InputData(methodSignature, results);
    }

    public static <T extends Type> T decodeStaticArray(
            String input, int offset, TypeReference<T> typeReference, Class<? extends Type> baseType, int length) {

        BiFunction<List<T>, String, T> function = (elements, typeName) -> {
            if (elements.isEmpty()) {
                throw new UnsupportedOperationException("Zero length fixed array is invalid type");
            } else {
                return instantiateStaticArray(typeReference, elements, length);
            }
        };

        return decodeArrayElements(input, offset, typeReference, baseType, length, function);
    }

    private static <T extends Type> T instantiateStaticArray(
            TypeReference<T> typeReference, List<T> elements, int length) {
        try {
            Class<? extends StaticArray> arrayClass =
                    (Class<? extends StaticArray>) Class.forName(
                            "com.xwc1125.chain5j.abi.datatypes.generated.StaticArray" + length);

            return (T) arrayClass.getConstructor(List.class).newInstance(elements);
        } catch (ReflectiveOperationException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public static <T extends Type> T decodeDynamicArray(
            String input, int offset, TypeReference<T> typeReference, Class<? extends Type> baseType) {

        int length = TypeDecoder.decodeUintAsInt(input, offset);

        BiFunction<List<T>, String, T> function = (elements, typeName) -> {
            if (elements.isEmpty()) {
                return (T) DynamicArray.empty(typeName);
            } else {
                return (T) new DynamicArray<>(elements);
            }
        };

        int valueOffset = offset + MAX_BYTE_LENGTH_FOR_HEX_STRING;

        return decodeArrayElements(input, valueOffset, typeReference, baseType, length, function);
    }

    private static <T extends Type> T decodeArrayElements(
            String input, int offset, TypeReference<T> typeReference, Class<? extends Type> baseType, int length,
            BiFunction<List<T>, String, T> consumer) {

        try {
            Class<T> cls = (Class<T>) baseType;
            if (Array.class.isAssignableFrom(cls)) {
                throw new UnsupportedOperationException(
                        "Arrays of arrays are not currently supported for external functions, see"
                                + "http://solidity.readthedocs.io/en/develop/types.html#members");
            } else {
                List<T> elements = new ArrayList<>(length);

                for (int i = 0, currOffset = offset;
                     i < length;
                     i++, currOffset += TypeDecoder.getSingleElementLength(input, currOffset, cls)
                             * MAX_BYTE_LENGTH_FOR_HEX_STRING) {
                    T value = TypeDecoder.decode(input, currOffset, cls);
                    elements.add(value);
                }

                String typeName = Utils.getSimpleTypeName(cls);

                return consumer.apply(elements, typeName);
            }
        } catch (Exception e) {
            throw new UnsupportedOperationException(
                    "Unable to access parameterized type " + typeReference.getType().getTypeName(),
                    e);
        }
    }

    static final int MAX_BYTE_LENGTH_FOR_HEX_STRING = Type.MAX_BYTE_LENGTH << 1;

    private static <T extends Type> int getDataOffset(String input, int offset, Class<T> type) {
        if (DynamicBytes.class.isAssignableFrom(type)
                || Utf8String.class.isAssignableFrom(type)
                || DynamicArray.class.isAssignableFrom(type)) {
            return TypeDecoder.decodeUintAsInt(input, offset) << 1;
        } else {
            return offset;
        }
    }

    static List<TypeInfo> buildTypeInfos(List<AbiDefinition.NamedType> namedTypes) {
        List<TypeInfo> result = new ArrayList<>(namedTypes.size());
        for (AbiDefinition.NamedType namedType : namedTypes) {
            result.add(buildTypeInfo(namedType.getType()));
        }
        return result;
    }
}
