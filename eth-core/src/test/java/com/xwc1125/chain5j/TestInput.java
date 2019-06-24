package com.xwc1125.chain5j;

import com.xwc1125.chain5j.abi.FunctionInputDecoder;
import com.xwc1125.chain5j.abi.TypeReference;
import com.xwc1125.chain5j.abi.datatypes.Address;
import com.xwc1125.chain5j.abi.datatypes.generated.Uint256;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: xwc1125
 * @Date: 2019-06-24 17:06
 * @Copyright Copyright@2019
 */
public class TestInput {
    public static void main(String[] args) {
        String method = "0xa9059cbb";
        String data = "000000000000000000000000c351f6901da67187ce0f234a9f244c1b47fdf805" + "00000000000000000000000000000000000000000000547dd555d765fb600000";
        String dataHex = method + data;
        List<TypeReference<?>> inputParameters = new ArrayList<>();
        inputParameters.add(new TypeReference<Address>() {
        });
        inputParameters.add(new TypeReference<Uint256>() {
        });
        FunctionInputDecoder.InputData resultList = FunctionInputDecoder.decode(dataHex, "transfer1", inputParameters);
        System.out.println(resultList.getInputParameters().get(0).getValue()+"");
        System.out.println(resultList.getInputParameters().get(1).getValue()+"");
        System.out.println(resultList.getMethod());
    }
}
