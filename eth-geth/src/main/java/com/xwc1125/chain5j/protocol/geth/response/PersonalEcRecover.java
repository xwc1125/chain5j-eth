package com.xwc1125.chain5j.protocol.geth.response;

import com.xwc1125.chain5j.protocol.core.Response;

/**
 * personal_ecRecover.
 */
public class PersonalEcRecover extends Response<String> {
    public String getRecoverAccountId() {
        return getResult();
    }
}
