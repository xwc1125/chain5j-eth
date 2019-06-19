package com.xwc1125.chain5j.protocol.token;

import com.xwc1125.chain5j.protocol.core.DefaultBlockParameterName;
import com.xwc1125.chain5j.protocol.core.Request;
import com.xwc1125.chain5j.protocol.http.HttpService;
import com.xwc1125.chain5j.protocol.token.response.GetToken;
import com.xwc1125.chain5j.protocol.token.response.GetTokenBalance;
import com.xwc1125.chain5j.protocol.token.response.GetTokenSupport;
import org.junit.Test;

/**
 * @Description:
 * @Author: xwc1125
 * @Date: 2019-06-17 17:58
 * @Copyright Copyright@2019
 */
public class TokenTest {

    @Test
    public void ethGetTokenBalance() {
        try {
            Token token = Token.build(new HttpService("http://127.0.0.1:8545"), "eth");
            Request<?, GetToken> ethGetToken = token.getToken("JQ0903KZ4W1JJS92IJKHOJMP0344Y4W2XMN", DefaultBlockParameterName.LATEST);
            System.out.println(ethGetToken.send().getTokenInfo());
            Request<?, GetTokenBalance> ethGetTokenBalance = token.getTokenBalance("JQ0903KZ4W1JJS92IJKHOJMP0344Y4W2XMN", "JQ0903KZ4W1JJS92IJKHOJMP0344Y4W2XMN", DefaultBlockParameterName.LATEST);
            System.out.println(ethGetTokenBalance.send().getBalance());
            Request<?, GetTokenSupport> ethGetTokenSupportRequest = token.getTokenSupport("JQ0903KZ4W1JJS92IJKHOJMP0344Y4W2XMN", DefaultBlockParameterName.LATEST);
            System.out.println("support=" + ethGetTokenSupportRequest.send().getTokenSupport());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
