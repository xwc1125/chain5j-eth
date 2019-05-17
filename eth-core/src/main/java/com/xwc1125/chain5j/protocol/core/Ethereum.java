package com.xwc1125.chain5j.protocol.core;

import java.math.BigInteger;

import com.xwc1125.chain5j.protocol.core.methods.request.ShhFilter;
import com.xwc1125.chain5j.protocol.core.methods.request.ShhPost;
import com.xwc1125.chain5j.protocol.core.methods.request.Transaction;
import com.xwc1125.chain5j.protocol.core.methods.response.*;
import com.xwc1125.chain5j.protocol.core.methods.response.DbGetHex;
import com.xwc1125.chain5j.protocol.core.methods.response.DbPutHex;
import com.xwc1125.chain5j.protocol.core.methods.response.EthBlockNumber;
import com.xwc1125.chain5j.protocol.core.methods.response.EthCoinbase;
import com.xwc1125.chain5j.protocol.core.methods.response.EthCompileLLL;
import com.xwc1125.chain5j.protocol.core.methods.response.EthEstimateGas;
import com.xwc1125.chain5j.protocol.core.methods.response.EthFilter;
import com.xwc1125.chain5j.protocol.core.methods.response.EthGasPrice;
import com.xwc1125.chain5j.protocol.core.methods.response.EthGetBalance;
import com.xwc1125.chain5j.protocol.core.methods.response.EthGetCode;
import com.xwc1125.chain5j.protocol.core.methods.response.EthGetCompilers;
import com.xwc1125.chain5j.protocol.core.methods.response.EthGetStorageAt;
import com.xwc1125.chain5j.protocol.core.methods.response.EthGetTransactionReceipt;
import com.xwc1125.chain5j.protocol.core.methods.response.EthGetUncleCountByBlockHash;
import com.xwc1125.chain5j.protocol.core.methods.response.EthGetUncleCountByBlockNumber;
import com.xwc1125.chain5j.protocol.core.methods.response.EthLog;
import com.xwc1125.chain5j.protocol.core.methods.response.EthMining;
import com.xwc1125.chain5j.protocol.core.methods.response.EthProtocolVersion;
import com.xwc1125.chain5j.protocol.core.methods.response.EthSubmitWork;
import com.xwc1125.chain5j.protocol.core.methods.response.EthTransaction;
import com.xwc1125.chain5j.protocol.core.methods.response.NetListening;
import com.xwc1125.chain5j.protocol.core.methods.response.NetVersion;
import com.xwc1125.chain5j.protocol.core.methods.response.ShhMessages;
import com.xwc1125.chain5j.protocol.core.methods.response.ShhNewFilter;
import com.xwc1125.chain5j.protocol.core.methods.response.ShhNewIdentity;
import com.xwc1125.chain5j.protocol.core.methods.response.Web3ClientVersion;
import com.xwc1125.chain5j.protocol.core.methods.response.Web3Sha3;

/**
 * Core Ethereum JSON-RPC API.
 */
public interface Ethereum {
    Request<?, Web3ClientVersion> web3ClientVersion();

    Request<?, Web3Sha3> web3Sha3(String data);

    Request<?, NetVersion> netVersion();

    Request<?, NetListening> netListening();

    Request<?, NetPeerCount> netPeerCount();

    Request<?, EthProtocolVersion> ethProtocolVersion();

    Request<?, EthCoinbase> ethCoinbase();

    Request<?, EthSyncing> ethSyncing();

    Request<?, EthMining> ethMining();

    Request<?, EthHashrate> ethHashrate();

    Request<?, EthGasPrice> ethGasPrice();

    Request<?, EthAccounts> ethAccounts();

    Request<?, EthBlockNumber> ethBlockNumber();

    Request<?, EthGetBalance> ethGetBalance(
            String address, DefaultBlockParameter defaultBlockParameter);

    Request<?, EthGetStorageAt> ethGetStorageAt(
            String address, BigInteger position,
            DefaultBlockParameter defaultBlockParameter);

    Request<?, EthGetTransactionCount> ethGetTransactionCount(
            String address, DefaultBlockParameter defaultBlockParameter);

    Request<?, EthGetBlockTransactionCountByHash> ethGetBlockTransactionCountByHash(
            String blockHash);

    Request<?, EthGetBlockTransactionCountByNumber> ethGetBlockTransactionCountByNumber(
            DefaultBlockParameter defaultBlockParameter);

