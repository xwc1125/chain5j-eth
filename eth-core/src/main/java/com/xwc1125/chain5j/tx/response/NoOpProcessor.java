package com.xwc1125.chain5j.tx.response;

import java.io.IOException;

import com.xwc1125.chain5j.protocol.Web3j;
import com.xwc1125.chain5j.protocol.core.methods.response.TransactionReceipt;
import com.xwc1125.chain5j.protocol.exceptions.TransactionException;

/**
 * Return an {@link EmptyTransactionReceipt} receipt back to callers containing only the
 * transaction hash.
 */
public class NoOpProcessor extends TransactionReceiptProcessor {

    public NoOpProcessor(Web3j web3j) {
        super(web3j);
    }

    @Override
    public TransactionReceipt waitForTransactionReceipt(String transactionHash)
            throws IOException, TransactionException {
        return new EmptyTransactionReceipt(transactionHash);
    }
}
