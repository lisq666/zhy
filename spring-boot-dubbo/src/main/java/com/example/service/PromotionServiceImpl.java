package com.example.service;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.JSONArray;
import com.example.dto.Constants;
import com.example.dto.GeneratorConstants;
import com.example.dto.PromotionConstants;
import com.example.dto.TimeConstants;
import com.example.mapper.PromotionMapper;
import com.example.model.*;
import com.example.utils.AESUtil;
import com.example.utils.JsonUtils;
import com.example.utils.StringUtils;
import com.example.utils.keygen.SerialGeneratorMgr;
import com.example.vo.json.CouponVo;
import com.example.vo.json.JsonResult;
import com.example.vo.parameter.ITMCouponVp;
import jdk.nashorn.internal.scripts.JS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;


@Service("promotionService")
@Transactional
public class PromotionServiceImpl implements PromotionService {

    private static final Logger logger = LoggerFactory.getLogger(PromotionServiceImpl.class);
    private static final JsonUtils jsonUtils = new JsonUtils(JsonUtils.JSON).ignoreEmpty();

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
    public Promotion ITMCreateInitPromotion(ITMCouponVp vp) throws Exception{
        if(null == vp){
            logger.error("The incoming parameter is empty , 传入参数为空");
            throw new NullPointerException("The incoming parameter is empty , 传入参数为空");
        }
        SerialGeneratorMgr serialGeneratorMgr = new SerialGeneratorMgr();
        String promotionId = serialGeneratorMgr.getSerialKey(GeneratorConstants.PROMOTION_SERIAL).trim();
       /* "promotionId": "201810120155",
        "PROMOTION_NAME": "ITMTest",
        "PROMOTION_START_TIME": "2018-10-12 11:35:49",
        "PROMOTION_END_TIME": "2018-10-12 23:59:59",
        "PROMOTION_STATUS": "02",
        "PROMOTION_LAUNCHER": "017516",
        "PROMOTION_CREATE_TIME": "2018-10-12 11:29:04",
        "PROMOTION_DEFINE_ID": "PD0001",
        "CREATE_ID": "017516",
        "MODIFY_TIME": "2018-10-12 11:33:19",
        "MODIFY_ID": "017516",
        "SPONSOR": "00",
        "PROMOTION_CHANNEL": "0",
        "mall_id": "00000000",
        "magnitude": "1",
        "releaseStatus": "5",
        */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

       Promotion promotion = new Promotion();
       promotion.setPromotionId(promotionId);
       promotion.setPromotionName(vp.getPromotionName());
       promotion.setPromotionStartTime(new Date(new Date().getTime() + TimeConstants.HALF_HOUR));
       promotion.setPromotionEndTime(sdf.parse(vp.getCouponEndTime()));
       promotion.setPromotionStatus(PromotionConstants.PROMOTION_STATUS_WORKON);
       promotion.setPromotionLauncher(PromotionConstants.ITM_PROMOTION_LAUNCHER);
       promotion.setPromotionCreateTime(new Date());
       promotion.setPromotionDefineId(PromotionConstants.PRMOTION_DEFINE_ID_COUPON);
       promotion.setCreateId(PromotionConstants.ITM_PROMOTION_LAUNCHER);
       promotion.setModifyTime(new Date());
       promotion.setModifyId(PromotionConstants.ITM_PROMOTION_LAUNCHER);
       promotion.setSponsor(PromotionConstants.PRMOTION_SPONSOR_MALL);
       promotion.setPromotionChannel(PromotionConstants.PROMOTION_CHANNEL_ALL);
       promotion.setMallId(Constants.MALL_ID);
       // 活动发送数量
       promotion.setMagnitude("1");
       promotion.setReleasestatus("5");

       return promotion;
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
    public JsonResult  ITMReceiveCoupon(ITMCouponVp vp) throws Exception {
        // 判断参数非空
        if(null == vp || null == vp.getCouponAmount() || null == vp.getCouponEndTime()
            || StringUtils.isBlank(vp.getPromotionName()) || StringUtils.isBlank(vp.getUserId()) ){
            logger.error("Incoming parameter exception, 传入参数异常");
            return JsonResult.failed(10000,"传入参数异常");
        }
        // 解密
        vp = VpDecode(vp);
        // 校验数据库中是否有次活动的信息
        boolean promotionFlag = true;
        Promotion promotion = promotionMapper.checkPromotionName(vp.getPromotionName());
        if(null != promotion && !StringUtils.isBlank(promotion.getPromotionId())){
            promotionFlag = false;
        }

        List<PromotionParamValue> paramValueList = new ArrayList<PromotionParamValue>(15);
        if(promotionFlag){
            // 1 初始化活动实例 Promotion
            promotion = ITMCreateInitPromotion(vp);
            // 2 初始化活动参数实例 PromotionParamValue
            paramValueList = promotionParamValueService.ITMCreateInitPromotionParamValue(promotion, vp);
        }
        // 3 初始化优惠券派发实例 CouponDispatch
        CouponDispatch couponDispatch = couponDispatchService.ITMCreateInitCouponDispatch(promotion.getPromotionId(), vp.getCouponAmount());
        // 4 初始化优惠券派发详情实例
        CouponDispatchDetail couponDispatchDetail = couponDispatchDetailService.ITMCreateInitCouponDispatchDetail(vp, couponDispatch);
        // 5 初始化优惠券实例 CouponRecord
        CouponRecord couponRecord = couponRecordService.ITMCreateInitCouponRecord(vp, couponDispatchDetail);

        // 初始化实例 非空校验
        if(null == promotion || (null == paramValueList && paramValueList.size() > 0) || null == couponRecord
                || null == couponDispatch || null == couponDispatchDetail){
            logger.error("Create initialization instance error, 创建初始化实例错误");
            return  JsonResult.failed(10001, "创建初始化实例错误");
        }

        // 插入数据库
        try {
            if(promotionFlag){
                if(insertPromotion(promotion) <= 0){
                    logger.error("insert Promotion failure, 数据库插入活动信息失败");
                    return JsonResult.failed(10002, "数据库插入活动信息失败");
                }
                for (PromotionParamValue value: paramValueList) {
                    if(promotionParamValueService.insertPromotionParamValue(value) <= 0){
                        logger.error("insert PromotionParamValue failure, 数据库插入活动参数信息失败");
                        return  JsonResult.failed(10003, "数据库插入活动参数信息失败");
                    }
                }
            }
            if(couponDispatchService.insertCouponDispatch(couponDispatch) <= 0){
                logger.error("insert CouponDispatch failure, 数据库插入优惠券派发信息失败");
                return  JsonResult.failed(10005, "数据库插入优惠券派发信息失败");
            }
            if(couponDispatchDetailService.insertCouponDispatchDetail(couponDispatchDetail) <= 0){
                logger.error("insert CouponDispatchDetail failure, 数据库插入优惠券派发详情信息失败");
                return  JsonResult.failed(10006, "数据库插入优惠券派发详情信息失败");
            }
            if(couponRecordService.insertCouponRecord(couponRecord) <= 0){
                logger.error("insert CouponRecord failure, 数据库插入优惠券信息失败");
                return  JsonResult.failed(10004, "数据库插入优惠券信息失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        CouponVo vo = couponRecordService.couponToVo(couponRecord);
        logger.info(JSON.json(vo));
        return JsonResult.success(vo);
    }

    private ITMCouponVp VpDecode(ITMCouponVp vp) {
        try {
            vp.setUserId(AESUtil.Decrypt(vp.getUserId()));
            vp.setPromotionName(AESUtil.Decrypt(vp.getPromotionName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vp;
    }


}

