package com.xwc1125.chain5j.protocol.core.methods.response;

import com.xwc1125.chain5j.protocol.core.Response;

/**
 * eth_coinbase.
 */
public class EthCoinbase extends Response<String> {
    public String getAddress() {
        return getResult();
    }
}
