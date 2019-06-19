package com.xwc1125.chain5j.protocol.token.response;

import com.xwc1125.chain5j.protocol.core.Response;
import com.xwc1125.chain5j.utils.Numeric;

import java.math.BigInteger;

/**
 * @Description:
 * @Author: xwc1125
 * @Date: 2019-06-17 18:31
 * @Copyright Copyright@2019
 */
public class GetTokenBalance extends Response<String> {
    public BigInteger getBalance() {
        return Numeric.decodeQuantity(getResult());
    }
}
