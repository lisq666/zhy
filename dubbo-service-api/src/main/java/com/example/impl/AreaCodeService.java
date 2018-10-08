package com.example.impl;

import com.example.model.AreaCode;
import com.example.vo.json.AreaCodeVo;

import java.util.List;

public interface AreaCodeService {

    AreaCode selectAreaCode(String id) throws Exception;

    List<AreaCode> selectAreaCodeList() throws Exception;
}
