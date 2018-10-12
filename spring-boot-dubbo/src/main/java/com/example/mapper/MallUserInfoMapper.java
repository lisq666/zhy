package com.example.mapper;

import com.example.model.MallUserInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface MallUserInfoMapper {
    int deleteByPrimaryKey(String userid);

    int insert(MallUserInfo record);

    int insertSelective(MallUserInfo record);

    MallUserInfo selectByPrimaryKey(String userid);

    int updateByPrimaryKeySelective(MallUserInfo record);

    int updateByPrimaryKey(MallUserInfo record);

    MallUserInfo selectByMobile(String mobile);
}