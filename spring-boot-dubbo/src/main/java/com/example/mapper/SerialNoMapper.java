package com.example.mapper;

import com.example.model.SerialNo;
import com.example.utils.keygen.SerialInfo;
import org.springframework.stereotype.Repository;


@Repository
public interface SerialNoMapper {
    int deleteByPrimaryKey(String serialName);

    int insert(SerialNo record);

    int insertSelective(SerialNo record);

    SerialNo selectByPrimaryKey(String serialName);

    int updateByPrimaryKeySelective(SerialNo record);

    int updateByPrimaryKey(SerialNo record);

    void updateKeyDataValue(SerialInfo serialInfo);

    Object queryForObject(SerialInfo serialInfo);
}