package com.example.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "b2c_coupon_dispatch")
public class CouponDispatch implements Serializable{
    @Id
    private String dispatchedId;

    private String promotionId;

    private BigDecimal dispatchedTotalAmount;

    private BigDecimal dispatchedSuccess;

    private BigDecimal dispatchedFailed;

    private Date dispatchedTime;

    private String dispatchedExecutor;

    private String dispatchedStatus;

    private String remark;

    private String mallId;

}