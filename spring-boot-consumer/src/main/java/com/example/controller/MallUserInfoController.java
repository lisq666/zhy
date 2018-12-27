package com.example.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.example.utils.AccountValidatorUtil;
import com.example.vo.json.JsonResult;
import com.example.service.MallUserInfoService;
import com.example.utils.SecurityTool;
import com.example.vo.json.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(produces = {"application/json;charset=UTF-8"})
public class MallUserInfoController extends BaseController{

    @Resource
    private MallUserInfoService mallUserInfoService;

    @RequestMapping(value="/userRegister", method = {RequestMethod.POST})
    @ResponseBody
    public JsonResult userRegister (){
        try {
            UserVo userVo = mallUserInfoService.userRegister();
            return JsonResult.success(userVo);
        } catch (Exception e) {
            log.error("注册异常",e);
            return JsonResult.failed("注册异常");
        }
    }


}