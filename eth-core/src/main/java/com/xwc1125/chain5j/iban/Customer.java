package com.xwc1125.chain5j.iban;

/**
 * @Description:
 * @Author: xwc1125
 * @Date: 2021/4/25 16:27
 * @Copyright Copyright@2021
 */
public class Customer {

    /**
     * 资产标识符，3个字符的字母数字（<16位）
     */
    String currency;
    /**
     * 机构标识符，4个字符的字母数字（<21位）;
     */
    String orgCode;
    /**
     * 机构客户标识符base36的标准长度+currencyLen+orgCodeLen+2位校验码
     * 如果长度不足时，base36会进行补0
     */
    int resultLen;
    /**
     * 机构客户标识符，9个字符的字母数字（<47位）;
     * 16进制
     */
    String customer;

    public Customer() {
    }

    /**
     * @param currency  资产标识
     * @param orgCode   机构code
     * @param resultLen 机构客户标识符base36的标准长度+currencyLen+orgCodeLen+2位校验码，如果长度不足时，base36会进行补0
     * @param customer  来源字符串。16进制
     */
    public Customer(String currency, String orgCode, int resultLen, String customer) {
        this.currency = currency;
        this.orgCode = orgCode;
        this.resultLen = resultLen;
        this.customer = customer;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getresultLen() {
        return resultLen;
    }

    public void setresultLen(int resultLen) {
        this.resultLen = resultLen;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"currency\":\"")
                .append(currency).append('\"');
        sb.append(",\"orgCode\":\"")
                .append(orgCode).append('\"');
        sb.append(",\"resultLen\":")
                .append(resultLen);
        sb.append(",\"customer\":\"")
                .append(customer).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
