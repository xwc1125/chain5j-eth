package com.xwc1125.chain5j.abi;

public class TypeMappingException extends RuntimeException {

    public TypeMappingException(Exception e) {
        super(e);
    }

    public TypeMappingException(String message) {
        super(message);
    }

    public TypeMappingException(String message, Exception e) {
        super(message, e);
    }
}
