package com.example.vo.parameter;

import lombok.Data;

import java.io.Serializable;

@Data
public class AreaCodeVp implements Serializable{

    private String codeId;
    private String codeName;
    private String addressName;
    private String addressId;
}
