package com.example.service;

import com.example.vo.json.AreaCodeVo;
import com.example.vo.parameter.AreaCodeVp;

public interface AreaCodeService {

    AreaCodeVo selectAreaCode(String id) throws Exception;

}
