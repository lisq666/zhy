package com.example.service;

import com.example.mapper.PromotionParamValueMapper;
import com.example.model.PromotionParamValue;
import com.example.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("promotionParamValueService")
@Transactional
public class PromotionParamValueServiceImpl implements PromotionParamValueService {

    protected static final Logger logger = LoggerFactory.getLogger(PromotionParamValueServiceImpl.class);
    protected static final JsonUtils jsonUtils = new JsonUtils(JsonUtils.JSON).ignoreEmpty();

    @Autowired
    private PromotionParamValueMapper promotionParamValueMapper;

    /**
     * 初始化ITM活动参数实例
     * @return
     * @throws Exception
     */
    public PromotionParamValue ITMCeeateInitPromotionParamValue() throws Exception {

        return null;
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
