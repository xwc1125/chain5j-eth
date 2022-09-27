package com.xwc1125.chain5j;

import com.xwc1125.chain5j.abi.FunctionEncoder;
import com.xwc1125.chain5j.abi.TypeReference;
import com.xwc1125.chain5j.abi.datatypes.Address;
import com.xwc1125.chain5j.abi.datatypes.Function;
import com.xwc1125.chain5j.abi.datatypes.Type;
import com.xwc1125.chain5j.abi.datatypes.Utf8String;
import com.xwc1125.chain5j.abi.datatypes.generated.Bytes32;
import com.xwc1125.chain5j.abi.datatypes.generated.Uint256;
import com.xwc1125.chain5j.bip44.HexUtils;
import com.xwc1125.chain5j.crypto.Credentials;
import com.xwc1125.chain5j.crypto.Hash;
import com.xwc1125.chain5j.crypto.Sign;
import com.xwc1125.chain5j.engine.SignEngine;
import com.xwc1125.chain5j.engine.SignRes;
import com.xwc1125.chain5j.engine.TransactionEngine;
import com.xwc1125.chain5j.engine.WalletEngine;
import com.xwc1125.chain5j.protocol.Web3j;
import com.xwc1125.chain5j.protocol.core.DefaultBlockParameter;
import com.xwc1125.chain5j.protocol.core.Request;
import com.xwc1125.chain5j.protocol.core.methods.request.Transaction;
import com.xwc1125.chain5j.protocol.core.methods.response.EthCall;
import com.xwc1125.chain5j.protocol.core.methods.response.EthEstimateGas;
import com.xwc1125.chain5j.protocol.core.methods.response.TransactionReceipt;
import com.xwc1125.chain5j.protocol.http.HttpService;
import com.xwc1125.chain5j.tx.TransactionManager;
import com.xwc1125.chain5j.utils.Convert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

/**
 * @Description:
 * @Author: xwc1125
 * @Date: 2020/5/14 23:18
 * @Copyright Copyright@2020
 */
public class Erc20Test {

    private static Web3j web3j;
    static String web3jUrl = "http://127.0.0.1:8545";

    /**
     * @return
     */
    private static Web3j getWeb3j() {
        if (web3j == null) {
            // 初始化web3j对象，创建连接
            web3j = Web3j.build(new HttpService(web3jUrl));
        }
        return web3j;
    }

