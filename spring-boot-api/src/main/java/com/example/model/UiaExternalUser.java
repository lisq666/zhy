package com.example.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@Data
@Table(name = "b2c_uia_external_user")
public class UiaExternalUser implements Serializable{
    @Id
    private String id;

    private String uiaId;

    private String userId;

    private String loginId;

    private String mobile;

    private String email;

    private String password;

    private String userType;

    private String mallid;

    private Date createdtime;

    private Date updatetime;

    private String status;

    private String toUiaPhone;

    private String toUiaPwd;

    private String toUiaEmail;

    private String toUiaLoginid;

    private String handlerId;


}