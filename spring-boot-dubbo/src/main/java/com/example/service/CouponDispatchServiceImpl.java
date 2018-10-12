package com.example.service;

import com.example.mapper.CouponDispatchMapper;
import com.example.model.CouponDispatch;
import com.example.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
    public CouponDispatch ITMCreateInitCouponDispatch() throws Exception {
        return null;
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
