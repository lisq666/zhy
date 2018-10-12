package com.example.service;

import com.example.model.Promotion;
import com.example.vo.json.ResultData;
import com.example.vo.parameter.ITMCouponVp;
import org.joda.time.DateTime;

public interface PromotionService {

    /**
     * 领劵方法
     */
    public ResultData ITMReceiveCoupon(ITMCouponVp couponVp) throws Exception;

    /**
     * 创建初始化活动实例
     * @return
     * @throws Exception
     */
    public Promotion ITMCreateInitPromotion() throws Exception;

    /**
     * 活动入库
     * @param promotion
     * @return
     * @throws Exception
     */
    public Integer insertPromotion(Promotion promotion) throws Exception;
}
