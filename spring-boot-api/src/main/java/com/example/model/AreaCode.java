package com.example.model;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "b2c_areacode")
public class AreaCode implements Serializable {
    private String codeId;
    private String codeName;
    private String addressId;
    private String addressName;
}