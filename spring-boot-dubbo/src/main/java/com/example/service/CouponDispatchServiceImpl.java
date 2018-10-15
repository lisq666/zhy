package com.example.service;

import com.example.dto.Constants;
import com.example.dto.CouponConstants;
import com.example.dto.GeneratorConstants;
import com.example.dto.PromotionConstants;
import com.example.mapper.CouponDispatchMapper;
import com.example.model.CouponDispatch;
import com.example.utils.JsonUtils;
import com.example.utils.StringUtils;
import com.example.utils.keygen.SerialGeneratorMgr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;

@Service("couponDispatchService")
@Transactional
public class CouponDispatchServiceImpl implements CouponDispatchService {

    protected static final Logger logger = LoggerFactory.getLogger(CouponDispatchServiceImpl.class);
    protected static final JsonUtils jsonUtils = new JsonUtils(JsonUtils.JSON).ignoreEmpty();

    @Autowired
    private CouponDispatchMapper couponDispatchMapper;

    /**
     * 创建初始化优惠券派发实例
     * @return
     * @throws Exception
     */
    @Override
    public CouponDispatch ITMCreateInitCouponDispatch(String promotionId, Integer couponAmount) throws Exception {
        if(StringUtils.isBlank(promotionId) || null == couponAmount || couponAmount == 0){
            logger.error("The incoming parameter is empty , 传入参数为空");
            throw new NullPointerException("The incoming parameter is empty , 传入参数为空");
        }
       /* "DISPATCHED_ID": "201810120000224301",
        "PROMOTION_ID": "201810120155",
        "DISPATCHED_TOTAL_AMOUNT": "1.00",
        "DISPATCHED_SUCCESS": "1",
        "DISPATCHED_FAILED": "0",
        "DISPATCHED_TIME": "2018-10-12 11:31:56",
        "DISPATCHED_EXECUTOR": "011002",
        "DISPATCHED_STATUS": "01",
        "REMARK": "商城派发",
        "mall_id": "00000000"*/
        CouponDispatch dispatch = new CouponDispatch();
        SerialGeneratorMgr mgr = new SerialGeneratorMgr();
        String dispatchId = mgr.getSerialKey(GeneratorConstants.COUPON_DISPATCH_SERIAL).trim();

        dispatch.setDispatchedId(dispatchId);
        dispatch.setPromotionId(promotionId);
        dispatch.setDispatchedTotalAmount(new BigDecimal(couponAmount));
        dispatch.setDispatchedSuccess(new BigDecimal(CouponConstants.DISPATCHED_SUCCESS));
        dispatch.setDispatchedFailed(new BigDecimal(CouponConstants.DISPATCHED_FAILD));
        dispatch.setDispatchedTime(new Date());
        dispatch.setDispatchedExecutor(PromotionConstants.ITM_PROMOTION_LAUNCHER);
        dispatch.setDispatchedStatus(CouponConstants.DISPATCHED_STATUS_SUCCESS);
        dispatch.setRemark("商城派发");
        dispatch.setMallId(Constants.MALL_ID);

        return dispatch;
    }

    /**
     * 优惠券派发实例入库
     * @param couponDispatch
     * @return
     * @throws Exception
     */
    @Override
    public Integer insertCouponDispatch(CouponDispatch couponDispatch) throws Exception {
        if(null != couponDispatch) return couponDispatchMapper.insertSelective(couponDispatch);
        return 0;
    }
}
