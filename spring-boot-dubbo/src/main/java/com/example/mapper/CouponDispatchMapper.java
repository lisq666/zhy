package com.example.mapper;

import com.example.model.CouponDispatch;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponDispatchMapper {
    int deleteByPrimaryKey(String dispatchedId);

    int insert(CouponDispatch record);

    int insertSelective(CouponDispatch record);

    CouponDispatch selectByPrimaryKey(String dispatchedId);

    int updateByPrimaryKeySelective(CouponDispatch record);

    int updateByPrimaryKey(CouponDispatch record);
}