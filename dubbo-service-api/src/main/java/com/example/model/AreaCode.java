package com.example.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AreaCode implements Serializable {
    private String codeId;
    private String codeName;
    private String addressId;
    private String addressName;
}