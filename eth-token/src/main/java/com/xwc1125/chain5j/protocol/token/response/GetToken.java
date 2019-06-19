package com.xwc1125.chain5j.protocol.token.response;

import com.xwc1125.chain5j.protocol.core.Response;
import com.xwc1125.chain5j.utils.Convert;
import com.xwc1125.chain5j.utils.Numeric;

import java.math.BigInteger;

/**
 * @Description:
 * @Author: xwc1125
 * @Date: 2019-06-17 17:29
 * @Copyright Copyright@2019
 */
public class GetToken extends Response<GetToken.TokenInfo> {

    public TokenInfo getTokenInfo() {
        return this.getResult();
    }

    public class TokenInfo {
        private String manager;
        private String name;
        private String supply;
        private Boolean canIncrease;
        private Boolean canBurn;

        public TokenInfo() {
        }

        public String getManager() {
            return manager;
        }

        public void setManager(String manager) {
            this.manager = manager;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BigInteger getSupply() {
            return Numeric.decodeQuantity(supply);
        }

        public void setSupply(String supply) {
            this.supply = supply;
        }

        public Boolean getCanIncrease() {
            return canIncrease;
        }

        public void setCanIncrease(Boolean canIncrease) {
            this.canIncrease = canIncrease;
        }

        public Boolean getCanBurn() {
            return canBurn;
        }

        public void setCanBurn(Boolean canBurn) {
            this.canBurn = canBurn;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("TokenInfo{");
            sb.append("manager='").append(manager).append('\'');
            sb.append(", name='").append(name).append('\'');
            sb.append(", supply=").append(getSupply());
            sb.append(", canIncrease=").append(canIncrease);
            sb.append(", canBurn=").append(canBurn);
            sb.append('}');
            return sb.toString();
        }
    }
}
