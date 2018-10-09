package com.example.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "b2c_auth")
public class Auth implements Serializable{
    @Id
    private String userid;

    private String loginId;

    private String password;

    private String userType;

    private String ctpfType;

    private String isLock;

    private String isEnable;

    private Date pwdValidTime;

    private String ispassmodified;

 }