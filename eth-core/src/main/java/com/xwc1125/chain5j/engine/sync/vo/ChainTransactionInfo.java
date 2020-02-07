package com.xwc1125.chain5j.engine.sync.vo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.xwc1125.chain5j.protocol.core.methods.response.Transaction;
import com.xwc1125.chain5j.utils.json.JSON;

public class ChainTransactionInfo extends Transaction {
    /**
     * 状态（-1未知 0成功 1不可用）
     */
    private Integer isSuccess;
    private Long timestamp;

    private ChainTransactionReceiptInfo transactionReceiptInfo;
    /**
     * 状态（0正常 1停用 2删除）
     */
    private Integer status;

    public Integer getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Integer isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ChainTransactionReceiptInfo getTransactionReceiptInfo() {
        return transactionReceiptInfo;
    }

    public void setTransactionReceiptInfo(ChainTransactionReceiptInfo transactionReceiptInfo) {
        this.transactionReceiptInfo = transactionReceiptInfo;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
