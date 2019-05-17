package com.xwc1125.chain5j.protocol.core.methods.response;

import java.util.List;

import com.xwc1125.chain5j.protocol.core.Response;

/**
 * eth_accounts.
 */
public class EthAccounts extends Response<List<String>> {
    public List<String> getAccounts() {
        return getResult();
    }
}
