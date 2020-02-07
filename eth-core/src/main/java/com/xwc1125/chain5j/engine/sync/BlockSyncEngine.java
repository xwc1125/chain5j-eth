package com.xwc1125.chain5j.engine.sync;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.xwc1125.chain5j.engine.sync.vo.ChainBlockInfo;
import com.xwc1125.chain5j.engine.sync.vo.ChainTransactionInfo;
import com.xwc1125.chain5j.engine.sync.vo.ChainTransactionReceiptInfo;
import com.xwc1125.chain5j.protocol.Web3j;
import com.xwc1125.chain5j.protocol.core.DefaultBlockParameter;
import com.xwc1125.chain5j.protocol.core.Request;
import com.xwc1125.chain5j.protocol.core.methods.response.*;
import com.xwc1125.chain5j.utils.json.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: xwc1125
 * @Date: 2019-06-03 19:29
 * @Copyright Copyright@2019
 */
public class BlockSyncEngine {
    static Logger log = LoggerFactory.getLogger(BlockSyncEngine.class);
    private static final Long WAITING_TIME = 60000L;
    static BlockSyncEngine ins;
    private static Web3j web3j;
    private static BlockSyncCallback callback;


    private BlockSyncEngine() {
    }

    private BlockSyncEngine(Web3j web3j, BlockSyncCallback callback) {
        if (ins == null) {
            BlockSyncEngine.web3j = web3j;
            BlockSyncEngine.callback = callback;
        }
    }

    public static BlockSyncEngine bulid(Web3j web3j, BlockSyncCallback callback) {
        if (ins == null) {
            ins = new BlockSyncEngine(web3j, callback);
        }
        return ins;
    }

    public void syncFlowable() {
        web3j.blockFlowable(true).subscribe(block -> {
            getBlockInfo(block.getBlock());
        });

        web3j.pendingTransactionFlowable().subscribe(tx -> {
            if (callback != null) {
                ChainTransactionInfo transactionInfo = getTransaction(tx, new BigInteger(System.currentTimeMillis() / 1000 + ""));
                callback.pendingTransaction(transactionInfo);
            }
        });

    }

    public void syncBlock(BigInteger currentBlockNumber) {
        getProtocolVersion(web3j);
        if (currentBlockNumber.compareTo(BigInteger.ZERO) < 0) {
            currentBlockNumber = BigInteger.ZERO;
        }
        syncFlowable();

        BigInteger lastBlockNumber = BigInteger.ZERO;
        while (true) {
            try {
                //获取链上最新的
                Request<?, EthBlockNumber> ethBlockNumberRequest = web3j.ethBlockNumber();
                lastBlockNumber = ethBlockNumberRequest.send().getBlockNumber();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                waitTime();
                syncBlock(currentBlockNumber);
                return;
            }
            if (currentBlockNumber.compareTo(lastBlockNumber) > 0) {
                // 当前线程等待1分钟
                waitTime();
                continue;
            }
            try {
                currentBlockNumber = queryBlockCurrentBlockNumber(currentBlockNumber, lastBlockNumber);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                waitTime();
                syncBlock(currentBlockNumber);
                return;
            }
        }
    }

