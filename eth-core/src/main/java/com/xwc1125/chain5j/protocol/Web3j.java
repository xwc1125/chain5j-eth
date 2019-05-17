package com.xwc1125.chain5j.protocol;

import java.util.concurrent.ScheduledExecutorService;

import com.xwc1125.chain5j.protocol.core.Ethereum;
import com.xwc1125.chain5j.protocol.core.JsonRpc2_0Web3j;
import com.xwc1125.chain5j.protocol.rx.Web3jRx;

/**
 * JSON-RPC Request object building factory.
 */
public interface Web3j extends Ethereum, Web3jRx {

    static Web3j build(Web3jService web3jService) {
        return new JsonRpc2_0Web3j(web3jService, "eth");
    }

    /**
     * Construct a new Web3j instance.
     *
     * @param web3jService web3j service instance - i.e. HTTP or IPC
     * @return new Web3j instance
     */
    static Web3j build(Web3jService web3jService, String clientIdentifier) {
        return new JsonRpc2_0Web3j(web3jService, clientIdentifier);
    }

    /**
     * Construct a new Web3j instance.
     *
     * @param web3jService             web3j service instance - i.e. HTTP or IPC
     * @param pollingInterval          polling interval for responses from network nodes
     * @param scheduledExecutorService executor service to use for scheduled tasks.
     *                                 <strong>You are responsible for terminating this thread
     *                                 pool</strong>
     * @return new Web3j instance
     */
    static Web3j build(
            Web3jService web3jService, String clientIdentifier, long pollingInterval,
            ScheduledExecutorService scheduledExecutorService) {
        return new JsonRpc2_0Web3j(web3jService, clientIdentifier, pollingInterval, scheduledExecutorService);
    }

    /**
     * Shutdowns a Web3j instance and closes opened resources.
     */
    void shutdown();
}
