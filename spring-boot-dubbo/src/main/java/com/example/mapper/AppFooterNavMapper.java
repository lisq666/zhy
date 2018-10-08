package com.example.mapper;

import com.example.model.AppFooterNav;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AppFooterNavMapper {
    int deleteByPrimaryKey(String id);

    int insert(AppFooterNav record);

    int insertSelective(AppFooterNav record);

    AppFooterNav selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AppFooterNav record);

    int updateByPrimaryKey(AppFooterNav record);
}