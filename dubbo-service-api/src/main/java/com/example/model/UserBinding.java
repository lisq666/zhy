package com.example.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "b2c_user_binding")
public class UserBinding implements Serializable{
    @Id
    private String userid;

    private String bindingMobile;

    private String bindingEmail;

    private String bindingQuestionId;

    private String bindingQuestionAnswer;

    private Date createTime;

    private Date updateTime;

}