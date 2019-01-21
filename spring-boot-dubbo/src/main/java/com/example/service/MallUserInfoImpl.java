package com.example.service;

import com.example.mapper.MallUserInfoMapper;
import com.example.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service("mallUserInfoService")
@Transactional
public class MallUserInfoImpl implements MallUserInfoService {

    protected static final Logger logger = LoggerFactory.getLogger(MallUserInfoImpl.class);
    protected static final JsonUtils jsonUtils = new JsonUtils(JsonUtils.JSON).ignoreEmpty();

    @Autowired
    private MallUserInfoMapper mallUserInfoMapper;

    @Override
    public void userRegister() throws Exception{

    }

}
