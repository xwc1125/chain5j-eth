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
public class StaticArray18<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray18(List<T> values) {
        super(18, values);
    }

    @Deprecated
    @SafeVarargs
    public StaticArray18(T... values) {
        super(18, values);
    }

    public StaticArray18(Class<T> type, List<T> values) {
        super(type, 18, values);
    }

    @SafeVarargs
    public StaticArray18(Class<T> type, T... values) {
        super(type, 18, values);
    }
}
