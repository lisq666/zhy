package com.example.utils.keygen;


public class SerialInfo {
    private int poolSize = 5;
    private String keyName = null;

    public SerialInfo(int poolSize, String keyName) {
        this.poolSize = poolSize;
        this.keyName = keyName;
    }


    public int getPoolSize() {
        return poolSize;
    }

    public String getKeyName() {
        return keyName;
    }


}