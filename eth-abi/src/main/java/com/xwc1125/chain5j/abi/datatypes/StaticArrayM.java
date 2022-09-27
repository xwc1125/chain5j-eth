package com.xwc1125.chain5j.abi.datatypes;

import java.util.Collections;
import java.util.List;

import com.xwc1125.chain5j.abi.datatypes.generated.AbiTypes;

/**
 * @Description:
 * @Author: xwc1125
 * @Date: 2021/1/15 17:58
 * @Copyright Copyright@2021
 */
public class StaticArrayM<T extends Type> extends Array<T> {

    public static int MAX_SIZE_OF_STATIC_ARRAY = 1024;
    private Integer expectedSize;
    private String typeAsString;

    public StaticArrayM(int len, T type) {
        super(type.getTypeAsString() + "[" + len + "]");
        this.typeAsString = type.getTypeAsString() + "[" + len + "]";
        this.isValid();
    }

    public StaticArrayM(int len, String typeAsString) {
        super(typeAsString + "[" + len + "]");
        this.typeAsString = typeAsString + "[" + len + "]";
        this.isValid();
    }

    @SafeVarargs
    public StaticArrayM(int expectedSize, T... values) {
        super(values[0].getTypeAsString() + "[" + values.length + "]", values);
        this.typeAsString = values[0].getTypeAsString() + "[" + values.length + "]";
        this.expectedSize = expectedSize;
        this.isValid();
    }

    public StaticArrayM(List<T> values) {
        super(((Type) values.get(0)).getTypeAsString() + "[" + values.size() + "]", values);
        this.typeAsString = ((Type) values.get(0)).getTypeAsString() + "[" + values.size() + "]";
        this.isValid();
    }

    public StaticArrayM(int expectedSize, List<T> values) {
        super(((Type) values.get(0)).getTypeAsString() + "[" + values.size() + "]", values);
        this.typeAsString = ((Type) values.get(0)).getTypeAsString() + "[" + values.size() + "]";
        this.expectedSize = expectedSize;
        this.isValid();
    }

    private void isValid() {
        if (this.expectedSize == null && this.value.size() > MAX_SIZE_OF_STATIC_ARRAY) {
            throw new UnsupportedOperationException("Static arrays with a length greater than 1024 are not supported.");
        } else if (this.expectedSize != null && this.value.size() != this.expectedSize) {
            throw new UnsupportedOperationException("Expected array of type [" + this.getClass().getSimpleName() + "] to have [" + this.expectedSize + "] elements.");
        }
    }

    @Override
    public List<T> getValue() {
        // Static arrays cannot be modified
        return Collections.unmodifiableList(value);
    }

    @Override
    public String getTypeAsString() {
        return this.typeAsString;
    }
}
