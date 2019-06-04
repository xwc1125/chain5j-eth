package com.xwc1125.chain5j.engine.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class InputUtils {

    private static final Logger logger = LoggerFactory.getLogger(InputUtils.class);

    /**
     * 解析input内容
     *
     * @param input
     * @return to:真实接收地址
     * value:含有进制的数
     */
    public static Map<String, Object> resolveInput(String input) {
        Map<String, Object> map = new HashMap<>();
        String subTo = null;
        String subValue = null;
        //	input = "0xa9059cbb0000000000000000000000000f712ff8865b616246b1be86d7bd12da0dbc33d3000000000000000000000000000000000000000000000000000000003b9aca00";
        try {
            subTo = input.substring(34, 74);
            subValue = input.substring(74);
            subTo = "0x" + subTo;
        } catch (Exception e) {
            logger.error("input :" + input + "---substring error: ", e);
            return map;
        }
        try {
            BigInteger bi = new BigInteger(subValue, 16);
            subValue = bi.toString();
        } catch (Exception e) {
            logger.error("input subV to V error: ", e);
        }
        map.put("to", subTo);
        map.put("value", subValue);
        return map;
    }
}
