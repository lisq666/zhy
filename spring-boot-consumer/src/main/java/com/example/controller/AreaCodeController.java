package com.example.controller;

import com.example.service.AreaCodeService;
import com.example.vo.json.AreaCodeVo;
import com.example.vo.json.JsonResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(produces = {"application/json;charset=UTF-8"})
public class AreaCodeController extends BaseController{

    @Resource
    private AreaCodeService areaCodeService;

    @RequestMapping(value="/select/selectAreaCode", method = {RequestMethod.GET})
    @ResponseBody
    public JsonResult selectAreaCode (String id){
        AreaCodeVo areaCode = null;
        try {
            areaCode = areaCodeService.selectAreaCode(id);
            return JsonResult.success(areaCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonResult.failed();
    }



}