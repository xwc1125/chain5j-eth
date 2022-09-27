package com.xwc1125.chain5j.engine;

import com.xwc1125.chain5j.crypto.Credentials;
import com.xwc1125.chain5j.crypto.Hash;
import com.xwc1125.chain5j.crypto.RawTransaction;
import com.xwc1125.chain5j.crypto.Sign;
import com.xwc1125.chain5j.crypto.TransactionUtils;
import org.junit.Test;

import java.math.BigInteger;

/**
 * @Description:
 * @Author: xwc1125
 * @Date: 2019-06-04 10:07
 * @Copyright Copyright@2019
 */
public class SignEngineTest {

    @Test
    public void signMsg() {
        Credentials credentials = null;
        try {
            credentials = WalletEngine.loadCredentialsByPrivateKey(
                    "0x587ca4a15bc4d239cfba433dda03366506e99ecd2c529216eb3168b3e7806257");
            System.out.println("address:" + credentials.getAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String msg = "aa";
        Sign.SignatureData signatureData = Sign.signMessage(msg.getBytes(), credentials.getEcKeyPair(), true);
        System.out.println(signatureData.toString());

        BigInteger privateKey = credentials.getEcKeyPair().getPrivateKey();
        BigInteger publicKey = credentials.getEcKeyPair().getPublicKey();

//        Cipher cipher = new NullCipher();
//        // Cipher.getInstance(ALGORITHM, keyFactory.getProvider());
//        cipher.init(Cipher.ENCRYPT_MODE, pubKey, ecPublicKeySpec.getParams());
//
//        try {
//            byte[] bytes = cipher.doFinal(msg.getBytes());
//
//        } catch (IllegalBlockSizeException e) {
//            e.printStackTrace();
//        } catch (BadPaddingException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void signTx() {
        Credentials credentials = WalletEngine.loadCredentialsByPrivateKey(
                "0x587ca4a15bc4d239cfba433dda03366506e99ecd2c529216eb3168b3e7806257");
        RawTransaction rawTransaction = RawTransaction.createEtherTransaction(BigInteger.ZERO, BigInteger.ZERO,
                BigInteger.valueOf(21000), "0x9254E62FBCA63769DFd4Cc8e23f630F0785610CE",
                BigInteger.ZERO);
        String rawTxHex = SignEngine.sign(rawTransaction, credentials);
        System.out.println(rawTxHex);
        String txHashLocal = Hash.sha3(rawTxHex);
        System.out.println(txHashLocal);

        String hex = TransactionUtils.generateTransactionHashHexEncoded(rawTransaction, credentials);
        System.out.println(hex);
    }
}
