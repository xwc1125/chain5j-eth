package com.xwc1125.chain5j.crypto;

import java.math.BigInteger;

import com.xwc1125.chain5j.utils.Numeric;

/**
 * Transaction class used for signing transactions locally.<br>
 * For the specification, refer to p4 of the <a href="http://gavwood.com/paper.pdf">
 * yellow paper</a>.
 */
public class RawTransaction {

    private BigInteger nonce;
    private BigInteger gasPrice;
    private BigInteger gasLimit;
    private String to;
    private BigInteger value;
    private String data;
    private String token;
    private Boolean hasToken = false;

    protected RawTransaction(BigInteger nonce, BigInteger gasPrice, BigInteger gasLimit, String to,
                             BigInteger value, String data, Boolean hasToken) {
        this.nonce = nonce;
        this.gasPrice = gasPrice;
        this.gasLimit = gasLimit;
        this.to = to;
        this.value = value;
        this.hasToken = hasToken;

        if (data != null) {
            this.data = Numeric.cleanHexPrefix(data);
        }
    }


    protected RawTransaction(BigInteger nonce, BigInteger gasPrice, BigInteger gasLimit, String to,
                             BigInteger value, String data, String token, Boolean hasToken) {
        this.nonce = nonce;
        this.gasPrice = gasPrice;
        this.gasLimit = gasLimit;
        this.to = to;
        this.value = value;

        if (data != null) {
            this.data = Numeric.cleanHexPrefix(data);
        }
        this.token = token;
        this.hasToken = hasToken;
    }

    public static RawTransaction createContractTransaction(
            BigInteger nonce, BigInteger gasPrice, BigInteger gasLimit, BigInteger value,
            String init, Boolean hasToken) {

        return new RawTransaction(nonce, gasPrice, gasLimit, "", value, init, hasToken);
    }

    public static RawTransaction createEtherTransaction(
            BigInteger nonce, BigInteger gasPrice, BigInteger gasLimit, String to,
            BigInteger value, Boolean hasToken) {

        return new RawTransaction(nonce, gasPrice, gasLimit, to, value, "", hasToken);

    }

    public static RawTransaction createTransaction(
            BigInteger nonce, BigInteger gasPrice, BigInteger gasLimit, String to, String data, Boolean hasToken) {
        return createTransaction(nonce, gasPrice, gasLimit, to, BigInteger.ZERO, data, hasToken);
    }

    public static RawTransaction createTransaction(
            BigInteger nonce, BigInteger gasPrice, BigInteger gasLimit, String to,
            BigInteger value, String data, Boolean hasToken) {

        return new RawTransaction(nonce, gasPrice, gasLimit, to, value, data, hasToken);
    }

    public BigInteger getNonce() {
        return nonce;
    }

    public BigInteger getGasPrice() {
        return gasPrice;
    }

    public BigInteger getGasLimit() {
        return gasLimit;
    }

    public String getTo() {
        return to;
    }

    public BigInteger getValue() {
        return value;
    }

    public String getData() {
        return data;
    }

    public String getToken() {
        return token;
    }

    public Boolean getHasToken() {
        return hasToken;
    }

    public void setHasToken(Boolean hasToken) {
        this.hasToken = hasToken;
    }
}
