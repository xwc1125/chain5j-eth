package com.xwc1125.chain5j;

import com.xwc1125.chain5j.abi.FunctionInputDecoder;
import com.xwc1125.chain5j.abi.TypeReference;
import com.xwc1125.chain5j.abi.datatypes.Address;
import com.xwc1125.chain5j.abi.datatypes.Utf8String;
import com.xwc1125.chain5j.abi.datatypes.generated.Uint256;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * @Description:
 * @Author: xwc1125
 * @Date: 2019-06-24 17:06
 * @Copyright Copyright@2019
 */
public class TestInput {
    public static void main(String[] args) {
        try {
            String method = "0xa9059cbb";
            String data = "0000000000000000000000000e623b82da9cc274e82669491d2b68df1d1efbee00000000000000000000000000000000000000000000152d02c7e14af6800000";
            String dataHex = method + data;
            List<TypeReference<?>> inputParameters = new ArrayList<>();
            inputParameters.add(new TypeReference<Address>() {
            });
            inputParameters.add(new TypeReference<Uint256>() {
            });
            FunctionInputDecoder.InputData resultList = FunctionInputDecoder.decode(dataHex, "transfer", inputParameters);
            System.out.println(resultList.getInputParameters().get(0).getValue() + "");
            System.out.println(resultList.getInputParameters().get(1).getValue() + "");
            System.out.println(resultList.getMethod());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test() {
        try {
            String method = "0x11f87b19";
            String data = "0000000000000000000000000654e8516e9bdb11a513c8b629a4a4c6ba1ea0840000000000000000000000000000000000000000000000000000000000000040000000000000000000000000000000000000000000000000000000000000000d7777772e62616964752e636f6d00000000000000000000000000000000000000";
            String dataHex = method + data;
            List<TypeReference<?>> inputParameters = new ArrayList<>();
            inputParameters.add(new TypeReference<Address>() {
            });
            inputParameters.add(new TypeReference<Utf8String>() {
            });
            FunctionInputDecoder.InputData resultList = FunctionInputDecoder.decode(dataHex, "mintNft", inputParameters);
            System.out.println(resultList.getInputParameters().get(0).getValue() + "");
            System.out.println(resultList.getInputParameters().get(1).getValue() + "");
            System.out.println(resultList.getMethod());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
