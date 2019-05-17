package com.xwc1125.chain5j.protocol.pantheon;

import java.util.Map;

import com.xwc1125.chain5j.protocol.pantheon.response.PantheonEthAccountsMapResponse;
import com.xwc1125.chain5j.protocol.pantheon.response.PantheonFullDebugTraceResponse;
import com.xwc1125.chain5j.protocol.Web3j;
import com.xwc1125.chain5j.protocol.Web3jService;
import com.xwc1125.chain5j.protocol.admin.methods.response.BooleanResponse;
import com.xwc1125.chain5j.protocol.core.DefaultBlockParameter;
import com.xwc1125.chain5j.protocol.core.Request;
import com.xwc1125.chain5j.protocol.core.methods.response.EthAccounts;
import com.xwc1125.chain5j.protocol.core.methods.response.MinerStartResponse;

public interface Pantheon extends Web3j {
    static Pantheon build(Web3jService web3jService) {
        return new JsonRpc2_0Pantheon(web3jService);
    }

    Request<?, MinerStartResponse> minerStart();

    Request<?, BooleanResponse> minerStop();

    Request<?, BooleanResponse> clicqueDiscard(String address);

    Request<?, EthAccounts> clicqueGetSigners(DefaultBlockParameter defaultBlockParameter);

    Request<?, EthAccounts> clicqueGetSignersAtHash(String blockHash);

    Request<?, BooleanResponse> cliquePropose(String address, Boolean signerAddition);

    Request<?, PantheonEthAccountsMapResponse> cliqueProposals();

    Request<?, PantheonFullDebugTraceResponse> debugTraceTransaction(
            String transactionHash, Map<String, Boolean> options);
}
