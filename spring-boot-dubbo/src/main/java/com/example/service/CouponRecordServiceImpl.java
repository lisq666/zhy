package com.example.service;

import com.example.dto.Constants;
import com.example.dto.CouponConstants;
import com.example.dto.GeneratorConstants;
import com.example.dto.TimeConstants;
import com.example.mapper.CouponRecordMapper;
import com.example.model.CouponDispatchDetail;
import com.example.model.CouponRecord;
import com.example.utils.JsonUtils;
import com.example.utils.keygen.SerialGeneratorMgr;
import com.example.vo.json.CouponVo;
import com.example.vo.parameter.ITMCouponVp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

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

        return coupon;
    }

    @Override
    public Integer insertCouponRecord(CouponRecord couponRecord) throws Exception {
        if(null != couponRecord) return couponRecordMapper.insertSelective(couponRecord);
        return  0;
    }

    @Override
    public CouponVo couponToVo(CouponRecord record) {
        if(null == record){
            logger.error("The incoming parameter is empty , 传入参数为空");
            throw new NullPointerException("The incoming parameter is empty");
        }

        CouponVo vo = new CouponVo();

        vo.setAcceptedStatus(record.getAcceptedStatus());
        vo.setChenel(record.getChenel());
        vo.setCouponExpireDate(record.getCouponExpireDate());
        vo.setCouponGenTime(record.getCouponGenTime());
        vo.setCouponId(record.getCouponId());
        vo.setCouponPutwayTime(record.getCouponPutwayTime());
        vo.setCouponStatus(record.getCouponStatus());
        vo.setCouponUsedAmount(record.getCouponUsedAmount());
        vo.setDenominationAmount(record.getDenominationAmount());
        vo.setDispatchedStatus(record.getDispatchedStatus());
        vo.setMemberId(record.getMemberId());
        vo.setPromotionId(record.getPromotionId());
        vo.setRecordType(record.getRecordType());

        return vo;
    }
}