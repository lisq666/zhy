package com.example.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.example.service.MallUserInfoService;
import com.example.utils.SecurityTool;
import com.example.vo.json.ResultData;
import com.example.vo.json.UserVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(produces = {"application/json;charset=UTF-8"})
public class MallUserInfoController extends BaseController{

    @Resource
    private MallUserInfoService mallUserInfoService;

    @RequestMapping(value="/user/userRegister", method = {RequestMethod.GET})
    @ResponseBody
    public ResultData userRegister (String sign,String mobile,String password){
        if(StringUtils.isBlank(mobile.trim())){
            return ResultData.failure("手机号不能为空");
        }
        if(StringUtils.isBlank(password.trim())){
            return ResultData.failure("密码不能为空");
        }

        Map<String, String> paramValues = new HashMap<String, String>();
        paramValues.put("mobile",mobile);
        paramValues.put("password",password);
        try {
            String key = SecurityTool.getSignature(paramValues,null);
            if(sign.equals("1")){
                UserVo userVo = mallUserInfoService.userRegister(mobile,password);
                return ResultData.success(userVo);
            }
            return ResultData.failure("验签失败");
        } catch (Exception e) {
            logger.error("注册异常",e);
            return ResultData.failure("注册异常");
        }
    }


}