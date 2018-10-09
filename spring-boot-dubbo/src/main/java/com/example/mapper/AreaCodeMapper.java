package com.example.mapper;

import com.example.model.AreaCode;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaCodeMapper {
    int deleteByPrimaryKey(String codeId);

    int insert(AreaCode record);

    int insertSelective(AreaCode record);

    AreaCode selectByPrimaryKey(String codeId);

    int updateByPrimaryKeySelective(AreaCode record);

    int updateByPrimaryKey(AreaCode record);
}