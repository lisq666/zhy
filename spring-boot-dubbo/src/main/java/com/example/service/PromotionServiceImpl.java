package com.example.service;

import com.example.mapper.PromotionMapper;
import com.example.model.*;
import com.example.utils.JsonUtils;
import com.example.utils.StringUtils;
import com.example.vo.json.ResultData;
import com.example.vo.parameter.ITMCouponVp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service("promotionService")
@Transactional
public class PromotionServiceImpl implements PromotionService {

    protected static final Logger logger = LoggerFactory.getLogger(PromotionServiceImpl.class);
    protected static final JsonUtils jsonUtils = new JsonUtils(JsonUtils.JSON).ignoreEmpty();

    @Autowired
    private PromotionMapper promotionMapper;
    @Autowired
    private PromotionParamValueService promotionParamValueService;
    @Autowired
    private CouponRecordService couponRecordService;
    @Autowired
    private CouponDispatchService couponDispatchService;
    @Autowired
    private CouponDispatchDetailService couponDispatchDetailService;


    /**
     * 创建ITM需求初始化活动
     * @return
     * @throws Exception
     */
    public Promotion ITMCreateInitPromotion() throws Exception{

        return null;
    }

    /**
     * 活动信息入库
     * @param promotion
     * @return
     * @throws Exception
     */
    public Integer insertPromotion(Promotion promotion) throws Exception{
        if(null != promotion) return promotionMapper.insertSelective(promotion);
        return 0;
    }

    /**
     * 智慧云项目对接领劵接口
     * @param vp
     * @return
     * @throws Exception
     */
    @Override
    public ResultData ITMReceiveCoupon(ITMCouponVp vp) throws Exception {
        // 判断参数非空
        if(null == vp || null == vp.getCouponAmount() || null == vp.getCouponEndTime()
            || StringUtils.isBlank(vp.getCouponId()) || StringUtils.isBlank(vp.getPromotionName())
            || StringUtils.isBlank(vp.getUserId()) ){
            logger.error("Incoming parameter exception, 传入参数异常");
            return ResultData.failure("Incoming parameter exception, 传入参数异常");
        }

        // 1 初始化活动实例 Promotion
        Promotion promotion = ITMCreateInitPromotion();
        // 2 初始化活动参数实例 PromotionParamValue
        PromotionParamValue promotionParamValue = promotionParamValueService.ITMCeeateInitPromotionParamValue();
        // 3 初始化优惠券实例 CouponRecord
        CouponRecord couponRecord = couponRecordService.ITMCreateInitCouponRecord(vp.getCouponId());
        // 4 初始化优惠券派发实例 CouponDispatch
        CouponDispatch couponDispatch = couponDispatchService.ITMCreateInitCouponDispatch();
        // 5 初始化优惠券派发详情实例
        CouponDispatchDetail couponDispatchDetail = couponDispatchDetailService.ITMCreateInitCouponDispatchDetail();

        // 初始化实例 非空校验
        if(null == promotion || null == promotionParamValue || null == couponRecord
                || null == couponDispatch || null == couponDispatchDetail){
            logger.error("Create initialization instance error, 创建初始化实例错误");
            return  ResultData.failure("Create initialization instance error, 创建初始化实例错误");
        }

        // 插入数据库
        try {
            if(insertPromotion(promotion) <= 0){
                logger.error("insert Promotion failure, 数据库插入活动信息失败");
                return ResultData.failure("insert Promotion failure, 数据库插入活动信息失败");
            }
            if(promotionParamValueService.insertPromotionParamValue(promotionParamValue) <= 0){
                logger.error("insert PromotionParamValue failure, 数据库插入活动参数信息失败");
                return  ResultData.failure("insert PromotionParamValue failure, 数据库插入活动参数信息失败");
            }
            if(couponRecordService.insertCouponRecord(couponRecord) <= 0){
                logger.error("insert CouponRecord failure, 数据库插入优惠券信息失败");
                return  ResultData.failure("insert CouponRecord failure, 数据库插入优惠券信息失败");
            }
            if(couponDispatchService.insertCouponDispatch(couponDispatch) <= 0){
                logger.error("insert CouponDispatch failure, 数据库插入优惠券派发信息失败");
                return  ResultData.failure("insert CouponDispatch failure, 数据库插入优惠券派发信息失败");
            }
            if(couponDispatchDetailService.insertCouponDispatchDetail(couponDispatchDetail) <= 0){
                logger.error("insert CouponDispatchDetail failure, 数据库插入优惠券派发详情信息失败");
                return  ResultData.failure("insert CouponDispatchDetail failure, 数据库插入优惠券派发详情信息失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultData.success(jsonUtils.serialize(couponRecord));
    }
}
