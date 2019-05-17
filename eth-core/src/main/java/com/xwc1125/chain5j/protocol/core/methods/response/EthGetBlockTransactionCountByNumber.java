package com.xwc1125.chain5j.protocol.core.methods.response;

import java.math.BigInteger;

import com.xwc1125.chain5j.protocol.core.Response;
import com.xwc1125.chain5j.utils.Numeric;

/**
 * eth_getBlockTransactionCountByNumber.
 */
public class EthGetBlockTransactionCountByNumber extends Response<String> {
    public BigInteger getTransactionCount() {
        return Numeric.decodeQuantity(getResult());
    }
}
