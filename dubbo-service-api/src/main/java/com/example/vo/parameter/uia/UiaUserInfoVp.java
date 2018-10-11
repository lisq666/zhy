package com.example.vo.parameter.uia;

import lombok.Data;

import java.io.Serializable;

@Data
public class UiaUserInfoVp implements Serializable{

    private String loginUserName;
    private String loginMobilePhone;
    private String userType;
    private String mobilePhone;
    private String pwd;
}
