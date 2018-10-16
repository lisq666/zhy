package com.example.mapper;

import com.example.model.CouponRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CouponRecordMapper {
    int deleteByPrimaryKey(String couponId);

    int insert(CouponRecord record);

    int insertSelective(CouponRecord record);

    CouponRecord selectByPrimaryKey(String couponId);

    int updateByPrimaryKeySelective(CouponRecord record);

    int updateByPrimaryKey(CouponRecord record);

    List<CouponRecord> selectReceiveCouponListByCouponIds(Map couponIds);
}