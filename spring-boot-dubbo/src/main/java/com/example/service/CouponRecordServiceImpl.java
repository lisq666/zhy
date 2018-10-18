package com.example.service;

import com.example.dto.Constants;
import com.example.dto.CouponConstants;
import com.example.dto.GeneratorConstants;
import com.example.dto.TimeConstants;
import com.example.mapper.CouponRecordMapper;
import com.example.model.CouponDispatchDetail;
import com.example.model.CouponRecord;
import com.example.utils.BeanUtils;
import com.example.utils.AESUtil;
import com.example.utils.JsonUtils;
import com.example.utils.StringUtils;
import com.example.utils.keygen.SerialGeneratorMgr;
import com.example.vo.json.CouponRecordVo;
import com.example.vo.json.CouponVo;
import com.example.vo.json.JsonResult;
import com.example.vo.parameter.ITMCouponVp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

@Service("couponRecordService")
@Transactional
public class CouponRecordServiceImpl implements CouponRecordService {

    protected static final Logger logger = LoggerFactory.getLogger(CouponRecordServiceImpl.class);
    protected static final JsonUtils jsonUtils = new JsonUtils(JsonUtils.JSON).ignoreEmpty();

    @Autowired
    private CouponRecordMapper couponRecordMapper;

    @Override
    public CouponRecord ITMCreateInitCouponRecord(ITMCouponVp vp, CouponDispatchDetail detail) throws Exception {
        if(null == vp || null == detail){
            logger.error("The incoming parameter is empty , 传入参数为空");
            throw new NullPointerException("The incoming parameter is empty");
        }
        /*{
            "PROMOTION_ID": "201810120155",
                "COUPON_ID": "18101219272864",
                "DENOMINATION_AMOUNT": "10.00",
                "COUPON_GEN_TIME": "2018-10-12 11:31:01",
                "COUPON_EXPIRE_DATE": "2018-10-12 23:59:59",
                "ACCEPTED_STATUS": "01",
                "MEMBER_ID": "201809210001981748",
                "COUPON_STATUS": "01",
                "COUPON_USED_AMOUNT": "10.00",
                "DISPATCHED_STATUS": "1",
                "DISPATCHED_DETAIL_ID": "201810120000224302",
                "CHENEL": "05",
                "RECORD_TYPE": "06",
                "COUPON_PUTWAY_TIME": "2018-10-12 11:33:19",
                "mall_id": "00000000",
                "IS_SEND_MESSAGE": "1"
        }*/
        SerialGeneratorMgr mgr = new SerialGeneratorMgr();
        String couponId = mgr.getSerialKey(GeneratorConstants.COUPON_RECORD_SERIAL).trim();

        CouponRecord coupon = new CouponRecord();
        coupon.setPromotionId(detail.getPromotionId());
        coupon.setCouponId(couponId);
        coupon.setDenominationAmount(detail.getDispatchedAmount());
        coupon.setCouponGenTime(new Date(new Date().getTime() + TimeConstants.HALF_HOUR));
        coupon.setCouponExpireDate(vp.getCouponEndTime());
        coupon.setAcceptedStatus(CouponConstants.ACCEPTED_STATUS_NO);
        coupon.setMemberId(vp.getUserId());
        coupon.setCouponStatus(CouponConstants.COUPON_STATUS_CAN_USER);
        coupon.setCouponUsedAmount(detail.getDispatchedAmount());
        coupon.setDispatchedStatus(CouponConstants.COUPON_DISPATCHED_STATUS_YES);
        coupon.setDispatchedDetailId(detail.getDispatchedDetailId());
        coupon.setChenel(CouponConstants.COUPON_CHENEL);
        coupon.setRecordType(CouponConstants.COUPON_RECORD_TYPE);
        coupon.setCouponPutwayTime(new Date());
        coupon.setMallId(Constants.MALL_ID);
        coupon.setIsSendMessage(CouponConstants.IS_SEND_MESSAGE_NO);
        // 设置为智慧云优惠券
        coupon.setSysSource(1);

        return coupon;
    }

    @Override
    public Integer insertCouponRecord(CouponRecord couponRecord) throws Exception {
        if(null != couponRecord) return couponRecordMapper.insertSelective(couponRecord);
        return  0;
    }

