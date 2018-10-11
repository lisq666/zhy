package com.example.vo.parameter.uia;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UiaVp implements Serializable{

    private String curUserId;
    private String curSysCode;
    private String syncType;
    private List<UiaUserInfoVp> dataList;

}
