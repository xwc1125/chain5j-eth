package com.xwc1125.chain5j.ens;

/**
 * ENS resolution exception.
 */
public class EnsResolutionException extends RuntimeException {
    public EnsResolutionException(String message) {
        super(message);
    }

    public EnsResolutionException(String message, Throwable cause) {
        super(message, cause);
    }
}
