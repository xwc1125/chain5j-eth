package com.xwc1125.chain5j.engine.sync;

import com.xwc1125.chain5j.engine.sync.vo.ChainBlockInfo;
import com.xwc1125.chain5j.engine.sync.vo.ChainTransactionInfo;
import com.xwc1125.chain5j.protocol.Web3j;
import com.xwc1125.chain5j.protocol.http.HttpService;

import java.math.BigInteger;

/**
 * @Description:
 * @Author: xwc1125
 * @Date: 2019-06-04 17:58
 * @Copyright Copyright@2019
 */
public class BlockSyncEngineTest {

    public static void main(String[] args) {
        BlockSyncEngine bulid = BlockSyncEngine.bulid(Web3j.build(new HttpService("http://127.0.0.1:8545")), new BlockSyncCallback() {
            @Override
            public void saveBlock(ChainBlockInfo blockInfo) {
                System.out.println(blockInfo.toJsonString());
            }

            @Override
            public void pendingTransaction(ChainTransactionInfo transactionInfo) {
                System.out.println(transactionInfo.toString());
            }

            @Override
            public void logException(Exception e) {
                e.printStackTrace();
            }
        });
        bulid.syncBlock(BigInteger.ZERO);
    }
}
