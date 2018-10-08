package com.example.impl;

import com.example.model.AppFooterNav;
import com.example.model.AreaCode;
import com.example.vo.json.AppFooterNavVo;

import java.util.List;

public interface AppFooterNavService {

    AppFooterNavVo selectAppFooterNav(String id) throws Exception;

}
