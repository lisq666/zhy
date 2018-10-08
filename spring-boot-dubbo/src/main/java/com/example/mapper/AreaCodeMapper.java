package com.example.mapper;

import com.example.model.AreaCode;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Mapper
public interface AreaCodeMapper {
    @Select("SELECT * FROM b2c_areacode")
    @Results(id = "userMap", value = {
            @Result(property = "codeId", column = "code_id", javaType = String.class),
            @Result(property = "codeName", column = "code_name", javaType = String.class),
            @Result(property = "addressId",column = "address_id", javaType = String.class),
            @Result(property = "addressName",column = "address_name", javaType = String.class)
    })
    public List<AreaCode> selectAreaCodeList();

    @Select("SELECT * FROM b2c_areacode where code_id=#{id}")
    @ResultMap("userMap")
    public AreaCode selectAreaCode(String id);
}