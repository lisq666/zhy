package com.example.vo.parameter;

import lombok.Data;

import java.util.Date;

@Data
public class ITMCouponVp {

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
    private Date couponEndTime;
//    private String couponId;
    /**
     * 活动名称
     */
    private String promotionName;

}
