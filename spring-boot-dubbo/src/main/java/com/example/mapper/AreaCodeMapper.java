package com.example.mapper;

import com.example.model.AreaCode;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaCodeMapper {

    AreaCode selectByPrimaryKey(String id);
}