    /**
     * 优惠券信息转成返回值信息并加密
     * @param record
     * @return
     */
    @Override
    public CouponVo couponToVo(CouponRecord record) {
        if(null == record){
            logger.error("The incoming parameter is empty , 传入参数为空");
            throw new NullPointerException("The incoming parameter is empty");
        }

        CouponVo vo = new CouponVo();

        try {
            vo.setCouponExpireDate(record.getCouponExpireDate());
            vo.setCouponGenTime(record.getCouponGenTime());
            vo.setCouponId(AESUtil.Encrypt(record.getCouponId()));
            vo.setCouponPutwayTime(record.getCouponPutwayTime());
            vo.setDenominationAmount(record.getDenominationAmount());
            vo.setMemberId(AESUtil.Encrypt(record.getMemberId()));
            vo.setPromotionId(AESUtil.Encrypt(record.getPromotionId()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return vo;
    }

    @Override
    public List<CouponRecordVo> selectReceiveCouponListByCouponIds(String[] couponIds) throws Exception{
        Map paramMap = new HashMap();
        paramMap.put("couponIds",couponIds);
        List<CouponRecordVo> targetCouponRecordList = new ArrayList<CouponRecordVo>();
        List<CouponRecord> resourcesCouponRecordList = couponRecordMapper.selectReceiveCouponListByCouponIds(paramMap);
        for(CouponRecord tempCouponRecord : resourcesCouponRecordList){
            //针对券编号进行AES加密
            tempCouponRecord.setCouponId(AESUtil.Encrypt(tempCouponRecord.getCouponId()));
            tempCouponRecord.setMemberId(AESUtil.Encrypt(tempCouponRecord.getMemberId()));
            tempCouponRecord.setPromotionId(AESUtil.Encrypt(tempCouponRecord.getPromotionId()));
            CouponRecordVo couponRecordVo = new CouponRecordVo();
            BeanUtils.copyA2B(tempCouponRecord,couponRecordVo);
            targetCouponRecordList.add(couponRecordVo);
        }
        return targetCouponRecordList;
    }

    /**
     * 智慧云项目同步优惠券状态
     * @param couponId
     * @return
     */
    @Override
    public JsonResult syncCouponStatus(String couponId) {
        if(StringUtils.isBlank(couponId)){
            return JsonResult.failed(10000, "优惠券id为空");
        }
        try{
            // 校验优惠券信息 正常返回优惠券信息 否则返回空
            CouponRecord coupon = checkCouponStatus(couponId);
            if(null == coupon){
                return JsonResult.failed(10001, "优惠券状态不正确");
            }
            // 修改优惠券状态
            int updateResult = 0;
            try {
                updateResult = initDefaultCouponAndUpdate(coupon);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(updateResult < 1){
                return JsonResult.failed(10002, "更新优惠券状态失败");
            }
            return JsonResult.success("更新成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return JsonResult.failed(19999, "系统异常");
    }

    /**
     * 初始化优惠券已使用状态并更新数据库
     * @param coupon
     * @return
     */
    private int initDefaultCouponAndUpdate(CouponRecord coupon) throws Exception{
        /*{
            "ORDER_ID": "020181017CS0304807",
                "COUPON_AMOUNT": "1.20",
                "ACCEPTED_STATUS": "02",
                "MERCHANT_ID": "00012096",
                "COUPON_STATUS": "02",
                "COUPON_USED_AMOUNT": "1.20",
                "ACCEPTED_AMOUNT": "0.00",
                "CHANNEL_TYPE": "2",
                "USED_SOURCE":"2"
        }*/
        if(null == coupon || StringUtils.isBlank(coupon.getCouponId())){ return 0; }

        coupon.setOrderId("-1");
        coupon.setCouponAmount(coupon.getDenominationAmount());
        coupon.setAcceptedStatus("02");
        coupon.setMerchantId("-1");
        coupon.setCouponStatus("02");
        coupon.setCouponUsedAmount(coupon.getDenominationAmount());
        coupon.setAcceptedAmount(new BigDecimal(0.00));
        coupon.setChannelType("2");
        coupon.setUsedSource("2");

        return couponRecordMapper.updateByPrimaryKeySelective(coupon);
    }

    /**
     * 校验优惠券状态
     * @param couponId
     * @return
     */
    private CouponRecord checkCouponStatus(String couponId) {
        CouponRecord couponRecord = couponRecordMapper.selectByPrimaryKey(couponId);
        // 优惠券来源为供销卷
        if(couponRecord.getSysSource() != 1){
            logger.error("优惠券" + couponId + "来源为供销卷, sysSource = 0");
            return null;
        }
        // 优惠券已经使用过
        if(!StringUtils.isBlank(couponRecord.getCouponStatus()) && couponRecord.getCouponStatus().equals("02")
                || !StringUtils.isBlank(couponRecord.getChannelType()) && couponRecord.getChannelType().equals("2")){
            logger.error("优惠券" + couponId + "已经使用过 already used");
            return null;
        }
        if(null == couponRecord){
            logger.error("优惠券" + couponId + "查询结果为空 null result");
        }
        return couponRecord;
    }
}
