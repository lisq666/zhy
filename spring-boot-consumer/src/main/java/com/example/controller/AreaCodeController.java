package com.example.controller;

import com.example.service.AreaCodeService;
import com.example.vo.json.AreaCodeVo;
import com.example.vo.json.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(produces = {"application/json;charset=UTF-8"})
public class AreaCodeController extends BaseController{

    @Resource
    private AreaCodeService areaCodeService;

    @RequestMapping(value="/select/selectAreaCode", method = {RequestMethod.GET})
    @ResponseBody
    public R selectAreaCode (String id){
        try {
            AreaCodeVo areaCode = areaCodeService.selectAreaCode(id);
            return R.ok().put("data",areaCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.error();
    }



}