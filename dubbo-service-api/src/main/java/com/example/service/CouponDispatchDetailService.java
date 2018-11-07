package com.example.service;

import com.example.model.Auth;
import com.example.model.CouponDispatch;
import com.example.model.CouponDispatchDetail;
import com.example.model.Promotion;
import com.example.vo.parameter.ITMCouponVp;

public interface CouponDispatchDetailService {

    /**
     * 创建优惠券派发详情实例
     * @return
     * @throws Exception
     */
    CouponDispatchDetail ITMCreateInitCouponDispatchDetail(ITMCouponVp vp, CouponDispatch dispatch, Auth auth) throws Exception;

    /**
     * 优惠券派发详情入库
     * @param couponDispatchDetail
     * @return
     * @throws Exception
     */
    Integer insertCouponDispatchDetail(CouponDispatchDetail couponDispatchDetail) throws Exception;
}
