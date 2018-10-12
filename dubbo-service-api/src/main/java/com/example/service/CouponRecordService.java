package com.example.service;

import com.example.model.CouponRecord;

public interface CouponRecordService {

    /**
     * 创建初始化优惠券信息
     * @param couponId
     * @return
     * @throws Exception
     */
    CouponRecord ITMCreateInitCouponRecord(String couponId)throws Exception;

    /**
     * 优惠券信息入库
     * @return
     * @throws Exception
     */
    Integer insertCouponRecord(CouponRecord couponRecord) throws Exception;
}
