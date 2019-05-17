package com.xwc1125.chain5j.protocol.core.methods.response;

import com.xwc1125.chain5j.protocol.core.Response;

/**
 * db_putString.
 */
public class DbPutString extends Response<Boolean> {

    public boolean valueStored() {
        return getResult();
    }
}
