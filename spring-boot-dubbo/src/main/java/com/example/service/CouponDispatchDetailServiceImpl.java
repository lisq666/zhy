package com.example.service;

import com.example.dto.Constants;
import com.example.dto.CouponConstants;
import com.example.dto.GeneratorConstants;
import com.example.dto.PromotionConstants;
import com.example.mapper.CouponDispatchDetailMapper;
import com.example.model.Auth;
import com.example.model.CouponDispatch;
import com.example.model.CouponDispatchDetail;
import com.example.utils.JsonUtils;
import com.example.utils.keygen.SerialGeneratorMgr;
import com.example.vo.parameter.ITMCouponVp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;


@Service("couponDispatchDetailService")
@Transactional
public class CouponDispatchDetailServiceImpl implements CouponDispatchDetailService {

    protected static final Logger logger = LoggerFactory.getLogger(CouponDispatchDetailServiceImpl.class);
    protected static final JsonUtils jsonUtils = new JsonUtils(JsonUtils.JSON).ignoreEmpty();

    @Autowired
    private CouponDispatchDetailMapper couponDispatchDetailMapper;
    @Resource
    private AuthService authService;

    @Override
    public CouponDispatchDetail ITMCreateInitCouponDispatchDetail(ITMCouponVp vp, CouponDispatch dispatch) throws Exception {
        if(null == vp || null == dispatch){
            logger.error("The incoming parameter is empty , 传入参数为空");
            throw new NullPointerException("The incoming parameter is empty , 传入参数为空");
        }
         /*{
            "DISPATCHED_DETAIL_ID": "201810120000224302",
                "PROMOTION_ID": "201810120155",
                "DISPATCHED_MEMBER_ID": "201809210001981748",
                "DISPATCHED_MEMBER_NAME": "WarriorWilds",
                "DISPATCHED_AMOUNT": "10.00",
                "DISPATCHED_ID": "201810120000224301",
                "DISPATCHED_DETAIL_STATUS": "01",
                "DISPATCHED_DONOMINATION": "10.00",
                "DISPATCHED_COUNT": "1",
                "ERROR_DESC": "商城发券！",
                "STATUS": "02",
                "IS_SEND_ALREADY": "02",
                "CREATE_DATE": "2018-10-12 00:00:00",
                "LAST_MODIFY_DATE": "2018-10-12 00:00:00",
                "CREATOR_ID": "011002",
                "REVIEWER_ID": "011002",
                "REMARK": "",
                "mall_id": "00000000",
                "GET_COUPON_DATE": "",
                "COUPON_OVERDUE_STATUS": "",
                "DELETE_COUPON_STATUS": ""
        }*/
        Auth auth = authService.getAuthByUserId(vp.getUserId());
        SerialGeneratorMgr mgr = new SerialGeneratorMgr();
        String detailId = mgr.getSerialKey(GeneratorConstants.COUPON_DIPATCH_DETAIL_SERIAL).trim();

        CouponDispatchDetail detail = new CouponDispatchDetail();
        detail.setDispatchedDetailId(detailId);
        detail.setPromotionId(dispatch.getPromotionId());
        detail.setDispatchedMemberId(auth.getUserid());
        detail.setDispatchedMemberName(auth.getLoginId());
        detail.setDispatchedAmount(dispatch.getDispatchedTotalAmount());
        detail.setDispatchedId(dispatch.getDispatchedId());
        detail.setDispatchedDetailStatus(CouponConstants.DISPATCHED_DETAIL_STATUS_SUCCESS);
        detail.setDispatchedDonomination(dispatch.getDispatchedTotalAmount());
        detail.setDispatchedCount(new BigDecimal(1));
        detail.setErrorDesc("商城发券！");
        detail.setStatus(CouponConstants.DETAIL_STATUS_SUCCESS);
        detail.setIsSendAlready(CouponConstants.IS_SEND_ALREADY_YES);
        detail.setCreateDate(new Date());
        detail.setLastModifyDate(new Date());
        detail.setCreatorId(PromotionConstants.ITM_PROMOTION_LAUNCHER);
        detail.setReviewerId(PromotionConstants.ITM_PROMOTION_LAUNCHER);
        detail.setMallId(Constants.MALL_ID);

        return detail;
    }

    @Override
    public Integer insertCouponDispatchDetail(CouponDispatchDetail couponDispatchDetail) throws Exception {
        if(null !=couponDispatchDetail) return couponDispatchDetailMapper.insertSelective(couponDispatchDetail);
        return 0;
    }
}
