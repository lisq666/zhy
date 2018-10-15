package com.example.service;

import com.example.model.CouponDispatchDetail;
import com.example.model.CouponRecord;
import com.example.vo.json.CouponVo;
import com.example.vo.parameter.ITMCouponVp;

public interface CouponRecordService {

    /**
     * 创建初始化优惠券信息
     * @return
     * @throws Exception
     */
    CouponRecord ITMCreateInitCouponRecord(ITMCouponVp vp, CouponDispatchDetail detail)throws Exception;

    /**
     * 优惠券信息入库
     * @return
     * @throws Exception
     */
    Integer insertCouponRecord(CouponRecord couponRecord) throws Exception;

    CouponVo couponToVo(CouponRecord couponRecord);
}
