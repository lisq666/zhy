package com.example.impl;

import com.example.mapper.AppFooterNavMapper;
import com.example.mapper.AreaCodeMapper;
import com.example.model.AppFooterNav;
import com.example.model.AreaCode;
import com.example.utils.BeanUtils;
import com.example.utils.JsonUtils;
import com.example.vo.json.AppFooterNavVo;
import com.example.vo.json.AreaCodeVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("appFooterNavService")
@Transactional
public class AppFooterNavServiceImpl implements AppFooterNavService {

    protected static final Logger logger = LoggerFactory.getLogger(AppFooterNavServiceImpl.class);
    protected static final JsonUtils jsonUtils = new JsonUtils(JsonUtils.JSON).ignoreEmpty();

    @Autowired
    private AppFooterNavMapper appFooterNavMapper;


    @Override
    public AppFooterNavVo selectAppFooterNav(String id) throws Exception {
        AppFooterNavVo appFooterNavVo = new AppFooterNavVo();
        AppFooterNav appFooterNav = appFooterNavMapper.selectByPrimaryKey(id);
        BeanUtils.copyA2B(appFooterNav,appFooterNavVo);
        return appFooterNavVo;
    }
}
