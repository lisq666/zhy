package com.example.vo.json;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class CouponVo implements Serializable {

    private String couponId;

    private String promotionId;

    private BigDecimal denominationAmount;

    private Date couponGenTime;

    private Date couponExpireDate;

    private String acceptedStatus;

    private String memberId;

    private String couponStatus;

    private BigDecimal couponUsedAmount;

    private String dispatchedStatus;

    private String chenel;

    private String recordType;

    private Date couponPutwayTime;

    /**
     * 系统生成来源 0供销 1智慧云
     */
    private int sysSource;

    /**
     * 使用来源 1供销 2智慧云
     */
    private String userSource;

}
