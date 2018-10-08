package com.example.controller;

import com.example.impl.AppFooterNavService;
import com.example.vo.json.AppFooterNavVo;
import com.example.vo.json.ResultData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@ResponseBody
@RequestMapping(produces = {"application/json;charset=UTF-8"})
public class AppFooterNavController extends BaseController{

    @Resource
    private AppFooterNavService appFooterNavService;

    @RequestMapping(value="/selectAppFooterNav/{id}", method = {RequestMethod.GET})
    public ResultData selectAppFooterNav (String id){
        AppFooterNavVo appFooterNav = null;
        try {
            appFooterNav = appFooterNavService.selectAppFooterNav(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultData.success(appFooterNav);
    }

    @RequestMapping(value="/selectApp/test", method = {RequestMethod.GET})
    public ResultData test (){
        return ResultData.success();
    }


}