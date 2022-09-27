package com.xwc1125.chain5j.iban;

import com.xwc1125.chain5j.utils.Numeric;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: <br>
 *
 * @author xwc1125<br>
 * @version V1.0
 * @Copyright: Copyright (c) 2018
 * @date 2018/10/30 10:35<br>
 */
public class IBanUtils extends BaseFunc {

    /**
     * 需要换成bigInteger的类型（16进制）
     */
    private static Integer VALUE_RADIX = 16;
    /**
     * 目标bigInteger的类型（36进制）
     */
    private static Integer DES_VALUE_RADIX = 36;

    /**
     * 16进制前缀
     */
    private static String HEX_PREFIX = "0x";
    private static Pattern p = Pattern.compile("-?[0-9a-fA-F]+");

    /**
     * 将客户信息转换为IBAN
     *
     * @param customer
     * @return
     */
    public static IBanInfo toICAP(Customer customer) throws Exception {
        String hex = Numeric.cleanHexPrefix(customer.customer);
        if (!isHexNumber(hex)) {
            throw new Exception("customer is not hex");
        }
        BigInteger asBn = new BigInteger(hex, VALUE_RADIX);
        String base36 = asBn.toString(DES_VALUE_RADIX);
        int currencyLen = 0;
        if (customer.currency != null) {
            currencyLen = customer.currency.length();
        }
        int orgCodeLen = 0;
        if (customer.orgCode != null) {
            orgCodeLen = customer.orgCode.length();
        }
        // 减去2位校验码
        int len = customer.resultLen - currencyLen - orgCodeLen - 2;
        String padded = padLeft(base36, len);
        String iban = fromBban(customer.currency, customer.orgCode, padded.toUpperCase());
        return new IBanInfo(currencyLen, orgCodeLen, hex.length(), iban);
    }

    // 十六进制
    private static boolean isHexNumber(String str) {
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * address to iban
     *
     * @param address
     * @return
     */
    public static String fromAddress(String address) throws Exception {
        IBanInfo icap = toICAP(new Customer("XE", "", 35, address));
        return icap.iban;
    }

    /**
     * Description: bban to iban
     * </p>
     *
     * @param bban
     * @return java.lang.String
     * @Author: xwc1125
     * @Date: 2019-05-31 17:28:12
     */
    private static String fromBban(String currency, String orgCode, String bban) {
        if (orgCode == null) {
            orgCode = "";
        }
        orgCode = orgCode.toUpperCase();
        int orgCodeLen = orgCode.length();
        if (currency == null) {
            currency = "";
        }
        currency = currency.toUpperCase();
        int currencyLen = currency.length();
        String countryCode = currency + orgCode;
        int remainder = Modulo97.checksum(orgCodeLen + currencyLen, (countryCode + "00" + bban) + "");
        String checkDigit = 98 - remainder + "";
        return countryCode + padLeft(checkDigit, 2) + bban;
    }

    /**
     * Is it an Iban address?
     *
     * @param iban
     * @return
     */
    public static boolean isValid(int prefixLen, String iban) {
        return Modulo97.verifyCheckDigits(prefixLen, iban);
    }

    /**
     * iban to address
     *
     * @param iban
     * @return
     */
    public static Customer fromICAP(IBanInfo iban) throws Exception {
        int prefixLen = iban.currencyLen + iban.orgCodeLen;
        if (isValid(prefixLen, iban.iban)) {
            String currency = iban.iban.substring(0, iban.currencyLen);
            String orgCode = iban.iban.substring(iban.currencyLen, prefixLen);
            // 最后的加2是2位校验码
            String base36 = iban.iban.substring(prefixLen + 2);
            BigInteger asBn = new BigInteger(base36, DES_VALUE_RADIX);
            String hex = padLeft(asBn.toString(VALUE_RADIX), iban.customerLen);
            if (hex != null && !hex.startsWith(HEX_PREFIX) && !hex.startsWith(HEX_PREFIX)) {
                hex = HEX_PREFIX + hex;
            }
            return new Customer(currency, orgCode, iban.customerLen, hex);
        } else {
            throw new Exception("invalid iban address:" + iban);
        }
    }

    /**
     * iban to address
     *
     * @param iban
     * @return
     */
    public static String toAddress(String iban) throws Exception {
        Customer customer = fromICAP(new IBanInfo(2, 0, 40, iban));
        return customer.customer;
    }
}
