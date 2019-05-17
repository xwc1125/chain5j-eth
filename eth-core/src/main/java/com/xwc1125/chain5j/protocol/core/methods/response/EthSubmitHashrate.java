package com.xwc1125.chain5j.protocol.core.methods.response;

import com.xwc1125.chain5j.protocol.core.Response;

/**
 * eth_submitHashrate.
 */
public class EthSubmitHashrate extends Response<Boolean> {

    public boolean submissionSuccessful() {
        return getResult();
    }
}
