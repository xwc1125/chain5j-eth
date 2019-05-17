package com.xwc1125.chain5j.tx.response;

import java.io.IOException;
import java.util.Optional;

import com.xwc1125.chain5j.protocol.Web3j;
import com.xwc1125.chain5j.protocol.core.methods.response.TransactionReceipt;
import com.xwc1125.chain5j.protocol.exceptions.TransactionException;
import com.xwc1125.chain5j.protocol.core.methods.response.EthGetTransactionReceipt;

/**
 * Abstraction for managing how we wait for transaction receipts to be generated on the network.
 */
public abstract class TransactionReceiptProcessor {

    private final Web3j web3j;

    public TransactionReceiptProcessor(Web3j web3j) {
        this.web3j = web3j;
    }

    public abstract TransactionReceipt waitForTransactionReceipt(
            String transactionHash)
            throws IOException, TransactionException;

    Optional<TransactionReceipt> sendTransactionReceiptRequest(
            String transactionHash) throws IOException, TransactionException {
        EthGetTransactionReceipt transactionReceipt =
                web3j.ethGetTransactionReceipt(transactionHash).send();
        if (transactionReceipt.hasError()) {
            throw new TransactionException("Error processing request: "
                    + transactionReceipt.getError().getMessage());
        }

        return transactionReceipt.getTransactionReceipt();
    }
}
