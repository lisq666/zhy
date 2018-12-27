package com.example.service;

import com.example.mapper.AreaCodeMapper;
import com.example.model.AreaCode;
import com.example.utils.BeanUtils;
import com.example.utils.JsonUtils;
import com.example.vo.json.AreaCodeVo;
import com.example.vo.parameter.AreaCodeVp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service("areaCodeService")
@Transactional
public class AreaCodeServiceImpl implements AreaCodeService {

    protected static final Logger logger = LoggerFactory.getLogger(AreaCodeServiceImpl.class);
    protected static final JsonUtils jsonUtils = new JsonUtils(JsonUtils.JSON).ignoreEmpty();

    @Autowired
    private AreaCodeMapper areaCodeMapper;
    @Resource
    private AsyncService asyncService;

    @Override
    public AreaCodeVo selectAreaCode(String id) throws Exception {
        asyncService.executeAsync();
        AreaCodeVo areaCodeVo = new AreaCodeVo();
        AreaCode areaCode = areaCodeMapper.selectByPrimaryKey(id);
        BeanUtils.copyA2B(areaCode,areaCodeVo);
        logger.info(jsonUtils.serialize(areaCodeVo));
        return areaCodeVo;
    }



}
