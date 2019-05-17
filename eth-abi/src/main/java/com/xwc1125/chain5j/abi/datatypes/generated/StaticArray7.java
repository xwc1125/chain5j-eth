package com.xwc1125.chain5j.abi.datatypes.generated;

import java.util.List;
import com.xwc1125.chain5j.abi.datatypes.StaticArray;
import com.xwc1125.chain5j.abi.datatypes.Type;

/**
 * Auto generated code.
 * <p><strong>Do not modifiy!</strong>
 * <p>Please use com.xwc1125.chain5j.codegen.AbiTypesGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 */
public class StaticArray7<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray7(List<T> values) {
        super(7, values);
    }

    @Deprecated
    @SafeVarargs
    public StaticArray7(T... values) {
        super(7, values);
    }

    public StaticArray7(Class<T> type, List<T> values) {
        super(type, 7, values);
    }

    @SafeVarargs
    public StaticArray7(Class<T> type, T... values) {
        super(type, 7, values);
    }
}
