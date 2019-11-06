package com.xwc1125.chain5j.engine.abi;

import com.xwc1125.chain5j.abi.TypeReference;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: xwc1125
 * @Date: 2019/11/4 20:23
 * @Copyright Copyright@2019
 */
public class MethodInfo {
    String name;
    String methodId;
    String methodSignature;
    String stateMutability;
    List<TypeReference<?>> inputParameters;
    List<TypeReference<?>> outputParameters;

    public MethodInfo() {
        inputParameters = new ArrayList<>();
        outputParameters = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    public String getMethodSignature() {
        return methodSignature;
    }

    public void setMethodSignature(String methodSignature) {
        this.methodSignature = methodSignature;
    }

    public String getStateMutability() {
        return stateMutability;
    }

    public void setStateMutability(String stateMutability) {
        this.stateMutability = stateMutability;
    }

    public List<TypeReference<?>> getInputParameters() {
        return inputParameters;
    }

    public void setInputParameters(List<TypeReference<?>> inputParameters) {
        this.inputParameters = inputParameters;
    }

    public List<TypeReference<?>> getOutputParameters() {
        return outputParameters;
    }

    public void setOutputParameters(List<TypeReference<?>> outputParameters) {
        this.outputParameters = outputParameters;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"methodId\":\"")
                .append(methodId).append('\"');
        sb.append(",\"methodSignature\":\"")
                .append(methodSignature).append('\"');
        sb.append(",\"stateMutability\":\"")
                .append(stateMutability).append('\"');
        sb.append(",\"inputParameters\":")
                .append(inputParameters);
        sb.append(",\"outputParameters\":")
                .append(outputParameters);
        sb.append('}');
        return sb.toString();
    }
}
