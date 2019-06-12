package com.xwc1125.chain5j.crypto;

import com.xwc1125.chain5j.utils.Numeric;
import com.xwc1125.chain5j.utils.StringUtils;

/**
 * Credentials wrapper.
 */
public class Credentials {

    private final ECKeyPair ecKeyPair;
    private final String address;

    private Credentials(ECKeyPair ecKeyPair, String address) {
        this.ecKeyPair = ecKeyPair;
        this.address = address;
    }

    public ECKeyPair getEcKeyPair() {
        return ecKeyPair;
    }

    public String getAddress() {
        return address;
    }

    public static Credentials create(ECKeyPair ecKeyPair) {
        return create(null, ecKeyPair);
    }

    public static Credentials create(String icapPrefix, ECKeyPair ecKeyPair) {
        String address = Keys.getAddress(icapPrefix, ecKeyPair);
        if (StringUtils.isEmpty(icapPrefix)) {
            address = Numeric.prependHexPrefix(address);
        }
        return new Credentials(ecKeyPair, address);
    }

    public static Credentials create(String icapPrefix, String privateKey, String publicKey) {
        return create(icapPrefix, new ECKeyPair(Numeric.toBigInt(privateKey), Numeric.toBigInt(publicKey)));
    }

    public static Credentials create(String icapPrefix, String privateKey) {
        return create(icapPrefix, ECKeyPair.create(Numeric.toBigInt(privateKey)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Credentials that = (Credentials) o;

        if (ecKeyPair != null ? !ecKeyPair.equals(that.ecKeyPair) : that.ecKeyPair != null) {
            return false;
        }

        return address != null ? address.equals(that.address) : that.address == null;
    }

    @Override
    public int hashCode() {
        int result = ecKeyPair != null ? ecKeyPair.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
