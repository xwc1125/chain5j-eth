package com.xwc1125.chain5j.protocol.core.methods.response;

import com.xwc1125.chain5j.protocol.core.Response;

public class EthSubscribe extends Response<String> {
    public String getSubscriptionId() {
        return getResult();
    }
}
