package com.xwc1125.chain5j.protocol.geth;

import java.util.Arrays;
import java.util.Collections;

import com.xwc1125.chain5j.protocol.geth.response.PersonalEcRecover;
import com.xwc1125.chain5j.protocol.geth.response.PersonalImportRawKey;
import io.reactivex.Flowable;

import com.xwc1125.chain5j.protocol.Web3jService;
import com.xwc1125.chain5j.protocol.admin.JsonRpc2_0Admin;
import com.xwc1125.chain5j.protocol.admin.methods.response.BooleanResponse;
import com.xwc1125.chain5j.protocol.admin.methods.response.PersonalSign;
import com.xwc1125.chain5j.protocol.core.Request;
import com.xwc1125.chain5j.protocol.core.methods.response.EthSubscribe;
import com.xwc1125.chain5j.protocol.core.methods.response.MinerStartResponse;
import com.xwc1125.chain5j.protocol.websocket.events.PendingTransactionNotification;
import com.xwc1125.chain5j.protocol.websocket.events.SyncingNotfication;

/**
 * JSON-RPC 2.0 factory implementation for Geth.
 */
public class JsonRpc2_0Geth extends JsonRpc2_0Admin implements Geth {

    public JsonRpc2_0Geth(Web3jService web3jService) {
        super(web3jService, "eth");
    }

    public JsonRpc2_0Geth(Web3jService web3jService, String clientIdentifier) {
        super(web3jService, clientIdentifier);
    }

    @Override
    public Request<?, PersonalImportRawKey> personalImportRawKey(
            String keydata, String password) {
        return new Request<>(
                "personal_importRawKey",
                Arrays.asList(keydata, password),
                web3jService,
                PersonalImportRawKey.class);
    }

    @Override
    public Request<?, BooleanResponse> personalLockAccount(String accountId) {
        return new Request<>(
                "personal_lockAccount",
                Arrays.asList(accountId),
                web3jService,
                BooleanResponse.class);
    }

    @Override
    public Request<?, PersonalSign> personalSign(
            String message, String accountId, String password) {
        return new Request<>(
                "personal_sign",
                Arrays.asList(message, accountId, password),
                web3jService,
                PersonalSign.class);
    }

    @Override
    public Request<?, PersonalEcRecover> personalEcRecover(
            String hexMessage, String signedMessage) {
        return new Request<>(
                "personal_ecRecover",
                Arrays.asList(hexMessage, signedMessage),
                web3jService,
                PersonalEcRecover.class);
    }

    @Override
    public Request<?, MinerStartResponse> minerStart(int threadCount) {
        return new Request<>(
                "miner_start",
                Arrays.asList(threadCount),
                web3jService,
                MinerStartResponse.class);
    }

    @Override
    public Request<?, BooleanResponse> minerStop() {
        return new Request<>(
                "miner_stop",
                Collections.<String>emptyList(),
                web3jService,
                BooleanResponse.class);
    }

    @Override
    public Flowable<PendingTransactionNotification> newPendingTransactionsNotifications() {
        return web3jService.subscribe(
                new Request<>(
                        clientIdentifier + "_subscribe",
                        Arrays.asList("newPendingTransactions"),
                        web3jService,
                        EthSubscribe.class),
                clientIdentifier + "_unsubscribe",
                PendingTransactionNotification.class
        );
    }

    @Override
    public Flowable<SyncingNotfication> syncingStatusNotifications() {
        return web3jService.subscribe(
                new Request<>(
                        clientIdentifier + "_subscribe",
                        Arrays.asList("syncing"),
                        web3jService,
                        EthSubscribe.class),
                clientIdentifier + "_unsubscribe",
                SyncingNotfication.class
        );
    }
}
