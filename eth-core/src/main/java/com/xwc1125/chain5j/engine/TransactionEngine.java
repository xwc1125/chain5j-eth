package com.xwc1125.chain5j.engine;

import com.xwc1125.chain5j.abi.FunctionEncoder;
import com.xwc1125.chain5j.abi.FunctionReturnDecoder;
import com.xwc1125.chain5j.abi.TypeReference;
import com.xwc1125.chain5j.abi.datatypes.Function;
import com.xwc1125.chain5j.abi.datatypes.Type;
import com.xwc1125.chain5j.abi.datatypes.Utf8String;
import com.xwc1125.chain5j.crypto.Hash;
import com.xwc1125.chain5j.crypto.RawTransaction;
import com.xwc1125.chain5j.crypto.TransactionDecoder;
import com.xwc1125.chain5j.protocol.Web3j;
import com.xwc1125.chain5j.protocol.core.DefaultBlockParameterName;
import com.xwc1125.chain5j.protocol.core.Request;
import com.xwc1125.chain5j.protocol.core.methods.response.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * @Description:
 * @Author: xwc1125
 * @Date: 2019-05-31 17:10
 * @Copyright Copyright@2019
 */
public class TransactionEngine {

    /**
     * 获取交易记录
     *
     * @param hash
     * @throws IOException
     */
    public static Transaction getTransaction(Web3j web3j, String hash) throws Exception {
        Request<?, EthTransaction> ethTransactionRequest = web3j.ethGetTransactionByHash(hash);
        EthTransaction ethTransaction = ethTransactionRequest.send();
        if (ethTransaction.hasError()) {
            throw new Exception(ethTransaction.getError().toString());
        }
        Optional<Transaction> transactionOptional = ethTransaction.getTransaction();
        if (transactionOptional.isPresent()) {
            Transaction transaction = transactionOptional.get();
            return transaction;
        }
        return null;
    }

    /**
     * 获取交易收据【判断交易是否成功，是需要看这个函数】
     *
     * @param hash
     * @return
     * @throws IOException
     */
    public static TransactionReceipt getTransactionReceipt(Web3j web3j, String hash) throws Exception {
        Request<?, EthGetTransactionReceipt> ethGetTransactionReceiptRequest = web3j.ethGetTransactionReceipt(hash);
        EthGetTransactionReceipt ethGetTransactionReceipt = ethGetTransactionReceiptRequest.send();
        if (ethGetTransactionReceipt.hasError()) {
            throw new Exception(ethGetTransactionReceipt.getError().toString());
        }
        Optional<TransactionReceipt> transactionReceiptOptional = ethGetTransactionReceipt.getTransactionReceipt();
        if (transactionReceiptOptional.isPresent()) {
            TransactionReceipt transactionReceipt = transactionReceiptOptional.get();
            return transactionReceipt;
        }
        return null;
    }

    /**
     * 交易解析
     *
     * @param txRaw
     * @return
     * @throws Exception
     */
    public static RawTransaction decodeRawTransaction(String txRaw) {
        return decodeRawTransaction(null, txRaw);
    }

    public static RawTransaction decodeRawTransaction(String icapPrefix, String txRaw) {
        return TransactionDecoder.decode(icapPrefix, txRaw);
    }

    /**
     * 获取交易记录的hash值
     *
     * @param signData
     * @return
     */
    public static String getTxHash(String signData) {
        String sha3 = Hash.sha3(signData);
        return sha3;
    }

    /**
     * Description: 解析合约交易
     * </p>
     *
     * @param web3j
     * @param from
     * @param to
     * @param method
     * @param inputParameters
     * @param outputParameters
     * @return java.util.List<com.xwc1125.chain5j.abi.datatypes.Type>
     * @Author: xwc1125
     * @Date: 2019-05-31 19:21:56
     */
    public static List<Type> decodeTransaction(Web3j web3j, String from, String to, String method, List<Type> inputParameters, List<TypeReference<?>> outputParameters) throws Exception {
        if (outputParameters == null || outputParameters.size() == 0) {
            outputParameters = Collections.<TypeReference<?>>emptyList();
        }
        if (inputParameters == null || inputParameters.size() == 0) {
            inputParameters = Collections.<Type>emptyList();
        }
        Function function = new Function(method, inputParameters, outputParameters);
        String dataHex = FunctionEncoder.encode(function);
        com.xwc1125.chain5j.protocol.core.methods.request.Transaction callTransaction = SignEngine.getCallTransaction(from, to, dataHex);
        CompletableFuture<EthCall> ethCallCompletableFuture = web3j.ethCall(callTransaction, DefaultBlockParameterName.LATEST).sendAsync();
        EthCall ethCall = ethCallCompletableFuture.get();
        if (ethCall.hasError()) {
            throw new Exception(ethCall.getError().toString());
        }
        String response = ethCall.getValue();
        List<Type> decode = new ArrayList<>();
        List<TypeReference<Type>> outputParameters1 = function.getOutputParameters();
        if (outputParameters1 != null && outputParameters1.size() > 0) {
            decode = FunctionReturnDecoder.decode(response, outputParameters1);
        } else {
            Utf8String utf8String = new Utf8String(response);
            decode.add(utf8String);
        }
        return decode;
    }
}
