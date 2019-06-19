package com.xwc1125.chain5j.protocol.token;

import com.xwc1125.chain5j.protocol.Web3j;
import com.xwc1125.chain5j.protocol.Web3jService;
import com.xwc1125.chain5j.protocol.core.DefaultBlockParameter;
import com.xwc1125.chain5j.protocol.core.Request;
import com.xwc1125.chain5j.protocol.token.response.GetToken;
import com.xwc1125.chain5j.protocol.token.response.GetTokenBalance;
import com.xwc1125.chain5j.protocol.token.response.GetTokenSupport;

import java.util.concurrent.ScheduledExecutorService;

/**
 * @Description:
 * @Author: xwc1125
 * @Date: 2019-06-17 17:12
 * @Copyright Copyright@2019
 */
public interface Token extends Web3j {

    static Token build(Web3jService web3jService) {
        return new JsonRpc2_0Token(web3jService, "eth");
    }

    static Token build(Web3jService web3jService, String clientIdentifier) {
        return new JsonRpc2_0Token(web3jService, clientIdentifier);
    }

    static Token build(
            Web3jService web3jService, String clientIdentifier, long pollingInterval,
            ScheduledExecutorService scheduledExecutorService) {
        return new JsonRpc2_0Token(web3jService, clientIdentifier, pollingInterval, scheduledExecutorService);
    }

    Request<?, GetTokenBalance> getTokenBalance(
            String icapAddress, String tokenIcapAddress, DefaultBlockParameter defaultBlockParameter);

    Request<?, GetToken> getToken(String tokenIcapAddress, DefaultBlockParameter defaultBlockParameter);

    Request<?, GetTokenSupport> getTokenSupport(String contractIcapAddress, DefaultBlockParameter defaultBlockParameter);


}
