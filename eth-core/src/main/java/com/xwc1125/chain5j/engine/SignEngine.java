package com.xwc1125.chain5j.engine;

import com.xwc1125.chain5j.abi.FunctionEncoder;
import com.xwc1125.chain5j.abi.TypeReference;
import com.xwc1125.chain5j.abi.datatypes.Address;
import com.xwc1125.chain5j.abi.datatypes.Function;
import com.xwc1125.chain5j.abi.datatypes.Type;
import com.xwc1125.chain5j.abi.datatypes.generated.Uint256;
import com.xwc1125.chain5j.crypto.*;
import com.xwc1125.chain5j.protocol.Web3j;
import com.xwc1125.chain5j.protocol.core.DefaultBlockParameter;
import com.xwc1125.chain5j.protocol.core.DefaultBlockParameterName;
import com.xwc1125.chain5j.protocol.core.Request;
import com.xwc1125.chain5j.protocol.core.methods.request.Transaction;
import com.xwc1125.chain5j.protocol.core.methods.response.*;
import com.xwc1125.chain5j.protocol.http.HttpService;
import com.xwc1125.chain5j.tx.Transfer;
import com.xwc1125.chain5j.utils.Convert;
import com.xwc1125.chain5j.utils.Numeric;
import com.xwc1125.chain5j.utils.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SignatureException;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;

import static com.xwc1125.chain5j.tx.gas.DefaultGasProvider.GAS_LIMIT;
import static com.xwc1125.chain5j.tx.gas.DefaultGasProvider.GAS_PRICE;

/**
 * @Description:
 * @Author: xwc1125
 * @Date: 2019-05-31 17:12
 * @Copyright Copyright@2019
 */
public class SignEngine {

    /**
     * @param web3jUrl
     * @return
     */
    public static Web3j getWeb3j(String web3jUrl, String clientIdentifier) {
        Web3j web3j;
        if (StringUtils.isEmpty(clientIdentifier)) {
            web3j = Web3j.build(new HttpService(web3jUrl));
        } else {
            web3j = Web3j.build(new HttpService(web3jUrl), clientIdentifier);
        }
        return web3j;
    }


    /**
     * 获取余额
     *
     * @param from
     * @return
     * @throws Exception
     */
    public static BigInteger getBalance(Web3j web3j, String from) throws Exception {
        CompletableFuture<EthGetBalance> future = web3j.ethGetBalance(from, DefaultBlockParameter.valueOf("latest")).sendAsync();
        EthGetBalance ethGetBalance = future.get();
        if (ethGetBalance.hasError()) {
            throw new Exception(ethGetBalance.getError().toString());
        }
        return ethGetBalance.getBalance();
    }

