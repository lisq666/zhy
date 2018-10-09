package com.example.service;

import com.example.mapper.AuthMapper;
import com.example.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("authService")
@Transactional
public class AuthServiceImpl implements AuthService {

    protected static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    protected static final JsonUtils jsonUtils = new JsonUtils(JsonUtils.JSON).ignoreEmpty();

    @Autowired
    private AuthMapper authMapper;




}