    Request<?, EthGetUncleCountByBlockHash> ethGetUncleCountByBlockHash(String blockHash);

    Request<?, EthGetUncleCountByBlockNumber> ethGetUncleCountByBlockNumber(
            DefaultBlockParameter defaultBlockParameter);

    Request<?, EthGetCode> ethGetCode(String address, DefaultBlockParameter defaultBlockParameter);

    Request<?, EthSign> ethSign(String address, String sha3HashOfDataToSign);

    Request<?, EthSendTransaction> ethSendTransaction(
            Transaction transaction);

    Request<?, EthSendTransaction> ethSendRawTransaction(
            String signedTransactionData);

    Request<?, EthCall> ethCall(
            Transaction transaction,
            DefaultBlockParameter defaultBlockParameter);

    Request<?, EthEstimateGas> ethEstimateGas(
            Transaction transaction);

    Request<?, EthBlock> ethGetBlockByHash(String blockHash, boolean returnFullTransactionObjects);

    Request<?, EthBlock> ethGetBlockByNumber(
            DefaultBlockParameter defaultBlockParameter,
            boolean returnFullTransactionObjects);

    Request<?, EthTransaction> ethGetTransactionByHash(String transactionHash);

    Request<?, EthTransaction> ethGetTransactionByBlockHashAndIndex(
            String blockHash, BigInteger transactionIndex);

    Request<?, EthTransaction> ethGetTransactionByBlockNumberAndIndex(
            DefaultBlockParameter defaultBlockParameter, BigInteger transactionIndex);

    Request<?, EthGetTransactionReceipt> ethGetTransactionReceipt(String transactionHash);

    Request<?, EthBlock> ethGetUncleByBlockHashAndIndex(
            String blockHash, BigInteger transactionIndex);

    Request<?, EthBlock> ethGetUncleByBlockNumberAndIndex(
            DefaultBlockParameter defaultBlockParameter, BigInteger transactionIndex);

    Request<?, EthGetCompilers> ethGetCompilers();

    Request<?, EthCompileLLL> ethCompileLLL(String sourceCode);

    Request<?, EthCompileSolidity> ethCompileSolidity(String sourceCode);

    Request<?, EthCompileSerpent> ethCompileSerpent(String sourceCode);

    Request<?, EthFilter> ethNewFilter(com.xwc1125.chain5j.protocol.core.methods.request.EthFilter ethFilter);

    Request<?, EthFilter> ethNewBlockFilter();

    Request<?, EthFilter> ethNewPendingTransactionFilter();

    Request<?, EthUninstallFilter> ethUninstallFilter(BigInteger filterId);

    Request<?, EthLog> ethGetFilterChanges(BigInteger filterId);

    Request<?, EthLog> ethGetFilterLogs(BigInteger filterId);

    Request<?, EthLog> ethGetLogs(com.xwc1125.chain5j.protocol.core.methods.request.EthFilter ethFilter);

    Request<?, EthGetWork> ethGetWork();

    Request<?, EthSubmitWork> ethSubmitWork(String nonce, String headerPowHash, String mixDigest);

    Request<?, EthSubmitHashrate> ethSubmitHashrate(String hashrate, String clientId);

    Request<?, DbPutString> dbPutString(String databaseName, String keyName, String stringToStore);

    Request<?, DbGetString> dbGetString(String databaseName, String keyName);

    Request<?, DbPutHex> dbPutHex(String databaseName, String keyName, String dataToStore);

    Request<?, DbGetHex> dbGetHex(String databaseName, String keyName);

    Request<?, com.xwc1125.chain5j.protocol.core.methods.response.ShhPost> shhPost(
            ShhPost shhPost);

    Request<?, ShhVersion> shhVersion();

    Request<?, ShhNewIdentity> shhNewIdentity();

    Request<?, ShhHasIdentity> shhHasIdentity(String identityAddress);

    Request<?, ShhNewGroup> shhNewGroup();

    Request<?, ShhAddToGroup> shhAddToGroup(String identityAddress);

    Request<?, ShhNewFilter> shhNewFilter(ShhFilter shhFilter);

    Request<?, ShhUninstallFilter> shhUninstallFilter(BigInteger filterId);

    Request<?, ShhMessages> shhGetFilterChanges(BigInteger filterId);

    Request<?, ShhMessages> shhGetMessages(BigInteger filterId);
}
