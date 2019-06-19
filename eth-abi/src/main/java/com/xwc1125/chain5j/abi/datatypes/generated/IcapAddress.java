package com.xwc1125.chain5j.abi.datatypes.generated;

import com.xwc1125.chain5j.abi.datatypes.Address;
import com.xwc1125.chain5j.crypto.ICAPUtils;
import com.xwc1125.chain5j.utils.Numeric;

import java.math.BigInteger;

/**
 * @Description:
 * @Author: xwc1125
 * @Date: 2019-06-17 17:36
 * @Copyright Copyright@2019
 */
public class IcapAddress extends Address {
    public static final String TYPE_NAME = "address";
    public final String icapPrefix;

    public IcapAddress(String icapPrefix, Uint160 value) {
        super(value);
        this.icapPrefix = icapPrefix;
    }

    public IcapAddress(String icapPrefix, BigInteger value) {
        super(value);
        this.icapPrefix = icapPrefix;
    }

    public IcapAddress(String icapPrefix, String hexValue) {
        super(hexValue);
        this.icapPrefix = icapPrefix;
    }

    @Override
    public String getTypeAsString() {
        return TYPE_NAME;
    }

    @Override
    public String toString() {
        String address = Numeric.toHexStringWithPrefixZeroPadded(value.getValue(), LENGTH_IN_HEX);
        return ICAPUtils.buildICAP(icapPrefix, address);
    }

    @Override
    public String getValue() {
        return toString();
    }
}
