package com.xwc1125.chain5j.engine.sync.vo;

import com.google.gson.Gson;
import com.xwc1125.chain5j.protocol.core.methods.response.EthBlock;

import java.util.List;

public class ChainBlockInfo extends EthBlock.Block {
    private List<ChainTransactionInfo> transactionInfos;
    /**
     * 状态（0正常 1停用 2删除）
     */
    private Integer status;

    public List<ChainTransactionInfo> getTransactionInfos() {
        return transactionInfos;
    }

    public void setTransactionInfos(List<ChainTransactionInfo> transactionInfos) {
        this.transactionInfos = transactionInfos;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
