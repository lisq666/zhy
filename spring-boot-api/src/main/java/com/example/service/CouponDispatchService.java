package com.example.service;

import com.example.model.CouponDispatch;

public interface CouponDispatchService {

    /**
     * 创建初始化活动派发实例
     * @return
     * @throws Exception
     */
    CouponDispatch ITMCreateInitCouponDispatch(String promotionId, Integer couponAmount) throws Exception;

    Integer insertCouponDispatch(CouponDispatch couponDispatch) throws Exception;
}