    /**
     * 获取合约余额
     *
     * @param address
     * @param contractAddress
     * @return
     * @throws Exception
     */
    public static BigInteger getTokenBalance(Web3j web3j, String address, String contractAddress) throws Exception {
        Address addressA = new Address(address);
        Function function = new Function("balanceOf", Arrays.<Type>asList(addressA), Collections.<TypeReference<?>>emptyList());
        Transaction transaction = getCallTransaction(address, contractAddress, function);
        EthCall ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).sendAsync().get();
        if (ethCall.hasError()) {
            throw new Exception(ethCall.getError().toString());
        }
        String value = ethCall.getValue();
        if (value.equals("0x")) {
            value = "0x0";
        }
        return Numeric.toBigInt(value);
    }

    /**
     * 获取gas
     *
     * @param from
     * @param to
     * @param contractAddress
     * @return
     * @throws Exception
     */
    public static BigInteger getGas(Web3j web3j, String from, String to, BigInteger value, String contractAddress) throws Exception {
        if (contractAddress == null || contractAddress.equals("")) {
            return new BigInteger("21000");
        }
        Address _to = new Address(to);
        Uint256 _value = new Uint256(value);
        Function function = new Function("transfer", Arrays.<Type>asList(_to, _value), Collections.<TypeReference<?>>emptyList());
        return getGas(web3j, from, contractAddress, function);
    }

    public static BigInteger getGas(Web3j web3j, String from, String to, Function function) throws Exception {
        String dataHex = "0x0";
        if (function != null) {
            dataHex = FunctionEncoder.encode(function);
        }
        return getGas(web3j, from, to, dataHex);
    }

    public static BigInteger getGas(Web3j web3j, String from, String to, String dataHex) throws Exception {
        if (to == null || to.equals("")) {
            return new BigInteger("21000");
        }
        Transaction transaction = new Transaction(from, (BigInteger) null, (BigInteger) null, (BigInteger) null, to, BigInteger.ONE, null);
        Request<?, EthEstimateGas> ethEstimateGasRequest = web3j.ethEstimateGas(transaction);
        CompletableFuture<EthEstimateGas> ethEstimateGasCompletableFuture = ethEstimateGasRequest.sendAsync();
        EthEstimateGas ethEstimateGas = ethEstimateGasCompletableFuture.get();
        if (ethEstimateGas.hasError()) {
            throw new Exception(ethEstimateGas.getError().toString());
        }
        BigInteger estimateGas = ethEstimateGas.getAmountUsed();
        return estimateGas;
    }

    public static Transaction getCallTransaction(String from, String to, Function function) {
        String dataHex = "0x0";
        if (function != null) {
            dataHex = FunctionEncoder.encode(function);
        }
        return getCallTransaction(from, to, dataHex);
    }

    public static Transaction getCallTransaction(String from, String to, String dataHex) {
        return Transaction.createEthCallTransaction(from, to, dataHex);
    }

    /**
     * 获取gasPrice
     *
     * @param isNon
     * @return
     * @throws Exception
     */
    public static BigInteger getGasPrice(Web3j web3j, boolean isNon) throws Exception {
        if (isNon) {
            return BigInteger.ZERO;
        }
        Request<?, EthGasPrice> ethGasPriceRequest = web3j.ethGasPrice();
        EthGasPrice ethGasPrice = ethGasPriceRequest.sendAsync().get();
        if (ethGasPrice.hasError()) {
            throw new Exception(ethGasPrice.getError().toString());
        }
        BigInteger gasPrice = ethGasPrice.getGasPrice();
        return gasPrice;
    }

    /**
     * 获取Nonce
     *
     * @param from
     * @return
     * @throws Exception
     */
    public static BigInteger getNonce(Web3j web3j, String from) throws Exception {
        //getNonce
        web3j.ethGetTransactionCount(
                from, DefaultBlockParameterName.LATEST).sendAsync();
        EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
                from, DefaultBlockParameterName.LATEST).sendAsync().get();
        if (ethGetTransactionCount.hasError()) {
            throw new Exception(ethGetTransactionCount.getError().toString());
        }
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
        return nonce;
    }

    /**
     * 获取Nonce
     *
     * @param from
     * @param defaultBlockParameter DefaultBlockParameterName
     *                              EARLIEST("earliest"),
     *                              LATEST("latest"),
     *                              PENDING("pending");
     * @return
     * @throws Exception
     */
    public static BigInteger getNonce(Web3j web3j, String from, DefaultBlockParameter defaultBlockParameter) throws Exception {
        //getNonce
        EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
                from, defaultBlockParameter).sendAsync().get();
        if (ethGetTransactionCount.hasError()) {
            throw new Exception(ethGetTransactionCount.getError().toString());
        }
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
        return nonce;
    }


    //==================签名==================

    /**
     * 签名交易
     *
     * @param credentials 证书
     * @param nonce
     * @param gasPrice
     * @param gas
     * @param to
     * @param value
     * @param fun
     * @return
     */
    public static SignRes sign(Credentials credentials, String to, BigInteger nonce, BigInteger gasPrice, BigInteger gas,
                               BigInteger value, Function fun) {
        return sign(false, credentials, to, nonce, gasPrice, gas, value, fun, (byte) 1);
    }

    /**
     * @param credentials
     * @param nonce
     * @param gasPrice
     * @param gas
     * @param to
     * @param value
     * @param fun
     * @param chainId
     * @return
     */
    public static SignRes signEIP155(Credentials credentials, String to, BigInteger nonce, BigInteger gasPrice, BigInteger gas,
                                     BigInteger value, Function fun, int chainId) {
        return sign(true, credentials, to, nonce, gasPrice, gas, value, fun, chainId);
    }

    /**
     * @param isEIP155
     * @param credentials
     * @param nonce
     * @param gasPrice
     * @param gas
     * @param to
     * @param value
     * @param fun
     * @param chainId
     * @return
     */
    public static SignRes sign(boolean isEIP155, Credentials credentials, String to, BigInteger nonce, BigInteger gasPrice,
                               BigInteger gas, BigInteger value, Function fun, int chainId) {
        RawTransaction rawTransaction;
        if (fun != null) {
            rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gas, to, value, FunctionEncoder.encode(fun));
        } else {
            rawTransaction = RawTransaction.createEtherTransaction(nonce, gasPrice, gas, to, value);
        }
        String ret;

        if (isEIP155) {
            ret = signEIP155(rawTransaction, credentials, chainId);
        } else {
            ret = sign(rawTransaction, credentials);
        }
        SignRes res = new SignRes();
        res.setRaw_transaction(ret);
        res.setTransaction_hash(Hash.sha3(ret));
        if (fun != null) {
            String data = FunctionEncoder.encode(fun);
            System.out.println(String.format("sign nonce:%s; gasPrice:%s; gasLimit:%s; to:%s; value:%s; funName:%s; txHash:%s; data:%s",
                    nonce.toString(), gasPrice.toString(), gas.toString(), to, value.toString(), fun.getName(), res.getTransaction_hash(), data));
        } else {
            System.out.println(String.format("sign nonce:%s; gasPrice:%s; gasLimit:%s; to:%s; value:%s; funName:%s; txHash:%s; data:%s",
                    nonce.toString(), gasPrice.toString(), gas.toString(), to, value.toString(), null, res.getTransaction_hash(), null));
        }
        return res;
    }

    /**
     * @param isEIP155
     * @param credentials
     * @param to
     * @param nonce
     * @param gasPrice
     * @param gas
     * @param value
     * @param data        如果无，请填null
     * @param chainId
     * @return
     */
    public static SignRes sign(boolean isEIP155, Credentials credentials, String to, BigInteger nonce, BigInteger gasPrice,
                               BigInteger gas, BigInteger value, String data, int chainId) {
        RawTransaction rawTransaction;
        if (StringUtils.isNotEmpty(data)) {
            rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gas, to, value, data);
        } else {
            rawTransaction = RawTransaction.createEtherTransaction(nonce, gasPrice, gas, to, value);
        }
        String ret;

        if (isEIP155) {
            ret = signEIP155(rawTransaction, credentials, chainId);
        } else {
            ret = sign(rawTransaction, credentials);
        }
        SignRes res = new SignRes();
        res.setRaw_transaction(ret);
        res.setTransaction_hash(Hash.sha3(ret));
        System.out.println(String.format("sign nonce:%s; gasPrice:%s; gasLimit:%s; to:%s; value:%s; funName:%s; txHash:%s; data:%s",
                nonce.toString(), gasPrice.toString(), gas.toString(), to, value.toString(), "unknow", res.getTransaction_hash(), data));
        return res;
    }

    /**
     * 签名EIP155
     *
     * @param rawTransaction
     * @param credentials
     * @param chainId
     * @return
     */
    public static String signEIP155(RawTransaction rawTransaction, Credentials credentials, int chainId) {
        byte[] signedMessage;
        signedMessage = TransactionEncoder.signMessage(rawTransaction, chainId, credentials);
        return Numeric.toHexString(signedMessage);
    }

    /**
     * 非EIP155的交易
     *
     * @param rawTransaction
     * @param credentials
     * @return
     */
    public static String sign(RawTransaction rawTransaction, Credentials credentials) {
        byte[] signedMessage;
        signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        return Numeric.toHexString(signedMessage);
    }

    //==================发送交易===============

    /**
     * 签名交易
     *
     * @param credentials     签名（签名不能为空）
     * @param to              收款地址（不能为空）
     * @param contractAddress 合约地址。（如果是以太坊自定义合约类型，可为空）
     * @param nonce           nonce值
     * @param gasPrice        单价（位）(为null则使用系统内部默认值)
     * @param gas             数量(为null则使用系统内部默认值)
     * @param value           转账金额
     * @return
     * @throws Exception
     */
    public static SignRes signTransfer(Credentials credentials,
                                       String to, String contractAddress, BigInteger nonce, BigInteger gasPrice,
                                       BigInteger gas, BigInteger value) throws Exception {
        if (StringUtils.isEmpty(to)) {
            throw new Exception("the toAddress should not be null");
        }
        if (credentials == null) {
            throw new Exception("the credentials should not be null");
        }
        SignRes signData;
        BigInteger gasLimit;
        if (StringUtils.isEmpty(contractAddress)) {
            if (gasPrice == null) {
                gasPrice = GAS_PRICE;
            }
            if (gas == null || gas.compareTo(BigInteger.ZERO) == 0) {
                gasLimit = GAS_LIMIT;
            } else {
                gasLimit = gas;
            }
            signData = signEther(credentials, nonce, gasPrice, gasLimit, to, value);
        } else {
            Address _to = new Address(to);
            Uint256 _value = new Uint256(value);
            Function function = new Function("transfer", Arrays.<Type>asList(_to, _value), Collections.<TypeReference<?>>emptyList());
            String dataHex = FunctionEncoder.encode(function);
            if (gasPrice == null) {
                gasPrice = GAS_PRICE;
            }
            if (gas == null || gas.compareTo(BigInteger.ZERO) == 0) {
                gasLimit = GAS_LIMIT;
            } else {
                gasLimit = gas;
            }
            //合约的valus一定只能是0
            signData = signContract(credentials, nonce, gasPrice, gasLimit, contractAddress, BigInteger.ZERO, dataHex);
        }
        return signData;
    }

    /**
     * 以太坊签名
     *
     * @param credentials
     * @param nonce
     * @param gasPrice
     * @param gasLimit
     * @param to
     * @param value
     * @return
     */
    private static SignRes signEther(Credentials credentials, BigInteger nonce, BigInteger gasPrice, BigInteger gasLimit,
                                     String to, BigInteger value) {
        RawTransaction rawTransaction = RawTransaction.createEtherTransaction(
                nonce, gasPrice, gasLimit, to, value);
        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String hexValue = Numeric.toHexString(signedMessage);
        SignRes res = new SignRes();
        res.setRaw_transaction(hexValue);
        res.setTransaction_hash(Hash.sha3(hexValue));
        return res;
    }

    /**
     * 合约类型签名
     *
     * @param credentials
     * @param nonce
     * @param gasPrice
     * @param gasLimit
     * @param contractAddress
     * @param value
     * @param data
     * @return
     */
    private static SignRes signContract(Credentials credentials, BigInteger nonce, BigInteger gasPrice, BigInteger gasLimit,
                                        String contractAddress, BigInteger value, String data) {
        RawTransaction rawTransaction = RawTransaction.createTransaction(
                nonce, gasPrice, gasLimit, contractAddress, value, data);
        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String hexValue = Numeric.toHexString(signedMessage);
        SignRes res = new SignRes();
        res.setRaw_transaction(hexValue);
        res.setTransaction_hash(Hash.sha3(hexValue));
        return res;
    }


    /**
     * 发送交易
     *
     * @param tx
     * @return
     * @throws Exception
     */
    public static String sendTransaction(Web3j web3j, String tx) throws Exception {
        Request<?, EthSendTransaction> ethSendTransactionRequest = web3j.ethSendRawTransaction(tx);
        EthSendTransaction ethSendTransaction = ethSendTransactionRequest.send();
        if (ethSendTransaction.hasError()) {
            throw new Exception(ethSendTransaction.getError().toString());
        }
        String transactionHash = ethSendTransaction.getTransactionHash();
        if (ethSendTransaction.getError() != null) {
            throw new Exception(ethSendTransaction.getError().getMessage());
        }
        return transactionHash;
    }

    /**
     * 发送交易
     *
     * @param credentials
     * @param toAddress
     * @param value
     * @param unit
     * @return
     * @throws Exception
     */
    public static String sendTransaction(Web3j web3j, Credentials credentials, String toAddress, BigDecimal value, Convert.Unit unit) throws Exception {
        TransactionReceipt transactionReceipt = Transfer.sendFunds(
                web3j, credentials, toAddress,
                value, unit).send();
        String transactionHash = transactionReceipt.getTransactionHash();
        return transactionHash;
    }

    //================签名内容=============

    /**
     * 签名信息
     *
     * @param msg
     * @param ecKeyPair
     * @return
     */
    public static Sign.SignatureData signMsg(String msg, ECKeyPair ecKeyPair) {
        Sign.SignatureData signatureData = Sign.signMessage(msg.getBytes(), ecKeyPair);
        return signatureData;
    }

    /**
     * 恢复地址
     *
     * @param msg
     * @param signatureData
     * @return 返回地址
     */
    public static String recoverAddress(String msg, Sign.SignatureData signatureData) {
        BigInteger publick = null;
        try {
            publick = Sign.signedMessageToKey(msg.getBytes(), signatureData);
        } catch (SignatureException e) {
        }
        String address = Keys.getAddress(publick);
        return Numeric.prependHexPrefix(address);
    }
}
