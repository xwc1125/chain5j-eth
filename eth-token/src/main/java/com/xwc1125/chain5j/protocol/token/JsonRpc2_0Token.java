package com.xwc1125.chain5j.protocol.token;

import com.xwc1125.chain5j.protocol.Web3jService;
import com.xwc1125.chain5j.protocol.core.DefaultBlockParameter;
import com.xwc1125.chain5j.protocol.core.JsonRpc2_0Web3j;
import com.xwc1125.chain5j.protocol.core.Request;
import com.xwc1125.chain5j.protocol.token.response.GetToken;
import com.xwc1125.chain5j.protocol.token.response.GetTokenBalance;
import com.xwc1125.chain5j.protocol.token.response.GetTokenSupport;

import java.util.Arrays;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @Description:
 * @Author: xwc1125
 * @Date: 2019-06-17 17:13
 * @Copyright Copyright@2019
 */
public class JsonRpc2_0Token extends JsonRpc2_0Web3j implements Token {
    public JsonRpc2_0Token(Web3jService web3jService) {
        super(web3jService, "eth");
    }

    public JsonRpc2_0Token(Web3jService web3jService, String clientIdentifier) {
        super(web3jService, clientIdentifier);
    }

    public JsonRpc2_0Token(Web3jService web3jService, String clientIdentifier, long pollingInterval, ScheduledExecutorService scheduledExecutorService) {
        super(web3jService, clientIdentifier, pollingInterval, scheduledExecutorService);
    }

    @Override
    public Request<?, GetTokenBalance> getTokenBalance(String icapAddress, String tokenIcapAddress, DefaultBlockParameter defaultBlockParameter) {
        return new Request<>(
                clientIdentifier + "_getTokenBalance",
                Arrays.asList(icapAddress, tokenIcapAddress, defaultBlockParameter.getValue()),
                web3jService,
                GetTokenBalance.class);
    }

    @Override
    public Request<?, GetToken> getToken(String tokenIcapAddress, DefaultBlockParameter defaultBlockParameter) {
        return new Request<>(
                clientIdentifier + "_getToken",
                Arrays.asList(tokenIcapAddress, defaultBlockParameter.getValue()),
                web3jService,
                GetToken.class);
    }

    @Override
    public Request<?, GetTokenSupport> getTokenSupport(String tokenIcapAddress, DefaultBlockParameter defaultBlockParameter) {
        return new Request<>(
                clientIdentifier + "_getTokenSupport",
                Arrays.asList(tokenIcapAddress, defaultBlockParameter.getValue()),
                web3jService,
                GetTokenSupport.class);
    }

}
