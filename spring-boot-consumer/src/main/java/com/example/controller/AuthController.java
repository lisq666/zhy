package com.example.controller;

import com.example.service.AreaCodeService;
import com.example.service.AuthService;
import com.example.service.MallUserInfoService;
import com.example.vo.json.AreaCodeVo;
import com.example.vo.json.ResultData;
import com.example.vo.parameter.AreaCodeVp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(produces = {"application/json;charset=UTF-8"})
public class AuthController extends BaseController{

    @Resource
    private AuthService authService;




}