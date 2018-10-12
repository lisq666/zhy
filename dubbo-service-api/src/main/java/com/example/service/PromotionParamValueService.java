package com.example.service;

import com.example.model.PromotionParamValue;

public interface PromotionParamValueService {

    /**
     * 初始化ITM活动参数实例
     * @return
     * @throws Exception
     */
    PromotionParamValue ITMCeeateInitPromotionParamValue() throws Exception;

    /**
     * 活动参数入库
     * @param promotionParamValue
     * @return
     * @throws Exception
     */
    Integer insertPromotionParamValue(PromotionParamValue promotionParamValue) throws Exception;
}
