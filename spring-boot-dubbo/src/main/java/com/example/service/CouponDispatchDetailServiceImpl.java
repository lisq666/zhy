package com.example.service;

import com.example.mapper.CouponDispatchDetailMapper;
import com.example.model.CouponDispatchDetail;
import com.example.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service("couponDispatchDetailService")
@Transactional
public class CouponDispatchDetailServiceImpl implements CouponDispatchDetailService {

    protected static final Logger logger = LoggerFactory.getLogger(CouponDispatchDetailServiceImpl.class);
    protected static final JsonUtils jsonUtils = new JsonUtils(JsonUtils.JSON).ignoreEmpty();

    @Autowired
    private CouponDispatchDetailMapper couponDispatchDetailMapper;

    @Override
    public CouponDispatchDetail ITMCreateInitCouponDispatchDetail() throws Exception {
        return null;
    }

    @Override
    public Integer insertCouponDispatchDetail(CouponDispatchDetail couponDispatchDetail) throws Exception {
        if(null !=couponDispatchDetail) couponDispatchDetailMapper.insertSelective(couponDispatchDetail);
        return 0;
    }
}