    public void waitTime() {
        try {
            Thread.sleep(WAITING_TIME);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * Description: 查询协议，检测是否能够连接
     * </p>
     *
     * @param
     * @return void
     * @Author: xwc1125
     * @Date: 2019-05-09 10:29:49
     */
    private void getProtocolVersion(Web3j web3j) {
        Request<?, EthProtocolVersion> protocolVersionRequest = web3j.ethProtocolVersion();
        try {
            String protocolVersion = protocolVersionRequest.send().getProtocolVersion();
            System.out.println("protocolVersion:" + protocolVersion);
        } catch (IOException e) {
            log.error("无法连接");
            log.error(e.getMessage(), e);
            waitTime();
            getProtocolVersion(web3j);
            return;
        }
    }

    /**
     * 查询当前和最新块的信息
     *
     * @param currentBlockNumber 当前块
     * @param lastBlockNumber    最新链上块
     */
    private BigInteger queryBlockCurrentBlockNumber(BigInteger currentBlockNumber, BigInteger lastBlockNumber) throws IOException {
        while (true) {
            if (currentBlockNumber.compareTo(lastBlockNumber) > 0) {
                return currentBlockNumber;
            }
            queryBlock(currentBlockNumber);
            //加1
            currentBlockNumber = currentBlockNumber.add(BigInteger.ONE);
        }
    }

    /**
     * 查询区块方法,方法中校验是否分叉
     *
     * @param currentBlockNumber
     * @return
     * @throws IOException
     */
    private void queryBlock(BigInteger currentBlockNumber) throws IOException {
        // 读取当前链上blockNumber为i的区块
        Request<?, EthBlock> ethGetBlockByNumber = web3j.ethGetBlockByNumber(DefaultBlockParameter.valueOf(currentBlockNumber), true);
        EthBlock.Block block = ethGetBlockByNumber.send().getBlock();
        //获取父类的hash
        String parentHash = block.getParentHash();

        // 从数据库中查询数据库中持久化的上一区块hash
        ChainBlockInfo one = null;
        if (callback != null) {
            one = callback.findBlockByNumber(currentBlockNumber.subtract(BigInteger.ONE));
        }
        if (one == null) {
            getBlockInfo(block);
            return;
        }
        String hash = one.getHash();
        //当前块是数据库块中的hash，回退12个块
        if (parentHash.equals(hash)) {
            getBlockInfo(block);
            return;
        } else {
            //出现分叉
            currentBlockNumber = currentBlockNumber.subtract(BigInteger.ONE);
            // 调用数据库中的方法删除上一区块的交易信息和另一个表中的区块信息
            // 删除交易信息
            if (callback != null) {
                callback.deleteTransactionByBlockHash(hash);
                // 删除block表中的分叉区块的数据
                callback.deleteBlockByNumber(currentBlockNumber);
            }
            return;
        }
    }

    /**
     * 保存区块链上的块信息
     *
     * @param block
     */
    void getBlockInfo(EthBlock.Block block) {
        ChainBlockInfo blockInfo = null;
        try {
            blockInfo = JSON.getObjectMapper().readValue(block.toJsonString(), ChainBlockInfo.class);
        } catch (JsonProcessingException e) {
            if (callback != null) {
                callback.logException(e);
            }
        } catch (IOException e) {
            if (callback != null) {
                callback.logException(e);
            }
        }
        if (blockInfo != null) {
            blockInfo.setStatus(StatusType.OK.value);
            List<ChainTransactionInfo> transactionInfoList = getTransactionInfo(block.getTransactions(), block.getTimestamp());
            blockInfo.setTransactionInfos(transactionInfoList);
            if (callback != null) {
                callback.saveBlock(blockInfo);
            }
        }
    }

    /**
     * 保存交易信息
     *
     * @param transactionResultList
     * @param timestamp
     */
    List<ChainTransactionInfo> getTransactionInfo(List<EthBlock.TransactionResult> transactionResultList, BigInteger timestamp) {
        List<ChainTransactionInfo> transactionInfoList = new ArrayList<>();
        if (transactionResultList != null && transactionResultList.size() > 0) {
            ChainTransactionInfo transactionInfo;
            for (EthBlock.TransactionResult transactionResult : transactionResultList) {
                try {
                    Transaction transaction = (Transaction) transactionResult.get();
                    transactionInfo = getTransaction(transaction, timestamp);
                    transactionInfoList.add(transactionInfo);

                } catch (JsonProcessingException e) {
                    if (callback != null) {
                        callback.logException(e);
                    }
                } catch (IOException e) {
                    if (callback != null) {
                        callback.logException(e);
                    }
                }
            }
        }
        return transactionInfoList;
    }

    ChainTransactionInfo getTransaction(Transaction transaction, BigInteger timestamp) throws IOException {
        Gson gson = new Gson();
        ChainTransactionInfo transactionInfo = gson.fromJson(transaction.toString(), ChainTransactionInfo.class);
//        ChainTransactionInfo transactionInfo = JSON.getObjectMapper().readValue(transaction.toString(), ChainTransactionInfo.class);
        transactionInfo.setTimestamp(timestamp.longValue());
        transactionInfo.setIsSuccess(StatusType.UNKNOWN.value);
        transactionInfo.setStatus(StatusType.OK.value);
        ChainTransactionReceiptInfo transactionReceipt = getTransactionReceiptInfo(transactionInfo.getHash());
        if (transactionReceipt != null) {
            transactionInfo.setTransactionReceiptInfo(transactionReceipt);
        }
        return transactionInfo;
    }


    ChainTransactionReceiptInfo getTransactionReceiptInfo(String txid) {
        ChainTransactionReceiptInfo transactionReceiptInfo = null;
        try {
            Request<?, EthGetTransactionReceipt> ethGetTransactionReceiptRequest = web3j.ethGetTransactionReceipt(txid);
            EthGetTransactionReceipt ethGetTransactionReceipt = ethGetTransactionReceiptRequest.send();
            if (ethGetTransactionReceipt.hasError()) {
                log.error(ethGetTransactionReceipt.getError().toString());
                if (callback != null) {
                    callback.logException(new Exception(ethGetTransactionReceipt.getError().toString()));
                }
                return null;
            }
            TransactionReceipt transactionReceipt = ethGetTransactionReceipt.getTransactionReceipt().get();
            if (transactionReceipt != null) {
                transactionReceiptInfo = JSON.getObjectMapper().readValue(transactionReceipt.toString(), ChainTransactionReceiptInfo.class);
            }
        } catch (Exception e) {
            if (callback != null) {
                callback.logException(e);
            }
        }
        return transactionReceiptInfo;
    }

}
