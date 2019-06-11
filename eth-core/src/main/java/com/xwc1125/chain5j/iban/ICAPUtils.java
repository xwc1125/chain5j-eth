package com.xwc1125.chain5j.iban;

import com.xwc1125.chain5j.utils.StringUtils;

import java.math.BigInteger;

/**
 * @Description:
 * @Author: xwc1125
 * @Date: 2019-06-10 14:18
 * @Copyright Copyright@2019
 */
public class ICAPUtils {
    private static String PRE_ADDRESS = "0x";
    private static String ICAP_XE_PREFIX = "XE";
    private static String IBAN_SCHEME = "iban:";
    private static String IBAN_MOD = "97";
    private static String ADDRESS_TEMPLATE = "0x00dA1a18Ed4c58223Fb8c2A54D9833DF5329E6bF";
    private static Integer AddressLenNoPre;
    private static Integer AddressLen;

    public static int getAddressLenNoPre() {
        if (AddressLenNoPre == null) {
            AddressLenNoPre = ADDRESS_TEMPLATE.length() - PRE_ADDRESS.length();
        }
        return AddressLenNoPre;
    }

    public static int getAddressLen() {
        if (AddressLen == null) {
            AddressLen = ADDRESS_TEMPLATE.length();
        }
        return AddressLen;
    }

    /**
     * Build ICAP iban address from ethereum address.
     *
     * @param address ethereum address
     * @return ICAP iban address
     * @example input:  0x730aea2b39aa2cf6b24829b3d39dc9a1f9297b88
     * return: iban:XE42DFRZLRUTFTFY4EVINAHYF7TQ6MACYH4
     */
    public static String buildICAP(String prefix, String address) {
        if (!address.startsWith(PRE_ADDRESS) || address.length() != getAddressLen()) {
            throw new IllegalArgumentException("Invalid ethereum address.");
        }
        // from base16
        BigInteger ethInt = new BigInteger(address.substring(2), 16);
        // to base36
        String base36Addr = ethInt.toString(36).toUpperCase();
        int base36Len = base36Addr.length();
        if (base36Len < 31) {
            // addres is start 0x00
            StringBuffer buffer1 = new StringBuffer();
            buffer1.append(StringUtils.addZeroForNum(base36Addr, 31, true));
            base36Addr = buffer1.toString();
        }

        String checkAddr = base36Addr + prefix + "00";
        String base10Str = "";
        for (Character c : checkAddr.toCharArray()) {
            base10Str += new BigInteger(c.toString(), 36);
        }
        Integer checkSum = 98 - (new BigInteger(base10Str)).mod(new BigInteger(IBAN_MOD)).intValue();
        if (prefix.equals(ICAP_XE_PREFIX)) {
            prefix = IBAN_SCHEME + ICAP_XE_PREFIX;
        }
        String checkCode = checkSum.toString();
        if (checkCode.length() < 2) {
            checkCode = StringUtils.addZeroForNum(checkCode, 2, true);
        }
        String icapAddress = prefix + checkCode + base36Addr;
        return icapAddress;
    }

    /**
     * Decode ethereum address from ICAP iban address
     *
     * @param icapAddress ICAP iban address
     * @return ethereum address
     * @example input:  iban:XE42DFRZLRUTFTFY4EVINAHYF7TQ6MACYH4
     * return: 0x730aea2b39aa2cf6b24829b3d39dc9a1f9297b88
     */
    public static String decodeICAP(String prefix, String icapAddress) {
        if (!isValid(prefix, icapAddress)) {
            throw new IllegalArgumentException("Invalid icap address.");
        }
        int startLen = prefix.length() + 2;
        if (prefix.equals(ICAP_XE_PREFIX)) {
            startLen = (IBAN_SCHEME + ICAP_XE_PREFIX).length() + 2;
        }
        BigInteger ethInt = new BigInteger(icapAddress.substring(startLen), 36);
        String base16Addr = ethInt.toString(16).toLowerCase();
        if (base16Addr.length() < getAddressLenNoPre()) {
            base16Addr = StringUtils.addZeroForNum(base16Addr, getAddressLenNoPre(), true);
        }
        return PRE_ADDRESS + base16Addr;
    }

    /**
     * Check ICAP iban address validation
     *
     * @param icapAddress ICAP iban address
     * @return true if valid; false if invalid
     */
    public static boolean isValid(String prefix, String icapAddress) {
        final int length = prefix.length();
        final int lengthBase31 = 31 + 2 + length;
        final int lengthBase30 = 30 + 2 + length;
        final int lengthBase18 = 2 + 3 + 4 + 9 + length;
        if (icapAddress.startsWith(IBAN_SCHEME)) {
            icapAddress = icapAddress.substring(IBAN_SCHEME.length());
        }
        int icapAddressLen = icapAddress.length();
        if ((!icapAddress.startsWith(prefix) && !icapAddress.startsWith(ICAP_XE_PREFIX)) ||
                (
                        icapAddressLen != lengthBase31
                                && icapAddressLen != lengthBase30
                                && icapAddressLen != lengthBase18)) {
            return false;
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append(icapAddress.substring(prefix.length() + 2));

        buffer.append(icapAddress, 0, prefix.length() + 2);

        String base10Str = "";
        String base36Str = buffer.toString();
        for (Character c : base36Str.toCharArray()) {
            base10Str += new BigInteger(c.toString(), 36);
        }
        Integer checkSum = (new BigInteger(base10Str)).mod(new BigInteger(IBAN_MOD)).intValue();
        return checkSum == 1;
    }
}
