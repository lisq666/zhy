package com.example.vo.json;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class AppFooterNavVo implements Serializable{

    private String id;

    private String title;

    private String description;

    private Date createTime;

    private Date beginTime;

    private Date endTime;

    private Date stopTime;

    private String status;

    private String indexBegin;

    private String indexAfter;

    private String categoryBegin;

    private String categoryAfter;

    private String shopcarBegin;

    private String shopcarAfter;

    private String myinfoBegin;

    private String myinfoAfter;

    private String textcolorBegin;

    private String textcolorAfter;

    private String auditStatus;

    private String auditUser;

    private Date auditTime;

    private String activityType;

   }