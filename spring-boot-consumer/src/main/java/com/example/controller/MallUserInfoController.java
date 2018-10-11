package com.example.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.example.service.MallUserInfoService;
import com.example.vo.json.ResultData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(produces = {"application/json;charset=UTF-8"})
public class MallUserInfoController extends BaseController{

    @Resource
    private MallUserInfoService mallUserInfoService;

    @RequestMapping(value="/user/userRegister", method = {RequestMethod.GET})
    @ResponseBody
    public ResultData userRegister (String mobile,String password){
        if(StringUtils.isBlank(mobile.trim())){
            return ResultData.failure("手机号不能为空");
        }
        if(StringUtils.isBlank(password.trim())){
            return ResultData.failure("密码不能为空");
        }
        try {
            String userId = mallUserInfoService.userRegister(mobile,password);
            return ResultData.success(userId);
        } catch (Exception e) {
            logger.error("注册异常",e);
            return ResultData.failure("注册异常");
        }
    }


}