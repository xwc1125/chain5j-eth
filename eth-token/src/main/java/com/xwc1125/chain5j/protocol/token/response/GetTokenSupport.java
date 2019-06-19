package com.xwc1125.chain5j.protocol.token.response;

import com.xwc1125.chain5j.abi.datatypes.generated.IcapAddress;
import com.xwc1125.chain5j.protocol.core.Response;

/**
 * @Description:
 * @Author: xwc1125
 * @Date: 2019-06-17 17:45
 * @Copyright Copyright@2019
 */
public class GetTokenSupport extends Response<String> {

    public String getTokenSupport() {
        return this.getResult().toString();
    }
}
