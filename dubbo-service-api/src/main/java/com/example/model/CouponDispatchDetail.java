package com.example.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "b2c_coupon_dispatch_detail")
public class CouponDispatchDetail implements Serializable{
    @Id
    private String dispatchedDetailId;

    private String promotionId;

    private String dispatchedMemberId;

    private String dispatchedMemberName;

    private BigDecimal dispatchedAmount;

    private String dispatchedId;

    private String dispatchedDetailStatus;

    private BigDecimal dispatchedDonomination;

    private BigDecimal dispatchedCount;

    private String errorDesc;

    private String status;

    private String isSendAlready;

    private Date createDate;

    private Date lastModifyDate;

    private String creatorId;

    private String reviewerId;

    private String remark;

    private String mallId;

    private Date getCouponDate;

    private String couponOverdueStatus;

    private String deleteCouponStatus;

}