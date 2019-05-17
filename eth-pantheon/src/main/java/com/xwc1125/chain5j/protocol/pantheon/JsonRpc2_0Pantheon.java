package com.xwc1125.chain5j.protocol.pantheon;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import com.xwc1125.chain5j.protocol.pantheon.response.PantheonEthAccountsMapResponse;
import com.xwc1125.chain5j.protocol.pantheon.response.PantheonFullDebugTraceResponse;
import com.xwc1125.chain5j.protocol.Web3jService;
import com.xwc1125.chain5j.protocol.admin.methods.response.BooleanResponse;
import com.xwc1125.chain5j.protocol.core.DefaultBlockParameter;
import com.xwc1125.chain5j.protocol.core.JsonRpc2_0Web3j;
import com.xwc1125.chain5j.protocol.core.Request;
import com.xwc1125.chain5j.protocol.core.methods.response.EthAccounts;
import com.xwc1125.chain5j.protocol.core.methods.response.MinerStartResponse;

public class JsonRpc2_0Pantheon extends JsonRpc2_0Web3j implements Pantheon {
    public JsonRpc2_0Pantheon(Web3jService web3jService) {
        super(web3jService, "eth");
    }

    public JsonRpc2_0Pantheon(Web3jService web3jService, String clientIdentifier) {
        super(web3jService, clientIdentifier);
    }

    @Override
    public Request<?, MinerStartResponse> minerStart() {
        return new Request<>(
                "miner_start",
                Collections.<String>emptyList(),
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
    public Request<?, BooleanResponse> clicqueDiscard(String address) {
        return new Request<>(
                "clique_discard",
                Arrays.asList(address),
                web3jService,
                BooleanResponse.class);
    }

    @Override
    public Request<?, EthAccounts> clicqueGetSigners(DefaultBlockParameter defaultBlockParameter) {
        return new Request<>(
                "clique_getSigners",
                Arrays.asList(defaultBlockParameter.getValue()),
                web3jService,
                EthAccounts.class);
    }

    @Override
    public Request<?, EthAccounts> clicqueGetSignersAtHash(String blockHash) {
        return new Request<>(
                "clique_getSignersAtHash",
                Arrays.asList(blockHash),
                web3jService,
                EthAccounts.class);
    }

    @Override
    public Request<?, BooleanResponse> cliquePropose(String address, Boolean signerAddition) {
        return new Request<>(
                "clique_propose",
                Arrays.asList(address, signerAddition),
                web3jService,
                BooleanResponse.class);
    }

    @Override
    public Request<?, PantheonEthAccountsMapResponse> cliqueProposals() {
        return new Request<>(
                "clique_proposals",
                Collections.<String>emptyList(),
                web3jService,
                PantheonEthAccountsMapResponse.class);
    }

    @Override
    public Request<?, PantheonFullDebugTraceResponse> debugTraceTransaction(
            String transactionHash, Map<String, Boolean> options) {
        return new Request<>(
                "debug_traceTransaction",
                Arrays.asList(transactionHash, options),
                web3jService,
                PantheonFullDebugTraceResponse.class);
    }
}
