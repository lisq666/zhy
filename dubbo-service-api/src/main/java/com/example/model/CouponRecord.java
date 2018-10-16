package com.example.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CouponRecord {
    private String couponId;

    private String promotionId;

    private String orderId;

    private BigDecimal couponAmount;

    private BigDecimal denominationAmount;

    private Date couponGenTime;

    private Date couponExpireDate;

    private String acceptedStatus;

    private String memberId;

    private String merchantId;

    private String couponStatus;

    private BigDecimal couponUsedAmount;

    private Date acceptedDate;

    private BigDecimal acceptedAmount;

    private String dispatchedStatus;

    private String dispatchedDetailId;

    private String chenel;

    private String recordType;

    private String fatherCouponId;

    private String rootCouponId;

    private String returnCouponRuleId;

    private BigDecimal couponPayAmt;

    private String couponOrderId;

    private String couponRuleId;

    private Date couponPutwayTime;

    private String channelType;

    private String mallId;

    private String isSendMessage;

    /**
     * 系统生成来源 0供销 1智慧云
     */
    private int sysSource;

    /**
     * 使用来源 1供销 2智慧云
     */
    private String usedSource;

}