package com.example.service;

import com.example.mapper.CouponRecordMapper;
import com.example.model.CouponRecord;
import com.example.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("couponRecordService")
@Transactional
public class CouponRecordServiceImpl implements CouponRecordService {

    protected static final Logger logger = LoggerFactory.getLogger(CouponRecordServiceImpl.class);
    protected static final JsonUtils jsonUtils = new JsonUtils(JsonUtils.JSON).ignoreEmpty();

    @Autowired
    private CouponRecordMapper couponRecordMapper;

    @Override
    public CouponRecord ITMCreateInitCouponRecord(String couponId) throws Exception {
        return null;
    }

    @Override
    public Integer insertCouponRecord(CouponRecord couponRecord) throws Exception {
        if(null != couponRecord) return couponRecordMapper.insertSelective(couponRecord);
        return  0;
    }
}
