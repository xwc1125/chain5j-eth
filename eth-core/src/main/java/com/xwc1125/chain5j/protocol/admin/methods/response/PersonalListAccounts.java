package com.xwc1125.chain5j.protocol.admin.methods.response;

import java.util.List;

import com.xwc1125.chain5j.protocol.core.Response;

/**
 * personal_listAccounts.
 */
public class PersonalListAccounts extends Response<List<String>> {
    public List<String> getAccountIds() {
        return getResult();
    }
}
