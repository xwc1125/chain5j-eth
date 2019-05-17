package com.xwc1125.chain5j.tx.response;

import com.xwc1125.chain5j.protocol.core.methods.response.TransactionReceipt;

/**
 * Transaction receipt processor callback.
 */
public interface Callback {
    void accept(TransactionReceipt transactionReceipt);

    void exception(Exception exception);
}
