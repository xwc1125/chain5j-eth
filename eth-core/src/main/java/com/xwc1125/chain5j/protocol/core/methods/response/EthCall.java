package com.xwc1125.chain5j.protocol.core.methods.response;

import java.util.Collections;
import java.util.List;

import com.xwc1125.chain5j.protocol.core.Response;
import com.xwc1125.chain5j.abi.FunctionReturnDecoder;
import com.xwc1125.chain5j.abi.TypeReference;
import com.xwc1125.chain5j.abi.datatypes.Type;
import com.xwc1125.chain5j.abi.datatypes.Utf8String;
import com.xwc1125.chain5j.abi.datatypes.generated.AbiTypes;

/**
 * eth_call.
 */
public class EthCall extends Response<String> {

    // Numeric.toHexString(Hash.sha3("Error(string)".getBytes())).substring(0, 10)
    private static final String errorMethodId = "0x08c379a0";

    @SuppressWarnings("unchecked")
    private static final List<TypeReference<Type>> revertReasonType = Collections.singletonList(
            TypeReference.create((Class<Type>) AbiTypes.getType("string")));

    public String getValue() {
        return getResult();
    }

    public boolean reverts() {
        return getValue() != null && getValue().startsWith(errorMethodId);
    }

    public String getRevertReason() {
        if (reverts()) {
            String hexRevertReason = getValue().substring(errorMethodId.length());
            List<Type> decoded = FunctionReturnDecoder.decode(hexRevertReason, revertReasonType);
            Utf8String decodedRevertReason = (Utf8String) decoded.get(0);
            return decodedRevertReason.getValue();
        }
        return null;
    }
}