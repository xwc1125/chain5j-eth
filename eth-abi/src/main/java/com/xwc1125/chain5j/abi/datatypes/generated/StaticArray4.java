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
public class StaticArray4<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray4(List<T> values) {
        super(4, values);
    }

    @Deprecated
    @SafeVarargs
    public StaticArray4(T... values) {
        super(4, values);
    }

    public StaticArray4(Class<T> type, List<T> values) {
        super(type, 4, values);
    }

    @SafeVarargs
    public StaticArray4(Class<T> type, T... values) {
        super(type, 4, values);
    }
}
