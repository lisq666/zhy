package com.example.vo.parameter;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ITMCouponVp implements Serializable {

    /**
     * 用户id
     */
    private String userId;
    /**
     * 优惠券面额
     */
    private Integer couponAmount;
    /**
     * 优惠券结束时间
     */
    private String couponEndTime;
//    private String couponId;
    /**
     * 活动名称
     */
    private String promotionName;
    /**
     * 时间戳
     */
    private String timeStamp;
    /**
     * 签名
     */
    private String sign;

}
