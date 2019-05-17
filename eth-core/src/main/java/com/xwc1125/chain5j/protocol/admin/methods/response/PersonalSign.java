package com.xwc1125.chain5j.protocol.admin.methods.response;

import com.xwc1125.chain5j.protocol.core.Response;

/**
 * personal_sign
 * parity_signMessage.
 */
public class PersonalSign extends Response<String> {
    public String getSignedMessage() {
        return getResult();
    }
}
