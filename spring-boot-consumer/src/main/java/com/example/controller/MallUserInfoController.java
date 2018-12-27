package com.example.controller;

import com.example.vo.json.JsonResult;
import com.example.service.MallUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping(produces = {"application/json;charset=UTF-8"})
public class MallUserInfoController extends BaseController{

    @Resource
    private MallUserInfoService mallUserInfoService;

    @RequestMapping(value="/userRegister", method = {RequestMethod.POST})
    @ResponseBody
    public void userRegister (){
        try {
            mallUserInfoService.userRegister();
        } catch (Exception e) {
            log.error("注册异常",e);
        }
    }


}