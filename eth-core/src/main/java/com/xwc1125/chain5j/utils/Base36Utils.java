package com.xwc1125.chain5j.utils;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @Description:
 * @Author: xwc1125
 * @Date: 2019-06-10 14:08
 * @Copyright Copyright@2019
 */
public class Base36Utils {
    private static final Charset CHAT_SET_UTF8 = StandardCharsets.UTF_8;

    public static String encode(byte[] inputBytes) {
        if (inputBytes == null || inputBytes.length == 0) {
            return "";
        }
        BigInteger bigInteger = new BigInteger(inputBytes);
        String xx = bigInteger.toString(36);
        if (xx.charAt(0) == '-') {
            StringBuffer stringBuffer = new StringBuffer(xx);
            stringBuffer.setCharAt(0, 'f');
            return stringBuffer.toString();
        } else {
            return "z" + xx;
        }
    }

    public static String encodeStr(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        return encode(str.getBytes(CHAT_SET_UTF8));
    }

    public static byte[] decode(String str) {
        if (str == null || str.isEmpty()) {
            return new byte[]{};
        }

        String str1;
        if (str.charAt(0) == 'z') {
            str1 = str.substring(1);
        } else {
            str1 = "-" + str.substring(1);
        }
        BigInteger bigInteger = new BigInteger(str1, 36);
        return bigInteger.toByteArray();
    }

    public static String decodeStr(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        byte[] bytes1 = decode(str);
        return new String(bytes1, CHAT_SET_UTF8);
    }

    public static boolean validBase36(String str) {
        byte[] bytes = str.getBytes();
        for (byte b : bytes) {
            if (b < 48 || (b > 57 && b < 65) || b > 90) {
                return false;
            }
        }
        return true;
    }
}
