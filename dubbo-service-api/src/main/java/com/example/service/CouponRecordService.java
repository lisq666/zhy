package com.example.service;

import com.example.model.CouponDispatchDetail;
import com.example.model.CouponRecord;
import com.example.vo.json.CouponRecordVo;
import com.example.vo.json.CouponVo;
import com.example.vo.json.JsonResult;
import com.example.vo.parameter.ITMCouponVp;

import java.util.List;
import java.util.Map;

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

    /**
     * 根据传入优惠券编号数组获取相应优惠券信息
     * @param couponIds 优惠券数组编号
     * @return 优惠券JSON集合
     */
    List<CouponRecordVo> selectReceiveCouponListByCouponIds(String[] couponIds) throws Exception;

    JsonResult syncCouponStatus(String couponId);
}
