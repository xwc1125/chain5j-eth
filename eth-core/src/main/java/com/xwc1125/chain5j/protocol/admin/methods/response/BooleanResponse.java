package com.xwc1125.chain5j.protocol.admin.methods.response;

import com.xwc1125.chain5j.protocol.core.Response;

/**
 * Boolean response type.
 */
public class BooleanResponse extends Response<Boolean> {
    public boolean success() {
        return getResult();
    }
}
