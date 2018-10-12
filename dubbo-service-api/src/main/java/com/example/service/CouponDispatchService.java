package com.example.service;

import com.example.model.CouponDispatch;

public interface CouponDispatchService {

    /**
     * 创建初始化活动派发实例
     * @return
     * @throws Exception
     */
    CouponDispatch ITMCreateInitCouponDispatch() throws Exception;

    Integer insertCouponDispatch(CouponDispatch couponDispatch) throws Exception;
}
