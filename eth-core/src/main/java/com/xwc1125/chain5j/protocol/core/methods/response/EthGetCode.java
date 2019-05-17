package com.xwc1125.chain5j.protocol.core.methods.response;

import com.xwc1125.chain5j.protocol.core.Response;

/**
 * eth_getCode.
 */
public class EthGetCode extends Response<String> {
    public String getCode() {
        return getResult();
    }
}
