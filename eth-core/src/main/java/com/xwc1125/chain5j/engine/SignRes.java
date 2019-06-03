package com.xwc1125.chain5j.engine;

public class SignRes {
    private String raw_transaction;
    private String transaction_hash;
    private Long timestamp = System.currentTimeMillis();

    public String getRaw_transaction() {
        return raw_transaction;
    }

    public void setRaw_transaction(String raw_transaction) {
        this.raw_transaction = raw_transaction;
    }

    public String getTransaction_hash() {
        return transaction_hash;
    }

    public void setTransaction_hash(String transaction_hash) {
        this.transaction_hash = transaction_hash;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SignRes{");
        sb.append("raw_transaction='").append(raw_transaction).append('\'');
        sb.append(", transaction_hash='").append(transaction_hash).append('\'');
        sb.append(", timestamp=").append(timestamp);
        sb.append('}');
        return sb.toString();
    }
}
