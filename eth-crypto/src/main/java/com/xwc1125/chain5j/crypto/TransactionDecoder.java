package com.xwc1125.chain5j.crypto;

import java.math.BigInteger;

import com.xwc1125.chain5j.rlp.RlpDecoder;
import com.xwc1125.chain5j.rlp.RlpList;
import com.xwc1125.chain5j.rlp.RlpString;
import com.xwc1125.chain5j.rlp.RlpType;
import com.xwc1125.chain5j.utils.Numeric;
import com.xwc1125.chain5j.utils.StringUtils;

public class TransactionDecoder {

    public static RawTransaction decode(String icapPrefix, String hexTransaction) {
        return decode(icapPrefix, hexTransaction, false);
    }

    public static RawTransaction decode(String icapPrefix, String hexTransaction, Boolean hasToken) {
        byte[] transaction = Numeric.hexStringToByteArray(hexTransaction);
        RlpList rlpList = RlpDecoder.decode(transaction);
        RlpList values = (RlpList) rlpList.getValues().get(0);
        BigInteger nonce = ((RlpString) values.getValues().get(0)).asPositiveBigInteger();
        BigInteger gasPrice = ((RlpString) values.getValues().get(1)).asPositiveBigInteger();
        BigInteger gasLimit = ((RlpString) values.getValues().get(2)).asPositiveBigInteger();
        RlpString rlpType = (RlpString) values.getValues().get(3);
        String to = rlpType.asICAPString();

        if (StringUtils.isNotEmpty(icapPrefix)) {
            to = ICAPUtils.buildICAP(icapPrefix, to);
        }
        BigInteger value = ((RlpString) values.getValues().get(4)).asPositiveBigInteger();
        String data = ((RlpString) values.getValues().get(5)).asString();
        if (values.getValues().size() > 6) {
            byte v = ((RlpString) values.getValues().get(6)).getBytes()[0];
            byte[] r = Numeric.toBytesPadded(
                    Numeric.toBigInt(((RlpString) values.getValues().get(7)).getBytes()), 32);
            byte[] s = Numeric.toBytesPadded(
                    Numeric.toBigInt(((RlpString) values.getValues().get(8)).getBytes()), 32);
            Sign.SignatureData signatureData = new Sign.SignatureData(v, r, s);
            return new SignedRawTransaction(icapPrefix, nonce, gasPrice, gasLimit,
                    to, value, data, signatureData, hasToken);
        } else {
            return RawTransaction.createTransaction(nonce,
                    gasPrice, gasLimit, to, value, data, hasToken);
        }
    }

}
