package com.example.mapper;

import com.example.model.Promotion;
import com.example.model.PromotionKey;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionMapper {
    int deleteByPrimaryKey(PromotionKey key);

    int insert(Promotion record);

    int insertSelective(Promotion record);

    Promotion selectByPrimaryKey(PromotionKey key);

    int updateByPrimaryKeySelective(Promotion record);

    int updateByPrimaryKeyWithBLOBs(Promotion record);

    int updateByPrimaryKey(Promotion record);

    Promotion checkPromotionName(String promotionName);
}