package com.example.mapper;

import com.example.model.CouponDispatchDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponDispatchDetailMapper {
    int deleteByPrimaryKey(String dispatchedDetailId);

    int insert(CouponDispatchDetail record);

    int insertSelective(CouponDispatchDetail record);

    CouponDispatchDetail selectByPrimaryKey(String dispatchedDetailId);

    int updateByPrimaryKeySelective(CouponDispatchDetail record);

    int updateByPrimaryKey(CouponDispatchDetail record);
}