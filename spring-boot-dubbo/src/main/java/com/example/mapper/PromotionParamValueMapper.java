package com.example.mapper;

import com.example.model.PromotionParamValue;
import com.example.model.PromotionParamValueKey;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionParamValueMapper {
    int deleteByPrimaryKey(PromotionParamValueKey key);

    int insert(PromotionParamValue record);

    int insertSelective(PromotionParamValue record);

    PromotionParamValue selectByPrimaryKey(PromotionParamValueKey key);

    int updateByPrimaryKeySelective(PromotionParamValue record);

    int updateByPrimaryKey(PromotionParamValue record);
}