package com.xwc1125.chain5j.protocol.core.methods.response;

import com.xwc1125.chain5j.protocol.core.Response;

/**
 * shh_newIdentity.
 */
public class ShhNewIdentity extends Response<String> {

    public String getAddress() {
        return getResult();
    }
}
