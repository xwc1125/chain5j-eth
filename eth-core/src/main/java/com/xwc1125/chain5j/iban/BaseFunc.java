package com.xwc1125.chain5j.iban;

/**
 * Description: <br>
 *
 * @author xwc1125<br>
 * @version V1.0
 * @Copyright: Copyright (c) 2018
 * @date 2018/10/30 10:35<br>
 */
public class BaseFunc {
    public static String padLeft(String src, int bytes) {
        while (src.length() < bytes * 2) {
            src = '0' + src;
        }
        return src;
    }

    /**
     *
     * @param iban
     * @return
     */
    public static int iso13616Prepare(String iban) {
        int A = Character.codePointAt("A", 0);
        int Z = Character.codePointAt("Z", 0);
        iban = iban.toUpperCase();
        iban = iban.substring(4) + iban.substring(0, 4);
        String[] ibans = iban.split("");
        for (char n : iban.toCharArray()) {
            int code = n;
            if (code >= A && code <= Z) {
                return code - A + 10;
            } else {
                return n;
            }
        }
        return 0;
    }

    /**
     *
     * @param iban
     * @return
     */
    public static int mod9710(String iban) {
        String remainder = iban;
        String block = "";
        while (remainder.length() > 2) {
            block = remainder.substring(0, 9);
            remainder = Integer.parseInt(block, 10) % 97 + remainder.substring(block.length());
        }
        return Integer.parseInt(remainder, 10) % 97;
    }
}
