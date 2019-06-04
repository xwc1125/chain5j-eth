package com.xwc1125.chain5j.engine.sync;

import com.xwc1125.chain5j.engine.sync.vo.ChainBlockInfo;
import com.xwc1125.chain5j.engine.sync.vo.ChainTransactionInfo;

import java.math.BigInteger;

/**
 * @Description:
 * @Author: xwc1125
 * @Date: 2019-06-03 19:39
 * @Copyright Copyright@2019
 */
public abstract class BlockSyncCallback {
    public abstract void saveBlock(ChainBlockInfo blockInfo);

    public void deleteBlockByNumber(BigInteger currentBlockNumber) {

    }

    public void deleteTransactionByBlockHash(String hash) {

    }

    public ChainBlockInfo findBlockByNumber(BigInteger number) {
        return null;
    }

    public abstract void logException(Exception e);

    public void pendingTransaction(ChainTransactionInfo transactionInfo) {
    }
}
