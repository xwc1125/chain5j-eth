package com.xwc1125.chain5j.protocol.core.filters;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import com.xwc1125.chain5j.protocol.Web3j;
import com.xwc1125.chain5j.protocol.core.Request;
import com.xwc1125.chain5j.protocol.core.methods.response.EthFilter;
import com.xwc1125.chain5j.protocol.core.methods.response.EthLog;
import com.xwc1125.chain5j.protocol.core.methods.response.Log;

/**
 * Log filter handler.
 */
public class LogFilter extends Filter<Log> {

    private final com.xwc1125.chain5j.protocol.core.methods.request.EthFilter ethFilter;

    public LogFilter(
            Web3j web3j, Callback<Log> callback,
            com.xwc1125.chain5j.protocol.core.methods.request.EthFilter ethFilter) {
        super(web3j, callback);
        this.ethFilter = ethFilter;
    }


    @Override
    EthFilter sendRequest() throws IOException {
        return web3j.ethNewFilter(ethFilter).send();
    }

    @Override
    void process(List<EthLog.LogResult> logResults) {
        for (EthLog.LogResult logResult : logResults) {
            if (logResult instanceof EthLog.LogObject) {
                Log log = ((EthLog.LogObject) logResult).get();
                callback.onEvent(log);
            } else {
                throw new FilterException(
                        "Unexpected result type: " + logResult.get() + " required LogObject");
            }
        }
    }

    @Override
    protected Optional<Request<?, EthLog>> getFilterLogs(BigInteger filterId) {
        return Optional.of(web3j.ethGetFilterLogs(filterId));
    }
}
