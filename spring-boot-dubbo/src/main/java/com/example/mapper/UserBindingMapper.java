package com.example.mapper;

import com.example.model.UserBinding;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBindingMapper {
    int deleteByPrimaryKey(String userid);

    int insert(UserBinding record);

    int insertSelective(UserBinding record);

    UserBinding selectByPrimaryKey(String userid);

    int updateByPrimaryKeySelective(UserBinding record);

    int updateByPrimaryKey(UserBinding record);
}