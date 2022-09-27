package com.xwc1125.chain5j.engine.abi;


import com.xwc1125.chain5j.abi.TypeReference;
import com.xwc1125.chain5j.protocol.core.methods.response.AbiDefinition;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: xwc1125
 * @Date: 2020/12/12 15:45
 * @Copyright Copyright@2020
 */
public class ContractAbiDefinition {
    private AbiDefinition abiDefinition;

    private List<TypeReference<?>> inputParameters;

    public ContractAbiDefinition() {
        inputParameters = new ArrayList<>();
    }

    public AbiDefinition getAbiDefinition() {
        return abiDefinition;
    }

    public void setAbiDefinition(AbiDefinition abiDefinition) {
        this.abiDefinition = abiDefinition;
    }

    public List<TypeReference<?>> getInputParameters() {
        return inputParameters;
    }

    public void setInputParameters(List<TypeReference<?>> inputParameters) {
        this.inputParameters = inputParameters;
    }

    public void addInputParameter(TypeReference<?> typeReference) {
        this.inputParameters.add(typeReference);
    }

}
