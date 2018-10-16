package com.example.controller;

import com.example.model.CouponRecord;
import com.example.service.CouponRecordService;
import com.example.utils.SecurityTool;
import com.example.vo.json.CouponRecordVo;
import com.example.vo.json.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping(produces = {"application/json;charset=UTF-8"})
public class CouponRecordController {

    @Resource
    private CouponRecordService couponRecordService;


    /**
     * 根据传入优惠券编号数组获取相应优惠券信息
     * @param couponIds 优惠券数组编号
     * @return 优惠券JSON集合
     */
    @ResponseBody
    @RequestMapping(value = "/couponRecord/acquireCouponRecords",method = {RequestMethod.POST})
    public JsonResult acquireCouponRecords(@RequestParam(value = "couponIds[]") String[] couponIds,String sign){
        try {
            if(couponIds == null || couponIds.length <= 0 || sign == null || "".equals(sign)){
                log.info("参数为空");
                return JsonResult.failedParamaterEmpty();
            }

            Map<String, String> paramValues = new HashMap<String, String>();
            paramValues.put("couponIds",couponIds.toString());
            String key = SecurityTool.getSignature(paramValues,null);
            if(sign.equals("1")){
                List<CouponRecordVo> couponRecordList = couponRecordService.selectReceiveCouponListByCouponIds(couponIds);
                if(couponRecordList == null || couponRecordList.size() <=0){
                    log.info("acquireCouponRecords方法无符合条件数据");
                    return JsonResult.failedDataEmpty();
                }
                return JsonResult.success(couponRecordList);
            }
            log.info("acquireCouponRecords验签失败" + sign);
            return JsonResult.failedSign();
        } catch (Exception e) {
            log.error("acquireCouponRecords方法系统异常" + e.getMessage());
        }
        return  JsonResult.failed();
    }

}

