package com.example.vo.parameter;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class ITMCouponVp {

    private String userId;
    private Integer couponAmount;
    private DateTime couponEndTime;
    private String couponId;
    private String promotionName;

}
