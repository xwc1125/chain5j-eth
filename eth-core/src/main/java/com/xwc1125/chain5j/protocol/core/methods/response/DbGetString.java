package com.xwc1125.chain5j.protocol.core.methods.response;

import com.xwc1125.chain5j.protocol.core.Response;

/**
 * db_getString.
 */
public class DbGetString extends Response<String> {

    public String getStoredValue() {
        return getResult();
    }
}
