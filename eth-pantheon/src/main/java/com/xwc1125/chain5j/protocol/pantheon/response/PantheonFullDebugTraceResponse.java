package com.xwc1125.chain5j.protocol.pantheon.response;

import com.xwc1125.chain5j.protocol.core.Response;

public class PantheonFullDebugTraceResponse extends Response<FullDebugTraceInfo> {
    public FullDebugTraceInfo getFullDebugTraceInfo() {
        return getResult();
    }
}
