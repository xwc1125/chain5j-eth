package com.xwc1125.chain5j.tx.gas;

import java.math.BigInteger;

import com.xwc1125.chain5j.tx.Contract;
import com.xwc1125.chain5j.tx.ManagedTransaction;

public class DefaultGasProvider extends StaticGasProvider {
    public static final BigInteger GAS_LIMIT = Contract.GAS_LIMIT;
    public static final BigInteger GAS_PRICE = ManagedTransaction.GAS_PRICE;

    public DefaultGasProvider() {
        super(GAS_PRICE, GAS_LIMIT);
    }
}
