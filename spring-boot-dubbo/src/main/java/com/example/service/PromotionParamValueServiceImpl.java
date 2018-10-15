package com.example.service;

import com.example.mapper.PromotionParamValueMapper;
import com.example.model.Promotion;
import com.example.model.PromotionParamValue;
import com.example.utils.JsonUtils;
import com.example.utils.StringUtils;
import com.example.vo.parameter.ITMCouponVp;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service("promotionParamValueService")
@Transactional
public class PromotionParamValueServiceImpl implements PromotionParamValueService {

    protected static final Logger logger = LoggerFactory.getLogger(PromotionParamValueServiceImpl.class);
    protected static final JsonUtils jsonUtils = new JsonUtils(JsonUtils.JSON).ignoreEmpty();

    @Autowired
    private PromotionParamValueMapper promotionParamValueMapper;

    private final String TIME_PARAM = "yyyy-MM-dd HH:mm:ss";
    /**
     * 初始化ITM活动参数实例
     * @return
     * @throws Exception
     */
    public List<PromotionParamValue> ITMCreateInitPromotionParamValue(Promotion promotion, ITMCouponVp vp) throws Exception {
        if(null == promotion || null == vp){
            logger.error("insert PromotionParamValue promotionId is null , 插入活动参数信息活动id为空");
            throw new NullPointerException("promotionId is null");
        }
        SimpleDateFormat sdf = new SimpleDateFormat();

        List<PromotionParamValue> list = new ArrayList<PromotionParamValue>();
        PromotionParamValue value = new PromotionParamValue();
        value.setPromotionId(promotion.getPromotionId());
        value.setPromotionParamName("total_amount");
        value.setPromotionParamValue("10");
        value.setPromotionParamId("PD0001_P005");
        value.setPromotionParamDname("发券总金额");
        list.add(value);

        value = new PromotionParamValue();
        value.setPromotionId(promotion.getPromotionId());
        value.setPromotionParamName("single_amount");
        value.setPromotionParamValue(vp.getCouponAmount().toString());
        value.setPromotionParamId("PD0001_P006");
        value.setPromotionParamDname("单张面额");
        list.add(value);

        value = new PromotionParamValue();
        value.setPromotionId(promotion.getPromotionId());
        value.setPromotionParamName("expire_date");
        value.setPromotionParamValue(new DateTime(promotion.getPromotionStartTime()).toString(TIME_PARAM) +
                "|" + new DateTime(promotion.getPromotionEndTime()).toString(TIME_PARAM));
        value.setPromotionParamId("PD0001_P009");
        value.setPromotionParamDname("代金券有效期");
        list.add(value);

        value = new PromotionParamValue();
        value.setPromotionId(promotion.getPromotionId());
        value.setPromotionParamName("is_limit_coupon");
        value.setPromotionParamValue("6");
        value.setPromotionParamId("PD0001_P019");
        value.setPromotionParamDname("电子券类型");
        list.add(value);

        value = new PromotionParamValue();
        value.setPromotionId(promotion.getPromotionId());
        value.setPromotionParamName("perCouponAmt");
        value.setPromotionParamValue("1");
        value.setPromotionParamId("PD0001_P023");
        value.setPromotionParamDname("每人发放张数");
        list.add(value);

        value = new PromotionParamValue();
        value.setPromotionId(promotion.getPromotionId());
        value.setPromotionParamName("mallCouponType");
        value.setPromotionParamValue("6");
        value.setPromotionParamId("PD0001_P025");
        value.setPromotionParamDname("商城券发放方式");
        list.add(value);

        value = new PromotionParamValue();
        value.setPromotionId(promotion.getPromotionId());
        value.setPromotionParamName("method");
        value.setPromotionParamValue("00");
        value.setPromotionParamId("PD0001_P028");
        value.setPromotionParamDname("发放方式");
        list.add(value);

        value = new PromotionParamValue();
        value.setPromotionId(promotion.getPromotionId());
        value.setPromotionParamName("scope");
        value.setPromotionParamValue("00");
        value.setPromotionParamId("PD0001_P029");
        value.setPromotionParamDname("优惠券的使用范围");
        list.add(value);

        value = new PromotionParamValue();
        value.setPromotionId(promotion.getPromotionId());
        value.setPromotionParamName("condition");
        value.setPromotionParamValue("0");
        value.setPromotionParamId("PD0001_P033");
        value.setPromotionParamDname("优惠券使用条件");
        list.add(value);

        value = new PromotionParamValue();
        value.setPromotionId(promotion.getPromotionId());
        value.setPromotionParamName("conditions");
        value.setPromotionParamValue("1");
        value.setPromotionParamId("PD0001_P034");
        value.setPromotionParamDname("优惠券满足条件");
        list.add(value);

        return list;
    }

    /**
     * 活动参数入库
     * @param promotionParamValue
     * @return
     * @throws Exception
     */
    public Integer insertPromotionParamValue(PromotionParamValue promotionParamValue) throws Exception {
        if(null != promotionParamValue){
            return promotionParamValueMapper.insertSelective(promotionParamValue);
        }
        return 0;
    }
}
