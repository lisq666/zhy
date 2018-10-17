package com.example.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.example.service.PromotionService;
import com.example.utils.SecurityTool;
import com.example.vo.json.JsonResult;
import com.example.vo.parameter.ITMCouponVp;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping(produces = {"application/json;charset=UTF-8"})
public class PromotionController {

    @Resource
    private PromotionService promotionService;

    @RequestMapping(value="/promotion/receiveCouponITM", method = {RequestMethod.POST})
    @ResponseBody
    public JsonResult ITMReceiveCoupon(ITMCouponVp vp) throws Exception{
        // 初始化验参参数
        HashMap<String, String> paramValues = initParamValues(vp);
        if(null == paramValues){
            return JsonResult.failed(10000, "传入参数异常");
        }
        try {
            // 验签
            String key = SecurityTool.getSignature(paramValues,null);
            if(StringUtils.isEquals(key, vp.getSign())){
                // 生成并发放优惠券
                return promotionService.ITMReceiveCoupon(vp);
            }
            return JsonResult.failed(99999,"验签失败");
        } catch (Exception e) {
            log.error("ITM领劵异常",e);
            return JsonResult.failed("ITM领劵异常");
        }
//        return promotionService.ITMReceiveCoupon(vp);
    }

    private HashMap<String, String> initParamValues(ITMCouponVp vp){
        // 校验非空
        if(null == vp || null == vp.getCouponAmount() || null == vp.getCouponEndTime()
                || com.example.utils.StringUtils.isBlank(vp.getPromotionName()) || com.example.utils.StringUtils.isBlank(vp.getUserId())
                || StringUtils.isBlank(vp.getTimeStamp()) || StringUtils.isBlank(vp.getSign()) ){
           return null;
        }
        // 初始化验签参数
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("promotionName", vp.getPromotionName());
        map.put("timeStamp", vp.getTimeStamp());
        map.put("userId", vp.getUserId());
        map.put("couponAmount", vp.getCouponAmount().toString());
        map.put("couponEndTime", new DateTime(vp.getCouponEndTime()).toString("yyyy-MM-dd HH:mm:ss"));
        return map;
    }

}
