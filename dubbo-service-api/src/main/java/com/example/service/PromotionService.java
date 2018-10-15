package com.example.service;

import com.example.model.Promotion;
import com.example.vo.json.JsonResult;
import com.example.vo.parameter.ITMCouponVp;

public interface PromotionService {

    /**
     * 领劵方法
     */
    public JsonResult ITMReceiveCoupon(ITMCouponVp couponVp) throws Exception;

    /**
     * 创建初始化活动实例
     * @throws Exception
     */
    public Promotion ITMCreateInitPromotion(ITMCouponVp vp) throws Exception;

    /**
     * 活动入库
     * @param promotion
     * @return
     * @throws Exception
     */
    public Integer insertPromotion(Promotion promotion) throws Exception;
}
