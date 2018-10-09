package com.example.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "b2c_mall_user_info")
public class MallUserInfo implements Serializable{
    @Id
    private String userid;

    private String userLevel;

    private String userLogo;

    private String userType;

    private Date registerTime;

    private Date memberBirthday;

    private String realName;

    private String email;

    private String sex;

    private String mobile;

    private String address;

    private String idTypeCode;

    private String idNumber;

    private String phone;

    private String post;

    private String province;

    private String city;

    private String isVisitedMember;

    private String registerChannels;

    private String registerWay;

    private String isFirstLogin;

    private String cisCode;

    private String registerAreaNumber;

    private String detailAddress;

    private String nickname;

    private String isZoneOther;

    private String ebankUserLevel;

    private Date modifyTime;

    private String remark;

    private String registerIp;

    private String mallId;

    private String siteCode;

    private String isCanEdit;

    private String silenceflag;

  }