package com.example.mapper;

import com.example.model.Auth;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthMapper {
    int deleteByPrimaryKey(String userid);

    int insert(Auth record);

    int insertSelective(Auth record);

    Auth selectByPrimaryKey(String userid);

    int updateByPrimaryKeySelective(Auth record);

    int updateByPrimaryKey(Auth record);

    int countByLoginId(String loginId);
}