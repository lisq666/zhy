package com.example.mapper;

import com.example.model.CouponRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRecordMapper {
    int deleteByPrimaryKey(String couponId);

    int insert(CouponRecord record);

    int insertSelective(CouponRecord record);

    CouponRecord selectByPrimaryKey(String couponId);

    int updateByPrimaryKeySelective(CouponRecord record);

    int updateByPrimaryKey(CouponRecord record);
}