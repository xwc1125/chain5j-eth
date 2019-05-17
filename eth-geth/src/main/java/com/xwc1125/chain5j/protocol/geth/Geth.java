package com.xwc1125.chain5j.protocol.geth;

import com.xwc1125.chain5j.protocol.geth.response.PersonalEcRecover;
import com.xwc1125.chain5j.protocol.geth.response.PersonalImportRawKey;
import io.reactivex.Flowable;

import com.xwc1125.chain5j.protocol.Web3jService;
import com.xwc1125.chain5j.protocol.admin.Admin;
import com.xwc1125.chain5j.protocol.admin.methods.response.BooleanResponse;
import com.xwc1125.chain5j.protocol.admin.methods.response.PersonalSign;
import com.xwc1125.chain5j.protocol.core.Request;
import com.xwc1125.chain5j.protocol.core.methods.response.MinerStartResponse;
import com.xwc1125.chain5j.protocol.websocket.events.PendingTransactionNotification;
import com.xwc1125.chain5j.protocol.websocket.events.SyncingNotfication;

/**
 * JSON-RPC Request object building factory for Geth. 
 */
public interface Geth extends Admin {
    static Geth build(Web3jService web3jService) {
        return new JsonRpc2_0Geth(web3jService);
    }
        
    Request<?, PersonalImportRawKey> personalImportRawKey(String keydata, String password);

    Request<?, BooleanResponse> personalLockAccount(String accountId);
    
    Request<?, PersonalSign> personalSign(String message, String accountId, String password);
    
    Request<?, PersonalEcRecover> personalEcRecover(String message, String signiture);

    Request<?, MinerStartResponse> minerStart(int threadCount);

    Request<?, BooleanResponse> minerStop();

    /**
     * Creates an {@link Flowable} instance that emits a notification when a new transaction is
     * added to the pending state and is signed with a key that is available in the node.
     *
     * @return a {@link Flowable} instance that emits a notification when a new transaction is
     *         added to the pending state
     */
    Flowable<PendingTransactionNotification> newPendingTransactionsNotifications();

    /**
     * Creates an {@link Flowable} instance that emits a notification when a node starts or stops
     * syncing.
     * @return a {@link Flowable} instance that emits changes to syncing status
     */
    Flowable<SyncingNotfication> syncingStatusNotifications();

}
