package com.example.controller;

import com.example.service.AreaCodeService;
import com.example.vo.json.AreaCodeVo;
import com.example.vo.json.ResultData;
import com.example.vo.parameter.AreaCodeVp;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(produces = {"application/json;charset=UTF-8"})
public class AreaCodeController extends BaseController{

    @Resource
    private AreaCodeService areaCodeService;

    @RequestMapping(value="/add/addAreaCode", method = {RequestMethod.POST})
    @ResponseBody
    public ResultData addAreaCode (AreaCodeVp areaCodeVp){
        try {
            areaCodeService.addAreaCode(areaCodeVp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultData.success();
    }

    @RequestMapping(value="/delete/deleteAreaCode", method = {RequestMethod.GET})
    @ResponseBody
    public ResultData deleteAreaCode (String id){
        try {
            areaCodeService.deleteAreaCode(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultData.success();
    }

    @RequestMapping(value="/update/updateAreaCode", method = {RequestMethod.POST})
    @ResponseBody
    public ResultData updateAreaCode (AreaCodeVp areaCodeVp){
        try {
            areaCodeService.updateAreaCode(areaCodeVp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultData.success();
    }


    @RequestMapping(value="/select/selectAreaCode", method = {RequestMethod.GET})
    @ResponseBody
    public ResultData selectAreaCode (String id){
        AreaCodeVo areaCode = null;
        try {
            areaCode = areaCodeService.selectAreaCode(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultData.success(areaCode);
    }



}