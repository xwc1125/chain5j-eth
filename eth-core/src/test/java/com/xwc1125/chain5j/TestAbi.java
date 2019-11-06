package com.xwc1125.chain5j;

import com.xwc1125.chain5j.abi.FunctionInputDecoder;
import com.xwc1125.chain5j.abi.datatypes.Type;
import com.xwc1125.chain5j.engine.AbiEngine;
import com.xwc1125.chain5j.protocol.core.methods.response.AbiDefinition;
import org.junit.Test;

import java.util.List;

/**
 * @Description:
 * @Author: xwc1125
 * @Date: 2019-06-24 18:42
 * @Copyright Copyright@2019
 */
public class TestAbi {
    @Test
    public void testAbi1() {
        {
            String abi = "[\n" +
                    "\t{\n" +
                    "\t\t\"constant\": true,\n" +
                    "\t\t\"inputs\": [],\n" +
                    "\t\t\"name\": \"name\",\n" +
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
                    "\t\t\t\t\"name\": \"_spender\",\n" +
                    "\t\t\t\t\"type\": \"address\"\n" +
                    "\t\t\t},\n" +
                    "\t\t\t{\n" +
                    "\t\t\t\t\"name\": \"_value\",\n" +
                    "\t\t\t\t\"type\": \"uint256\"\n" +
                    "\t\t\t}\n" +
                    "\t\t],\n" +
                    "\t\t\"name\": \"approve\",\n" +
                    "\t\t\"outputs\": [\n" +
                    "\t\t\t{\n" +
                    "\t\t\t\t\"name\": \"success\",\n" +
                    "\t\t\t\t\"type\": \"bool\"\n" +
                    "\t\t\t}\n" +
                    "\t\t],\n" +
                    "\t\t\"payable\": false,\n" +
                    "\t\t\"stateMutability\": \"nonpayable\",\n" +
                    "\t\t\"type\": \"function\"\n" +
                    "\t},\n" +
                    "\t{\n" +
                    "\t\t\"constant\": true,\n" +
                    "\t\t\"inputs\": [],\n" +
                    "\t\t\"name\": \"totalSupply\",\n" +
                    "\t\t\"outputs\": [\n" +
                    "\t\t\t{\n" +
                    "\t\t\t\t\"name\": \"\",\n" +
                    "\t\t\t\t\"type\": \"uint256\"\n" +
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
                    "\t\t\t\t\"name\": \"_from\",\n" +
                    "\t\t\t\t\"type\": \"address\"\n" +
                    "\t\t\t},\n" +
                    "\t\t\t{\n" +
                    "\t\t\t\t\"name\": \"_to\",\n" +
                    "\t\t\t\t\"type\": \"address\"\n" +
                    "\t\t\t},\n" +
                    "\t\t\t{\n" +
                    "\t\t\t\t\"name\": \"_value\",\n" +
                    "\t\t\t\t\"type\": \"uint256\"\n" +
                    "\t\t\t}\n" +
                    "\t\t],\n" +
                    "\t\t\"name\": \"transferFrom\",\n" +
                    "\t\t\"outputs\": [\n" +
                    "\t\t\t{\n" +
                    "\t\t\t\t\"name\": \"success\",\n" +
                    "\t\t\t\t\"type\": \"bool\"\n" +
                    "\t\t\t}\n" +
                    "\t\t],\n" +
                    "\t\t\"payable\": false,\n" +
                    "\t\t\"stateMutability\": \"nonpayable\",\n" +
                    "\t\t\"type\": \"function\"\n" +
                    "\t},\n" +
                    "\t{\n" +
                    "\t\t\"constant\": true,\n" +
                    "\t\t\"inputs\": [],\n" +
                    "\t\t\"name\": \"INITIAL_SUPPLY\",\n" +
                    "\t\t\"outputs\": [\n" +
                    "\t\t\t{\n" +
                    "\t\t\t\t\"name\": \"\",\n" +
                    "\t\t\t\t\"type\": \"uint256\"\n" +
                    "\t\t\t}\n" +
                    "\t\t],\n" +
                    "\t\t\"payable\": false,\n" +
                    "\t\t\"stateMutability\": \"view\",\n" +
                    "\t\t\"type\": \"function\"\n" +
                    "\t},\n" +
                    "\t{\n" +
                    "\t\t\"constant\": true,\n" +
                    "\t\t\"inputs\": [],\n" +
                    "\t\t\"name\": \"decimals\",\n" +
                    "\t\t\"outputs\": [\n" +
                    "\t\t\t{\n" +
                    "\t\t\t\t\"name\": \"\",\n" +
                    "\t\t\t\t\"type\": \"uint256\"\n" +
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
                    "\t\t\t\t\"name\": \"_owner\",\n" +
                    "\t\t\t\t\"type\": \"address\"\n" +
                    "\t\t\t}\n" +
                    "\t\t],\n" +
                    "\t\t\"name\": \"balanceOf\",\n" +
                    "\t\t\"outputs\": [\n" +
                    "\t\t\t{\n" +
                    "\t\t\t\t\"name\": \"balance\",\n" +
                    "\t\t\t\t\"type\": \"uint256\"\n" +
                    "\t\t\t}\n" +
                    "\t\t],\n" +
                    "\t\t\"payable\": false,\n" +
                    "\t\t\"stateMutability\": \"view\",\n" +
                    "\t\t\"type\": \"function\"\n" +
                    "\t},\n" +
                    "\t{\n" +
                    "\t\t\"constant\": true,\n" +
                    "\t\t\"inputs\": [],\n" +
                    "\t\t\"name\": \"owner\",\n" +
                    "\t\t\"outputs\": [\n" +
                    "\t\t\t{\n" +
                    "\t\t\t\t\"name\": \"\",\n" +
                    "\t\t\t\t\"type\": \"address\"\n" +
                    "\t\t\t}\n" +
                    "\t\t],\n" +
                    "\t\t\"payable\": false,\n" +
                    "\t\t\"stateMutability\": \"view\",\n" +
                    "\t\t\"type\": \"function\"\n" +
                    "\t},\n" +
                    "\t{\n" +
                    "\t\t\"constant\": true,\n" +
                    "\t\t\"inputs\": [],\n" +
                    "\t\t\"name\": \"symbol\",\n" +
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
                    "\t\t\"inputs\": [],\n" +
                    "\t\t\"name\": \"nickName\",\n" +
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
                    "\t\t\t\t\"name\": \"_to\",\n" +
                    "\t\t\t\t\"type\": \"address\"\n" +
                    "\t\t\t},\n" +
                    "\t\t\t{\n" +
                    "\t\t\t\t\"name\": \"_value\",\n" +
                    "\t\t\t\t\"type\": \"uint256\"\n" +
                    "\t\t\t}\n" +
                    "\t\t],\n" +
                    "\t\t\"name\": \"transfer\",\n" +
                    "\t\t\"outputs\": [\n" +
                    "\t\t\t{\n" +
                    "\t\t\t\t\"name\": \"success\",\n" +
                    "\t\t\t\t\"type\": \"bool\"\n" +
                    "\t\t\t}\n" +
                    "\t\t],\n" +
                    "\t\t\"payable\": false,\n" +
                    "\t\t\"stateMutability\": \"nonpayable\",\n" +
                    "\t\t\"type\": \"function\"\n" +
                    "\t},\n" +
                    "\t{\n" +
                    "\t\t\"constant\": true,\n" +
                    "\t\t\"inputs\": [\n" +
                    "\t\t\t{\n" +
                    "\t\t\t\t\"name\": \"_owner\",\n" +
                    "\t\t\t\t\"type\": \"address\"\n" +
                    "\t\t\t},\n" +
                    "\t\t\t{\n" +
                    "\t\t\t\t\"name\": \"_spender\",\n" +
                    "\t\t\t\t\"type\": \"address\"\n" +
                    "\t\t\t}\n" +
                    "\t\t],\n" +
                    "\t\t\"name\": \"allowance\",\n" +
                    "\t\t\"outputs\": [\n" +
                    "\t\t\t{\n" +
                    "\t\t\t\t\"name\": \"remaining\",\n" +
                    "\t\t\t\t\"type\": \"uint256\"\n" +
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
                    "\t\t\t\t\"name\": \"newOwner\",\n" +
                    "\t\t\t\t\"type\": \"address\"\n" +
                    "\t\t\t}\n" +
                    "\t\t],\n" +
                    "\t\t\"name\": \"transferOwnership\",\n" +
                    "\t\t\"outputs\": [],\n" +
                    "\t\t\"payable\": false,\n" +
                    "\t\t\"stateMutability\": \"nonpayable\",\n" +
                    "\t\t\"type\": \"function\"\n" +
                    "\t},\n" +
                    "\t{\n" +
                    "\t\t\"inputs\": [],\n" +
                    "\t\t\"payable\": false,\n" +
                    "\t\t\"stateMutability\": \"nonpayable\",\n" +
                    "\t\t\"type\": \"constructor\"\n" +
                    "\t},\n" +
                    "\t{\n" +
                    "\t\t\"payable\": true,\n" +
                    "\t\t\"stateMutability\": \"payable\",\n" +
                    "\t\t\"type\": \"fallback\"\n" +
                    "\t},\n" +
                    "\t{\n" +
                    "\t\t\"anonymous\": false,\n" +
                    "\t\t\"inputs\": [\n" +
                    "\t\t\t{\n" +
                    "\t\t\t\t\"indexed\": true,\n" +
                    "\t\t\t\t\"name\": \"previousOwner\",\n" +
                    "\t\t\t\t\"type\": \"address\"\n" +
                    "\t\t\t},\n" +
                    "\t\t\t{\n" +
                    "\t\t\t\t\"indexed\": true,\n" +
                    "\t\t\t\t\"name\": \"newOwner\",\n" +
                    "\t\t\t\t\"type\": \"address\"\n" +
                    "\t\t\t}\n" +
                    "\t\t],\n" +
                    "\t\t\"name\": \"OwnershipTransferred\",\n" +
                    "\t\t\"type\": \"event\"\n" +
                    "\t},\n" +
                    "\t{\n" +
                    "\t\t\"anonymous\": false,\n" +
                    "\t\t\"inputs\": [\n" +
                    "\t\t\t{\n" +
                    "\t\t\t\t\"indexed\": true,\n" +
                    "\t\t\t\t\"name\": \"from\",\n" +
                    "\t\t\t\t\"type\": \"address\"\n" +
                    "\t\t\t},\n" +
                    "\t\t\t{\n" +
                    "\t\t\t\t\"indexed\": true,\n" +
                    "\t\t\t\t\"name\": \"to\",\n" +
                    "\t\t\t\t\"type\": \"address\"\n" +
                    "\t\t\t},\n" +
                    "\t\t\t{\n" +
                    "\t\t\t\t\"indexed\": false,\n" +
                    "\t\t\t\t\"name\": \"value\",\n" +
                    "\t\t\t\t\"type\": \"uint256\"\n" +
                    "\t\t\t}\n" +
                    "\t\t],\n" +
                    "\t\t\"name\": \"Transfer\",\n" +
                    "\t\t\"type\": \"event\"\n" +
                    "\t},\n" +
                    "\t{\n" +
                    "\t\t\"anonymous\": false,\n" +
                    "\t\t\"inputs\": [\n" +
                    "\t\t\t{\n" +
                    "\t\t\t\t\"indexed\": true,\n" +
                    "\t\t\t\t\"name\": \"owner\",\n" +
                    "\t\t\t\t\"type\": \"address\"\n" +
                    "\t\t\t},\n" +
                    "\t\t\t{\n" +
                    "\t\t\t\t\"indexed\": true,\n" +
                    "\t\t\t\t\"name\": \"spender\",\n" +
                    "\t\t\t\t\"type\": \"address\"\n" +
                    "\t\t\t},\n" +
                    "\t\t\t{\n" +
                    "\t\t\t\t\"indexed\": false,\n" +
                    "\t\t\t\t\"name\": \"value\",\n" +
                    "\t\t\t\t\"type\": \"uint256\"\n" +
                    "\t\t\t}\n" +
                    "\t\t],\n" +
                    "\t\t\"name\": \"Approval\",\n" +
                    "\t\t\"type\": \"event\"\n" +
                    "\t}\n" +
                    "]";
            try {
                List<AbiDefinition> list1 = AbiEngine.loadContractDefinition(abi);
                System.out.println(list1);


                String method = "0xa9059cbb";
                String data = "000000000000000000000000c351f6901da67187ce0f234a9f244c1b47fdf805" + "00000000000000000000000000000000000000000000547dd555d765fb600000";
                String dataHex = method + data;

                FunctionInputDecoder.InputData inputData = AbiEngine.parseInput(dataHex, list1);
                if (inputData != null) {
                    System.out.println(inputData.getMethod());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testAbi2() {
        String abi = "[\n" +
                "\t{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"employeeUuid\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
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
                "\t\t\t\t\"name\": \"uuid\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"isEmployeeRegistered\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"bool\"\n" +
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
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"managers\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
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
                "\t\t\t\t\"name\": \"uuid\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"eaddr\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"registerEmployee\",\n" +
                "\t\t\"outputs\": [],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"nonpayable\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"employees\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
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
                "\t\t\t\t\"name\": \"maddr\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"unsetPlatformManager\",\n" +
                "\t\t\"outputs\": [],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"nonpayable\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"uuid\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"delCompany\",\n" +
                "\t\t\"outputs\": [],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"nonpayable\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"maddr\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"setPlatformManager\",\n" +
                "\t\t\"outputs\": [],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"nonpayable\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [],\n" +
                "\t\t\"name\": \"owner\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
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
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"platformManagers\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"bool\"\n" +
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
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"companyUuid\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
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
                "\t\t\t\t\"name\": \"uuid\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"newaddr\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"updateCompany\",\n" +
                "\t\t\"outputs\": [],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"nonpayable\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"uuid\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"newaddr\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"updateEmployee\",\n" +
                "\t\t\"outputs\": [],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"nonpayable\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"employedBy\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
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
                "\t\t\t\t\"name\": \"uuid\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"delEmployee\",\n" +
                "\t\t\"outputs\": [],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"nonpayable\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"uuid\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"isCompanyRegistered\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"bool\"\n" +
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
                "\t\t\t\t\"name\": \"newOwner\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"transferOwnership\",\n" +
                "\t\t\"outputs\": [],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"nonpayable\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"uuid\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"maddr\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"euuid\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"registerCompany\",\n" +
                "\t\t\"outputs\": [],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"nonpayable\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"inputs\": [],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"nonpayable\",\n" +
                "\t\t\"type\": \"constructor\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"anonymous\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"indexed\": false,\n" +
                "\t\t\t\t\"name\": \"uuid\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"indexed\": false,\n" +
                "\t\t\t\t\"name\": \"maddr\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"RegisterCompany\",\n" +
                "\t\t\"type\": \"event\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"anonymous\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"indexed\": false,\n" +
                "\t\t\t\t\"name\": \"uuid\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"indexed\": false,\n" +
                "\t\t\t\t\"name\": \"newaddr\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"indexed\": false,\n" +
                "\t\t\t\t\"name\": \"oldaddr\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"UpdateCompany\",\n" +
                "\t\t\"type\": \"event\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"anonymous\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"indexed\": false,\n" +
                "\t\t\t\t\"name\": \"uuid\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"DelCompany\",\n" +
                "\t\t\"type\": \"event\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"anonymous\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"indexed\": false,\n" +
                "\t\t\t\t\"name\": \"uuid\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"indexed\": false,\n" +
                "\t\t\t\t\"name\": \"eaddr\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"RegisterEmployee\",\n" +
                "\t\t\"type\": \"event\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"anonymous\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"indexed\": false,\n" +
                "\t\t\t\t\"name\": \"uuid\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"indexed\": false,\n" +
                "\t\t\t\t\"name\": \"newaddr\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"indexed\": false,\n" +
                "\t\t\t\t\"name\": \"oldaddr\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"UpdateEmployee\",\n" +
                "\t\t\"type\": \"event\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"anonymous\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"indexed\": false,\n" +
                "\t\t\t\t\"name\": \"uuid\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"DelEmployee\",\n" +
                "\t\t\"type\": \"event\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"anonymous\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"indexed\": false,\n" +
                "\t\t\t\t\"name\": \"maddress\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"SetPlatformManager\",\n" +
                "\t\t\"type\": \"event\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"anonymous\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"indexed\": false,\n" +
                "\t\t\t\t\"name\": \"maddress\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"UnsetPlatformManager\",\n" +
                "\t\t\"type\": \"event\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"anonymous\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"indexed\": true,\n" +
                "\t\t\t\t\"name\": \"previousOwner\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"indexed\": true,\n" +
                "\t\t\t\t\"name\": \"newOwner\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"OwnershipTransferred\",\n" +
                "\t\t\"type\": \"event\"\n" +
                "\t}\n" +
                "]";
        try {
            List<AbiDefinition> list1 = AbiEngine.loadContractDefinition(abi);
            System.out.println(list1);

            String dataHex = "0xfc5575d20000000000000000000000000000000000000000000000000000000000000001000000000000000000000000353c02434de6c99f5587b62ae9d6da2bd776daa70000000000000000000000000000000000000000000000000000000000000001";

            FunctionInputDecoder.InputData inputData = AbiEngine.parseInput(dataHex, list1);
            if (inputData != null) {
                System.out.println(inputData.getMethod());
                for (Type type : inputData.getInputParameters()) {
                    System.out.println(type.getTypeAsString() + ";" + type.getValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAbi3() {
        String abi = "[\n" +
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
            List<AbiDefinition> list1 = AbiEngine.loadContractDefinition(abi);
            System.out.println(list1);

            // setA(uint8)
            String dataHex="0xc04a87d10000000000000000000000000000000000000000000000000000000000000001";
            // setB(uint8[])
//            String dataHex="0xb0a761b60000000000000000000000000000000000000000000000000000000000000020000000000000000000000000000000000000000000000000000000000000000200000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000002";
//            // setC(uint8[6])
//            String dataHex = "0x84bbc98c000000000000000000000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000000000000000020000000000000000000000000000000000000000000000000000000000000003000000000000000000000000000000000000000000000000000000000000000400000000000000000000000000000000000000000000000000000000000000050000000000000000000000000000000000000000000000000000000000000006";

            FunctionInputDecoder.InputData inputData = AbiEngine.parseInput(dataHex, list1);
            if (inputData != null) {
                System.out.println(inputData.getMethod());
                for (Type type : inputData.getInputParameters()) {
                    System.out.println(type.getTypeAsString() + ";" + type.getValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
