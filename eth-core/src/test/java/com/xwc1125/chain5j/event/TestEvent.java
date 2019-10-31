package com.xwc1125.chain5j.event;

import com.xwc1125.chain5j.abi.EventEncoder;
import com.xwc1125.chain5j.abi.EventValues;
import com.xwc1125.chain5j.abi.TypeReference;
import com.xwc1125.chain5j.abi.datatypes.Address;
import com.xwc1125.chain5j.abi.datatypes.Event;
import com.xwc1125.chain5j.abi.datatypes.Type;
import com.xwc1125.chain5j.abi.datatypes.generated.Uint256;
import com.xwc1125.chain5j.protocol.Web3j;
import com.xwc1125.chain5j.protocol.core.DefaultBlockParameterName;
import com.xwc1125.chain5j.protocol.core.methods.request.EthFilter;
import com.xwc1125.chain5j.protocol.core.methods.response.Log;
import com.xwc1125.chain5j.protocol.http.HttpService;
import com.xwc1125.chain5j.tx.Contract;
import io.reactivex.Flowable;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @Description:
 * @Author: xwc1125
 * @Date: 2019-08-15 14:24
 * @Copyright Copyright@2019
 */
public class TestEvent {

    public static void main(String[] args) {
        Web3j web3j = Web3j.build(new HttpService("http://127.0.0.1:7545"));


        Event event = new Event("Transfer",
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {
                                                },
                        new TypeReference<Address>(true) {
                        }, new TypeReference<Uint256>() {
                        }));
        List<String> list=new ArrayList<>();
        list.add("0x9254E62FBCA63769DFd4Cc8e23f630F0785610CE");
        list.add("0x9254E62FBCA63769DFd4Cc8e23f630F0785610CE");
        EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST,
                DefaultBlockParameterName.LATEST, list);
        filter.addSingleTopic(EventEncoder.encode(event));
        Flowable<TransferEventResponse> flowable = web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(event, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse._from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse._value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public static class TransferEventResponse {
        public Log log;

        public String _from;

        public String _to;

        public BigInteger _value;
    }

    static EventValuesWithLog extractEventParametersWithLog(Event event, Log log) {
        final EventValues eventValues = Contract.staticExtractEventParameters(event, log);
        return (eventValues == null) ? null : new EventValuesWithLog(eventValues, log);
    }

    public static class EventValuesWithLog {
        private final EventValues eventValues;
        private final Log log;

        private EventValuesWithLog(EventValues eventValues, Log log) {
            this.eventValues = eventValues;
            this.log = log;
        }

        public List<Type> getIndexedValues() {
            return eventValues.getIndexedValues();
        }

        public List<Type> getNonIndexedValues() {
            return eventValues.getNonIndexedValues();
        }

        public Log getLog() {
            return log;
        }
    }
}
