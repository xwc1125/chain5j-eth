package com.xwc1125.chain5j.protocol.admin.methods.response;

import com.xwc1125.chain5j.protocol.core.Response;

/**
 * personal_unlockAccount.
 */
public class PersonalUnlockAccount extends Response<Boolean> {
    public Boolean accountUnlocked() {
        return getResult();
    }
}