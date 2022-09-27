package com.xwc1125.chain5j.iban;

/**
 * @Description:
 * @Author: xwc1125
 * @Date: 2021/4/25 16:37
 * @Copyright Copyright@2021
 */
public class IBanInfo {

    /**
     * 资产标识符，3个字符的字母数字（<16位）
     */
    int currencyLen;
    /**
     * 机构标识符长度
     */
    int orgCodeLen;

    /**
     * 机构客户标识符的标准长度
     * 如果长度不足时，会进行补0
     */
    int customerLen;

    /**
     * iban标识
     */
    String iban;

    public IBanInfo() {
    }

    /**
     * @param currencyLen 资产长度
     * @param orgCodeLen  机构长度
     * @param customerLen 客户标识长度(不含0x)
     * @param iban        iban字符串
     */
    public IBanInfo(int currencyLen, Integer orgCodeLen, int customerLen, String iban) {
        this.currencyLen = currencyLen;
        this.orgCodeLen = orgCodeLen;
        this.iban = iban;
        this.customerLen = customerLen;
    }

    public int getCurrencyLen() {
        return currencyLen;
    }

    public void setCurrencyLen(int currencyLen) {
        this.currencyLen = currencyLen;
    }

    public int getOrgCodeLen() {
        return orgCodeLen;
    }

    public void setOrgCodeLen(int orgCodeLen) {
        this.orgCodeLen = orgCodeLen;
    }

    public int getCustomerLen() {
        return customerLen;
    }

    public void setCustomerLen(int customerLen) {
        this.customerLen = customerLen;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"currencyLen\":")
                .append(currencyLen);
        sb.append(",\"orgCodeLen\":")
                .append(orgCodeLen);
        sb.append(",\"customerLen\":")
                .append(customerLen);
        sb.append(",\"iban\":\"")
                .append(iban).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
