package com.example.service;

import com.example.model.CouponDispatchDetail;

public interface CouponDispatchDetailService {

    /**
     * 创建优惠券派发详情实例
     * @return
     * @throws Exception
     */
    CouponDispatchDetail ITMCreateInitCouponDispatchDetail() throws Exception;

    /**
     * 优惠券派发详情入库
     * @param couponDispatchDetail
     * @return
     * @throws Exception
     */
    Integer insertCouponDispatchDetail(CouponDispatchDetail couponDispatchDetail) throws Exception;
}
