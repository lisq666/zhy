package com.example.impl;

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
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("areaCodeService")
@Transactional
public class AreaCodeServiceImpl implements AreaCodeService {

    protected static final Logger logger = LoggerFactory.getLogger(AreaCodeServiceImpl.class);
    protected static final JsonUtils jsonUtils = new JsonUtils(JsonUtils.JSON).ignoreEmpty();

    @Autowired
    private AreaCodeMapper areaCodeMapper;

    @Override
    public void addAreaCode(AreaCodeVp areaCodeVp) throws Exception {
        AreaCode areaCode = new AreaCode();
        BeanUtils.copyA2B(areaCodeVp,areaCode);
        areaCodeMapper.insert(areaCode);
    }

    @Override
    public void deleteAreaCode(String id) throws Exception {
        areaCodeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateAreaCode(AreaCodeVp areaCodeVp) throws Exception{
        AreaCode areaCode = new AreaCode();
        BeanUtils.copyA2B(areaCodeVp,areaCode);
        areaCodeMapper.updateByPrimaryKey(areaCode);
    }

    @Override
    public AreaCodeVo selectAreaCode(String id) throws Exception {
        AreaCodeVo areaCodeVo = new AreaCodeVo();
        AreaCode areaCode = areaCodeMapper.selectByPrimaryKey(id);
        BeanUtils.copyA2B(areaCode,areaCodeVo);
        logger.info(jsonUtils.serialize(areaCodeVo));
        return areaCodeVo;
    }



}