    @Test
    public void TestBalance() {
        try {
            BigInteger balance = SignEngine.getBalance(getWeb3j(), "0x9254e62fbca63769dfd4cc8e23f630f0785610ce");
            System.out.println(balance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            BigInteger balance = SignEngine.getBalance(getWeb3j(), "0x353c02434de6c99f5587b62ae9d6da2bd776daa7");
            System.out.println(balance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void GetName() {
        try {
            Function function = new Function("name", Collections.<Type>emptyList(),
                    Collections.<TypeReference<?>>emptyList());
            String dataHex = FunctionEncoder.encode(function);
            String from = "0x9254e62fbca63769dfd4cc8e23f630f0785610ce";
            String contractAddress = "0x2b84de51225e9613634dd7ed92245f2bc75e3372";
            Transaction transaction = Transaction
                    .createFunctionCallTransaction(from, null, null, null, contractAddress, null, dataHex);
            Request<?, EthCall> callRequest = getWeb3j().ethCall(transaction, DefaultBlockParameter.valueOf("latest"));
            EthCall ethCall = callRequest.sendAsync().get();
            System.out.println(ethCall.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void CallErr() {
        try {
            byte[] hashBytes = HexUtils.toBytes("783037b76f7b089fdf737363ca7387ecc10521698ade6def1b0fa15af9d74d7a");
            Bytes32 hashBytes2=new Bytes32(hashBytes);
            Function function = new Function("getEvidence", Arrays.<Type>asList(hashBytes2),
                    Collections.<TypeReference<?>>emptyList());
            String dataHex = FunctionEncoder.encode(function);
            String from = "0x9254e62fbca63769dfd4cc8e23f630f0785610ce";
            String contractAddress = "0x2b84dE51225e9613634dD7Ed92245F2bC75e3372";
            Transaction transaction = Transaction
                    .createFunctionCallTransaction(from, null, null, null, contractAddress, null, dataHex);
            Request<?, EthCall> callRequest = getWeb3j().ethCall(transaction, DefaultBlockParameter.valueOf("latest"));
            EthCall ethCall = callRequest.sendAsync().get();
            System.out.println(ethCall.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BigInteger ethEstimateGas(Web3j web3j, String from, String to, BigInteger value,
            String contractAddress) throws Exception {
        Address _to = new Address(to);
        Uint256 _value = new Uint256(value);
        Function function = new Function("transfer", Arrays.<Type>asList(_to, _value),
                Collections.<TypeReference<?>>emptyList());
        String dataHex = FunctionEncoder.encode(function);

        Transaction transaction = Transaction
                .createFunctionCallTransaction(from, null, null, null, contractAddress, null, dataHex);
        Request<?, EthEstimateGas> ethEstimateGasRequest = web3j.ethEstimateGas(transaction);
        BigInteger estimateGas = ethEstimateGasRequest.sendAsync().get().getAmountUsed();
        return estimateGas;
    }

    @Test
    public void sign() {
        try {
            String privakey = "0x587ca4a15bc4d239cfba433dda03366506e99ecd2c529216eb3168b3e7806257";
            Credentials credentials = WalletEngine.loadCredentialsByPrivateKey(privakey);
            String from = credentials.getAddress();
            String to = "0x9254E62FBCA63769DFd4Cc8e23f630F0785610CE";

            BigInteger nonce = SignEngine.getNonce(getWeb3j(), from);
            BigInteger gasPrice = BigInteger.valueOf(2000000000);
            BigInteger value = BigInteger.ONE;
            BigInteger gas = BigInteger.valueOf(21000);

            SignRes signRes = SignEngine
                    .sign(true, credentials, to, nonce, gasPrice, gas, value, null, 1125);
            String raw_transaction = signRes.getRaw_transaction();//签名后的内容
            String hash = signRes.getTransaction_hash();//hash
            System.out.println("raw_transaction{}" + raw_transaction);
            System.out.println(hash);
            //=============发送交易===============
            String transactionHash = SignEngine.sendTransaction(getWeb3j(), raw_transaction);
            System.out.println(transactionHash);
            //=============扫描交易===============
            try {
                //交易内容
                com.xwc1125.chain5j.protocol.core.methods.response.Transaction transaction1 = TransactionEngine
                        .getTransaction(getWeb3j(), hash);
                System.out.println("transaction1");
                System.out.println(transaction1);
                //交易收据
                TransactionReceipt transactionReceipt = TransactionEngine.getTransactionReceipt(getWeb3j(), hash);
                System.out.println("transactionReceipt");
                System.out.println(transactionReceipt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sign2() {
        try {
            String privakey = "0x587ca4a15bc4d239cfba433dda03366506e99ecd2c529216eb3168b3e7806257";
            Credentials credentials = WalletEngine.loadCredentialsByPrivateKey(privakey);
            String from = credentials.getAddress();
            String contractAddress = "0x2b84de51225e9613634dd7ed92245f2bc75e3372";
            String to = "0x353C02434dE6c99F5587b62Ae9d6DA2BD776Daa7";

            BigInteger nonce = SignEngine.getNonce(getWeb3j(), from);
            BigInteger gasPrice = SignEngine.getGasPrice(getWeb3j(), false);
            BigInteger value = Convert.toWei("10", Convert.Unit.MWEI).toBigInteger();

            Address _to = new Address(to);
            Uint256 _value = new Uint256(value);
            Function function = new Function("transfer", Arrays.<Type>asList(_to, _value),
                    Collections.<TypeReference<?>>emptyList());
            String dataHex = FunctionEncoder.encode(function);
            BigInteger gas = ethEstimateGas(getWeb3j(), from, contractAddress, value, contractAddress);

            SignRes signRes = SignEngine
                    .sign(false, credentials, contractAddress, nonce, gasPrice, gas, BigInteger.ZERO, function, 1);
            String raw_transaction = signRes.getRaw_transaction();//签名后的内容
            String hash = signRes.getTransaction_hash();//hash

            //=============发送交易===============
            String transactionHash = SignEngine.sendTransaction(getWeb3j(), raw_transaction);
            System.out.println(transactionHash);
            //=============扫描交易===============
            try {
                //交易内容
                com.xwc1125.chain5j.protocol.core.methods.response.Transaction transaction1 = TransactionEngine
                        .getTransaction(getWeb3j(), hash);
                System.out.println("transaction1");
                System.out.println(transaction1);
                //交易收据
                TransactionReceipt transactionReceipt = TransactionEngine.getTransactionReceipt(getWeb3j(), hash);
                System.out.println("transactionReceipt");
                System.out.println(transactionReceipt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
