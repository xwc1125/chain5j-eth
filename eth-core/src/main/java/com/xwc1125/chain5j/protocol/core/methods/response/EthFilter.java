package com.xwc1125.chain5j.protocol.core.methods.response;

import java.math.BigInteger;

import com.xwc1125.chain5j.protocol.core.Response;
import com.xwc1125.chain5j.utils.Numeric;

/**
 * eth_newFilter.
 */
public class EthFilter extends Response<String> {
    public BigInteger getFilterId() {
        return Numeric.decodeQuantity(getResult());
    }
}
