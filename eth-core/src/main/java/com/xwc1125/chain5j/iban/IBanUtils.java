package com.xwc1125.chain5j.iban;

import com.xwc1125.chain5j.utils.Numeric;

import java.math.BigInteger;

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
     * address to iban
     *
     * @param address
     * @return
     */
    public static String fromAddress(String address) {
        address = Numeric.cleanHexPrefix(address);
        BigInteger asBn = new BigInteger(address, 16);
        String base36 = asBn.toString(36);
        String padded = padLeft(base36, 15);
        return fromBban(padded.toUpperCase());
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
    private static String fromBban(String bban) {
        String countryCode = "XE";
        int remainder = Modulo97.checksum((countryCode + "00" + bban) + "");
        String checkDigit = 98 - remainder + "";
        return countryCode + checkDigit + bban;
    }

    /**
     * Is it an Iban address?
     *
     * @param iban
     * @return
     */
    private static boolean isValid(String iban) {
        return Modulo97.verifyCheckDigits(iban);
    }

    /**
     * iban to address
     *
     * @param iban
     * @return
     */
    public static String toAddress(String iban) throws Exception {
        if (isValid(iban)) {
            String base36 = iban.substring(4);
            BigInteger asBn = new BigInteger(base36, 36);
            String address = padLeft(asBn.toString(16), 20);
            if (address != null && !address.startsWith("0x") && !address.startsWith("0X")) {
                address = "0x" + address;
            }
            return address;
        } else {
            throw new Exception("invalid iban address:" + iban);
        }
    }

}
