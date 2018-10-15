package com.example.service;

import com.example.model.Promotion;
import com.example.model.PromotionParamValue;
import com.example.vo.parameter.ITMCouponVp;

import java.util.List;

public interface PromotionParamValueService {

    /**
     * 初始化ITM活动参数实例
     * @return
     * @throws Exception
     */
    List<PromotionParamValue> ITMCreateInitPromotionParamValue(Promotion promotion, ITMCouponVp vp) throws Exception;

    /**
     * 活动参数入库
     * @param promotionParamValue
     * @return
     * @throws Exception
     */
    Integer insertPromotionParamValue(PromotionParamValue promotionParamValue) throws Exception;
}
