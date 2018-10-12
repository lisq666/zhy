package com.example.mapper;

import com.example.model.UiaExternalUser;
import org.springframework.stereotype.Repository;

@Repository
public interface UiaExternalUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(UiaExternalUser record);

    int insertSelective(UiaExternalUser record);

    UiaExternalUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UiaExternalUser record);

    int updateByPrimaryKey(UiaExternalUser record);
}