package com.xwc1125.chain5j.engine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xwc1125.chain5j.crypto.*;
import com.xwc1125.chain5j.protocol.ObjectMapperFactory;
import com.xwc1125.chain5j.utils.Numeric;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

/**
 * @Description:
 * @Author: xwc1125
 * @Date: 2019-05-31 16:11
 * @Copyright Copyright@2019
 */
public class WalletEngine {
    private static final String HEX_PREFIX = "0x";

    private static final SecureRandom secureRandom = SecureRandomUtils.secureRandom();

    /**
     * Description: generate key pair to create wallet
     * </p>
     *
     * @param mnemonics
     * @param password
     * @return com.xwc1125.chain5j.crypto.ECKeyPair
     * @Author: xwc1125
     * @Date: 2019-05-31 16:55:14
     */
    public static ECKeyPair generateKeyPair(String mnemonics, String password) throws Exception {
        if (!MnemonicUtils.validateMnemonic(mnemonics)) {
            throw new Exception("mnemonics is wrong");
        }
        Credentials credentials = Bip44WalletUtils.loadBip44Credentials(password, mnemonics);
        return credentials.getEcKeyPair();
    }

    /**
     * Description: TODO
     * </p>
     *
     * @param password
     * @param isFullWallet
     * @return java.lang.String
     * @Author: xwc1125
     * @Date: 2019-05-31 16:56:32
     */
    public static String generateKeystore(String password, boolean isFullWallet) throws CipherException, IOException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
        ECKeyPair ecKeyPair = Keys.createEcKeyPair();
        WalletFile walletFile;

        if (isFullWallet) {
            walletFile = Wallet.createStandard(password, ecKeyPair);
        } else {
            walletFile = Wallet.createLight(password, ecKeyPair);
        }

        return getKeystore(walletFile);
    }

    /**
     * 获取keystore
     *
     * @param walletFile
     * @return
     * @throws IOException
     */
    public static String getKeystore(WalletFile walletFile) throws IOException {
        ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
        String keystore = objectMapper.writeValueAsString(walletFile);
        return keystore;
    }

    /**
     * 生成一组随机的助记词
     *
     * @return
     */
    public String generateMnemonics() {
        byte[] initialEntropy = new byte[16];
        secureRandom.nextBytes(initialEntropy);
        String mnemonic = MnemonicUtils.generateMnemonic(initialEntropy);
        return mnemonic;
    }

    /**
     * 生成私钥（16进制）
     *
     * @return
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     */
    public static String generatePrivateKey()
            throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
        ECKeyPair ecKeyPair = Keys.createEcKeyPair();
        return getPrivateKey(ecKeyPair);
    }

    /**
     * 获取私钥
     *
     * @param ecKeyPair
     * @return
     */
    public static String getPrivateKey(ECKeyPair ecKeyPair) {
        BigInteger privateKey = ecKeyPair.getPrivateKey();
        return Numeric.toHexStringWithPrefix(privateKey);
    }

    /**
     * 生成钱包
     *
     * @param password     密码
     * @param isFullWallet 是否创建全量钱包
     * @return
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws CipherException
     */
    public static WalletFile generateWallet(String password, boolean isFullWallet)
            throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException,
            CipherException {
        ECKeyPair ecKeyPair = Keys.createEcKeyPair();
        WalletFile walletFile;

        if (isFullWallet) {
            walletFile = Wallet.createStandard(password, ecKeyPair);
        } else {
            walletFile = Wallet.createLight(password, ecKeyPair);
        }

        return walletFile;
    }

    // ==================导入=================

    /**
     * @param ecKeyPair
     * @return
     */
    public static Credentials loadCredentialsByECKeyPair(ECKeyPair ecKeyPair) {
        return Credentials.create(ecKeyPair);
    }

    /**
     * 通过keystore获取证书
     *
     * @param password
     * @param keystoreContent
     * @return
     * @throws IOException
     * @throws CipherException
     */
    public static Credentials loadCredentialsByKeyStore(String password, String keystoreContent)
            throws IOException, CipherException {
        ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
        WalletFile walletFile = objectMapper.readValue(keystoreContent, WalletFile.class);
        ECKeyPair ecKeyPair = Wallet.decrypt(password, walletFile);

        return loadCredentialsByECKeyPair(ecKeyPair);
    }

    /**
     * 助记词加载证书
     *
     * @param mnemonics
     * @return
     * @throws IOException
     * @throws CipherException
     */
    public static Credentials loadCredentialsByMnemonics(String mnemonics) throws Exception {
        ECKeyPair ecKeyPair = generateKeyPair(mnemonics, null);

        return loadCredentialsByECKeyPair(ecKeyPair);
    }

    public static ECKeyPair loadECKeyPairByPrivateKey(BigInteger privateKey) {
        return ECKeyPair.create(privateKey);
    }

    /**
     * @param privateKey 16进制
     * @return
     */
    public static ECKeyPair loadECKeyPairByPrivateKey(String privateKey) {
        return ECKeyPair.create(Numeric.toBigInt(privateKey));
    }

    public static Credentials loadCredentialsByPrivateKey(BigInteger privateKey) {
        return Credentials.create(loadECKeyPairByPrivateKey(privateKey));
    }

    /**
     * @param privateKey 16进制
     * @return
     */
    public static Credentials loadCredentialsByPrivateKey(String privateKey) {
        return Credentials.create(privateKey);
    }

    /**
     * 获取公钥
     *
     * @param ecKeyPair
     * @return
     */
    public static String getPublicKey(ECKeyPair ecKeyPair) {
        BigInteger publicKey = ecKeyPair.getPublicKey();
        return Numeric.toHexStringWithPrefix(publicKey);
    }

    /**
     * 获取地址
     *
     * @param ecKeyPair
     * @return
     */
    public static String getAddress(ECKeyPair ecKeyPair) {
        String address = Keys.getAddress(ecKeyPair);
        return HEX_PREFIX + address;
    }

    // =================地址大小写================

    /**
     * 验证地址的大小写
     *
     * @param address
     * @return
     */
    public static Boolean validateAddress(String address) {
        address = Numeric.cleanHexPrefix(address);
        String hash = Numeric.toHexStringNoPrefix(Hash.sha3(address.toLowerCase().getBytes()));
        for (int i = 0; i < 40; i++) {
            if (Character.isLetter(address.charAt(i))) {
                // each uppercase letter should correlate with a first bit of 1 in the hash
                // char with the same index, and each lowercase letter with a 0 bit
                int charInt = Integer.parseInt(Character.toString(hash.charAt(i)), 16);
                if (((Character.isUpperCase(address.charAt(i)) && charInt <= 7))
                        || ((Character.isLowerCase(address.charAt(i)) && charInt > 7))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 地址生成大小写混合
     *
     * @param address
     * @return
     */
    public static String toBlendAddress(String address) {
        address = Numeric.cleanHexPrefix(address);
        String hash = Numeric.toHexStringNoPrefix(Hash.sha3(address.toLowerCase().getBytes()));
        String ret = "0x";
        for (int i = 0; i < 40; i++) {
            if (Character.isLetter(address.charAt(i))) {
                int charInt = Integer.parseInt(Character.toString(hash.charAt(i)), 16);
                if (charInt > 7) {
                    ret += Character.toString(address.charAt(i)).toUpperCase();
                } else {
                    ret += Character.toString(address.charAt(i));
                }
            } else {
                ret += Character.toString(address.charAt(i));
            }
        }
        return ret;
    }
}
