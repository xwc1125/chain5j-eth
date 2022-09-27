package com.xwc1125.chain5j.engine.abi;

import com.xwc1125.chain5j.abi.TypeReference;
import com.xwc1125.chain5j.abi.datatypes.Type;

import java.util.List;

/**
 * @Description:
 * @Author: xwc1125
 * @Date: 2020/12/14 14:18
 * @Copyright Copyright@2020
 */
public class MethodParams {

    private String method;
    private List<Type> inputParameters;
    private List<TypeReference<?>> outputParameters;

    public MethodParams() {
    }

    public MethodParams(String method, List<Type> inputParameters, List<TypeReference<?>> outputParameters) {
        this.method = method;
        this.inputParameters = inputParameters;
        this.outputParameters = outputParameters;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<Type> getInputParameters() {
        return inputParameters;
    }

    public void setInputParameters(List<Type> inputParameters) {
        this.inputParameters = inputParameters;
    }

    public List<TypeReference<?>> getOutputParameters() {
        return outputParameters;
    }

    public void setOutputParameters(List<TypeReference<?>> outputParameters) {
        this.outputParameters = outputParameters;
    }
}
