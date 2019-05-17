package com.xwc1125.chain5j.protocol.pantheon.response;

import java.util.Map;

import com.xwc1125.chain5j.protocol.core.Response;


public class PantheonEthAccountsMapResponse extends Response<Map<String, Boolean>> {
    public Map<String, Boolean> getAccounts() {
        return getResult();
    }
}
