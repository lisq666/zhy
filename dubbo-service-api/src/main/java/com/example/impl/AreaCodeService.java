package com.example.impl;

import com.example.model.AreaCode;
import com.example.vo.json.AreaCodeVo;
import com.example.vo.parameter.AreaCodeVp;

import java.util.List;

public interface AreaCodeService {

    void addAreaCode(AreaCodeVp areaCodeVp) throws Exception;

    void deleteAreaCode(String id) throws Exception;

    AreaCodeVo selectAreaCode(String id) throws Exception;

    void updateAreaCode(AreaCodeVp areaCodeVp) throws Exception;
}
