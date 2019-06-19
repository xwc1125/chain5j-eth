package com.xwc1125.chain5j.iban;

import com.xwc1125.chain5j.crypto.ICAPUtils;
import org.junit.Test;


/**
 * @Description:
 * @Author: xwc1125
 * @Date: 2019-06-10 19:38
 * @Copyright Copyright@2019
 */
public class ICAPUtilsTest {

    @Test
    public void getAddressLenNoPre() {
        String icap = ICAPUtils.buildICAP("JQ", "0x73dA1a18Ed4c58223Fb8c2A54D9833DF5329E6bF");
        System.out.println("icap=" + icap);
        String decodeICAP = ICAPUtils.decodeICAP("JQ", icap);
        System.out.println("decodeICAP=" + decodeICAP);
        decodeICAP = ICAPUtils.decodeICAP("JQ", "JQ0903KZ4W1JJS92IJKHOJMP0344Y4W2XMN");
        System.out.println("decodeICAP=" + decodeICAP);
    }
}
