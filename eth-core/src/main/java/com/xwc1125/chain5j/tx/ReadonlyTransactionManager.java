package com.xwc1125.chain5j.tx;

import java.io.IOException;
import java.math.BigInteger;

import com.xwc1125.chain5j.protocol.Web3j;
import com.xwc1125.chain5j.protocol.core.methods.response.EthSendTransaction;

/**
 * Transaction manager implementation for read-only operations on smart contracts.
 */
public class ReadonlyTransactionManager extends TransactionManager {

    public ReadonlyTransactionManager(Web3j web3j, String fromAddress) {
        super(web3j, fromAddress);
    }

    @Override
    public EthSendTransaction sendTransaction(
            BigInteger gasPrice, BigInteger gasLimit, String to, String data, BigInteger value)
            throws IOException {
        throw new UnsupportedOperationException(
                "Only read operations are supported by this transaction manager");
    }
}
