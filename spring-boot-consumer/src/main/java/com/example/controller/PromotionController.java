package com.example.controller;

import com.example.service.PromotionService;
import com.example.vo.json.JsonResult;
import com.example.vo.parameter.ITMCouponVp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping(produces = {"application/json;charset=UTF-8"})
public class PromotionController {

    @Resource
    private PromotionService promotionService;

    @RequestMapping(value="/promotion/receiveCouponITM", method = {RequestMethod.POST})
    @ResponseBody
    public JsonResult ITMReceiveCoupon(ITMCouponVp vp) throws Exception{
        return promotionService.ITMReceiveCoupon(vp);
    }

}
