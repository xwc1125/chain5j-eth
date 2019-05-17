package com.xwc1125.chain5j.protocol.core.methods.response;

import com.xwc1125.chain5j.protocol.core.Response;

/**
 * eth_compileSerpent.
 */
public class EthCompileSerpent extends Response<String> {
    public String getCompiledSourceCode() {
        return getResult();
    }
}
