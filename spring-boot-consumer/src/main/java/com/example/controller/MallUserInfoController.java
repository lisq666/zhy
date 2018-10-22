package com.example.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.echinacoop.uia.crypto.encoder.Encoder;
import com.example.utils.AccountValidatorUtil;
import com.example.utils.UiaEncoder;
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
    public JsonResult userRegister (String timeStamp, String sign,String mobile,String password){
        log.info("智慧云注册请求参数:sign={},mobile={},password={}",sign,mobile,password);
        if(mobile == null || StringUtils.isBlank(mobile.trim())){
            return JsonResult.failed(10002,"手机号不能为空");
        }
        if(password == null || StringUtils.isBlank(password.trim())){
            return JsonResult.failed(10003,"密码不能为空");
        }
        if(sign == null || StringUtils.isBlank(sign.trim())){
            return JsonResult.failed(10004,"sign签名不能为空");
        }
        if(!AccountValidatorUtil.isMobile(mobile)){
            return JsonResult.failed(10005,"手机号不合法");
        }
        Map<String, String> paramValues = new HashMap<String, String>();
        paramValues.put("mobile",mobile);
        paramValues.put("password",password);
        paramValues.put("timeStamp",timeStamp);
        try {
            String key = SecurityTool.getSignature(paramValues,null);
            log.info("智慧云签名校验:sign={},key={}",sign,key);
            if(sign.equals("1")){
                UserVo userVo = mallUserInfoService.userRegister(mobile,password);
                return JsonResult.success(userVo);
            }
            return JsonResult.failed(10001,"验签失败");
        } catch (Exception e) {
            log.error("注册异常",e);
            return JsonResult.failed("注册异常");
        }
    }


}