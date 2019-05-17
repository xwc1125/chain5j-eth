package com.xwc1125.chain5j.protocol.geth.response;

import com.xwc1125.chain5j.protocol.core.Response;

/**
 * personal_importRawKey.
 */
public class PersonalImportRawKey extends Response<String> {
    public String getAccountId() {
        return getResult();
    }
}
