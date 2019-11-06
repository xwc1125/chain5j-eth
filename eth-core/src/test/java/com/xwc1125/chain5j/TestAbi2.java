package com.xwc1125.chain5j;

import java.util.List;

import com.xwc1125.chain5j.abi.FunctionInputDecoder;
import com.xwc1125.chain5j.abi.datatypes.DynamicArray;
import com.xwc1125.chain5j.abi.datatypes.StaticArray;
import com.xwc1125.chain5j.abi.datatypes.Type;
import com.xwc1125.chain5j.engine.AbiEngine;
import com.xwc1125.chain5j.protocol.core.methods.response.AbiDefinition;
import org.junit.Test;

/**
 * @Description:
 * @Author: xwc1125
 * @Date: 2019/11/5 14:35
 * @Copyright Copyright@2019
 */
public class TestAbi2 {

    public static void main(String[] args) {
        String abi = "[\n" +
                "\t{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [],\n" +
                "\t\t\"name\": \"a\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"uint8\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"view\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"c\",\n" +
                "\t\t\t\t\"type\": \"uint8[6]\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"b\",\n" +
                "\t\t\t\t\"type\": \"uint8[]\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"setD\",\n" +
                "\t\t\"outputs\": [],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"nonpayable\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"c\",\n" +
                "\t\t\t\t\"type\": \"uint8[6]\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"setC\",\n" +
                "\t\t\"outputs\": [],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"nonpayable\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"x\",\n" +
                "\t\t\t\t\"type\": \"bytes32\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"bytes32ToString\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"string\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"view\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"data\",\n" +
                "\t\t\t\t\"type\": \"bytes32[]\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"bytes32ArrayToString\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"string\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"view\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"b\",\n" +
                "\t\t\t\t\"type\": \"uint8[]\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"setB\",\n" +
                "\t\t\"outputs\": [],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"nonpayable\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"a\",\n" +
                "\t\t\t\t\"type\": \"uint8\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"b\",\n" +
                "\t\t\t\t\"type\": \"uint8\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"add\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"uint8\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"view\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"a\",\n" +
                "\t\t\t\t\"type\": \"uint8\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"setA\",\n" +
                "\t\t\"outputs\": [],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"nonpayable\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"b\",\n" +
                "\t\t\t\t\"type\": \"bytes32\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"byte32ToString\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"string\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"view\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"a\",\n" +
                "\t\t\t\t\"type\": \"uint8\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"Test\",\n" +
                "\t\t\"outputs\": [],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"nonpayable\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [],\n" +
                "\t\t\"name\": \"div\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"z\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"view\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t}\n" +
                "]";
        try {
            List<AbiDefinition> list1;

//            // setA(uint8)
//            String dataHex="0xc04a87d10000000000000000000000000000000000000000000000000000000000000001";
            // setB(uint8[])
//            String dataHex="0xb0a761b60000000000000000000000000000000000000000000000000000000000000020000000000000000000000000000000000000000000000000000000000000000200000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000002";
            // setC(uint8[6])
//            String dataHex = "0x84bbc98c000000000000000000000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000000000000000020000000000000000000000000000000000000000000000000000000000000003000000000000000000000000000000000000000000000000000000000000000400000000000000000000000000000000000000000000000000000000000000050000000000000000000000000000000000000000000000000000000000000006";
            // setC(uint8[6],uint8[])
//            String dataHex = "0x79d1b2e400000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000002000000000000000000000000000000000000000000000000000000000000000300000000000000000000000000000000000000000000000000000000000000040000000000000000000000000000000000000000000000000000000000000005000000000000000000000000000000000000000000000000000000000000000600000000000000000000000000000000000000000000000000000000000000e00000000000000000000000000000000000000000000000000000000000000003000000000000000000000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000000000000000020000000000000000000000000000000000000000000000000000000000000003";
            // add(uint8,uint8)
            String dataHex = "0xbb4e3f4d00000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000002";

//            SolidityFunctionWrapper testAbi2 = new SolidityFunctionWrapper(false);
//            list1 = testAbi2.loadContractDefinition(abi);
//            FieldSpec fieldSpec = testAbi2.parseInput(dataHex, list1);
//            System.out.println(fieldSpec);

            list1 = AbiEngine.loadContractDefinition(abi);
            FunctionInputDecoder.InputData inputData = AbiEngine.parseInput(dataHex, list1);
            if (inputData != null) {
                System.out.println(inputData.getMethod());
                for (Type type : inputData.getInputParameters()) {
                    System.out.println("========================");
                    if (type instanceof DynamicArray) {
                        DynamicArray array = (DynamicArray) type;
                        List list = array.getValue();
                        for (int i = 0; i < list.size(); i++) {
                            Type type1 = (Type) list.get(i);
                            System.out.println(i + ";" + type1.getTypeAsString() + ";" + type1.getValue());
                        }
                    } else if (type instanceof StaticArray) {
                        StaticArray array = (StaticArray) type;
                        List list = array.getValue();
                        for (int i = 0; i < list.size(); i++) {
                            Type type1 = (Type) list.get(i);
                            System.out.println(i + ";" + type1.getTypeAsString() + ";" + type1.getValue());
                        }
                    } else {
                        System.out.println(type.getTypeAsString() + ";" + type.getValue());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Test2() {
        // 0x84bbc98c
        String methodId = AbiEngine.buildMethodId("setC(uint8[6])");
        System.out.println(methodId);
        // 0xb0a761b6
        String methodId2 = AbiEngine.buildMethodId("setB(uint8[])");
        System.out.println(methodId2);
    }
}
