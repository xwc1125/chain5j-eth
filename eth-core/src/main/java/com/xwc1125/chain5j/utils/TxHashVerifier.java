package com.xwc1125.chain5j.utils;

public class TxHashVerifier {
    public boolean verify(String hash1, String hash2) {
        return hash1.equals(hash2);
    }
}
