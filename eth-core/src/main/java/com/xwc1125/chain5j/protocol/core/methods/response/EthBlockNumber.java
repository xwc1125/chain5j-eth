package com.xwc1125.chain5j.protocol.core.methods.response;

import java.math.BigInteger;

import com.xwc1125.chain5j.protocol.core.Response;
import com.xwc1125.chain5j.utils.Numeric;

/**
 * eth_blockNumber.
 */
public class EthBlockNumber extends Response<String> {
    public BigInteger getBlockNumber() {
        return Numeric.decodeQuantity(getResult());
    }
}
