package com.example.controller;

import com.example.impl.AreaCodeService;
import com.example.model.AreaCode;
import com.example.vo.json.AreaCodeVo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class AreaCodeController extends BaseController{

    @Resource
    private AreaCodeService areaCodeService;

    @RequestMapping(value="/selectAreaCode/{id}", method = {RequestMethod.GET})
    public String selectAreaCode (@PathVariable String id){
        AreaCode areaCode = null;
        try {
            areaCode = areaCodeService.selectAreaCode(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return areaCode.toString();
    }

    @RequestMapping(value="/selectAreaCodeList/")
    public String selectAreaCodeList (){
        List<AreaCode> areaCode = null;
        try {
            areaCode = areaCodeService.selectAreaCodeList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return areaCode.toString();
    }


}