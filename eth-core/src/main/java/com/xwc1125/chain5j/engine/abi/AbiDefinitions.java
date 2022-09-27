package com.xwc1125.chain5j.engine.abi;

import com.xwc1125.chain5j.abi.FunctionEncoder;
import com.xwc1125.chain5j.engine.AbiEngine;
import com.xwc1125.chain5j.protocol.core.methods.response.AbiDefinition;
import com.xwc1125.chain5j.utils.Numeric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: xwc1125
 * @Date: 2020/12/12 15:23
 * @Copyright Copyright@2020
 */
public class AbiDefinitions {
    private static final Logger logger = LoggerFactory.getLogger(AbiDefinitions.class);
    private AbiDefinition constructor = null;
    private Map<String, List<AbiDefinition>> functions = new HashMap();
    private Map<String, List<AbiDefinition>> events = new HashMap();
    private Map<String, AbiDefinition> methodIDToFunctions = new HashMap();

    public AbiDefinitions() {
    }

    public AbiDefinition getConstructor() {
        return constructor;
    }

    public void setConstructor(AbiDefinition constructor) {
        this.constructor = constructor;
    }

    public Map<String, List<AbiDefinition>> getFunctions() {
        return functions;
    }

    public void setFunctions(Map<String, List<AbiDefinition>> functions) {
        this.functions = functions;
    }

    public Map<String, List<AbiDefinition>> getEvents() {
        return events;
    }

    public void setEvents(Map<String, List<AbiDefinition>> events) {
        this.events = events;
    }

    public Map<String, AbiDefinition> getMethodIDToFunctions() {
        return methodIDToFunctions;
    }

    public void setMethodIDToFunctions(Map<String, AbiDefinition> methodIDToFunctions) {
        this.methodIDToFunctions = methodIDToFunctions;
    }

    public void addFunction(String name, AbiDefinition abiDefinition) {
        List<AbiDefinition> abiDefinitions = (List) this.functions.get(name);
        if (abiDefinitions == null) {
            this.functions.put(name, new ArrayList());
            abiDefinitions = (List) this.functions.get(name);
        } else {
            logger.info(" overload method ??? name: {}, abiDefinition: {}", name, abiDefinition);
        }

        abiDefinitions.add(abiDefinition);
        String methodSignature = AbiEngine.buildMethodSignature(name, abiDefinition.getInputs());
        String methodId = FunctionEncoder.buildMethodId(methodSignature);
        this.methodIDToFunctions.put(methodId, abiDefinition);
        if (logger.isDebugEnabled()) {
            logger.debug(" name: {}, methodId: {}, methodSignature: {}, abi: {}", new Object[]{name, methodId, methodSignature, abiDefinition});
        }

    }

    public void addEvent(String name, AbiDefinition abiDefinition) {
        this.events.putIfAbsent(name, new ArrayList());
        List<AbiDefinition> abiDefinitions = (List) this.events.get(name);
        abiDefinitions.add(abiDefinition);
        if (logger.isDebugEnabled()) {
            logger.debug(" name: {}, abi: {}", name, abiDefinition);
        }

    }

    public AbiDefinition getABIDefinitionByMethodId(String methodId) {
        return (AbiDefinition) this.methodIDToFunctions.get(Numeric.prependHexPrefix(methodId));
    }
}
