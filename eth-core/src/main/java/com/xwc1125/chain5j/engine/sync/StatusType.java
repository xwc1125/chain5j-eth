package com.xwc1125.chain5j.engine.sync;

/**
 * @Description: 状态类型
 * @Author: xwc1125
 * @Date: 2019-02-21 14:32
 * @Copyright Copyright@2019
 */
public enum StatusType {

    UNKNOWN(-1, "UNKNOWN"),
    /**
     * 可用
     */
    OK(0, "OK"),

    /**
     * 不可用
     */
    DISABLE(1, "DISABLE"),

    /**
     * 删除
     */
    DELETE(2, "DELETE");

    public final int value;
    public final String msg;

    StatusType(int value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public static StatusType parseInt(int value) {
        if (OK.value == value) {
            return OK;
        }
        if (DISABLE.value == value) {
            return DISABLE;
        }
        if (DELETE.value == value) {
            return DELETE;
        }
        return UNKNOWN;
    }
}
