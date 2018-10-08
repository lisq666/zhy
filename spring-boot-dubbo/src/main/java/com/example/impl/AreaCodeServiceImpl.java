package com.example.impl;

import com.example.mapper.AreaCodeMapper;
import com.example.model.AreaCode;
import com.example.utils.BeanUtils;
import com.example.utils.JsonUtils;
import com.example.vo.json.AreaCodeVo;
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
    public AreaCode selectAreaCode(String id) throws Exception {
        AreaCodeVo areaCodeVo = new AreaCodeVo();
        AreaCode areaCode = areaCodeMapper.selectAreaCode(id);
        BeanUtils.copyA2B(areaCode,areaCodeVo);
        logger.info(jsonUtils.serialize(areaCodeVo));
        return areaCode;
    }
    @Override
    public List<AreaCode> selectAreaCodeList() throws Exception{
         List<AreaCodeVo> list = new ArrayList<AreaCodeVo>();
         List<AreaCode> list1 = areaCodeMapper.selectAreaCodeList();
         for (AreaCode var :list1) {
            AreaCodeVo areaCodeVo = new AreaCodeVo();
            BeanUtils.copyA2B(var,areaCodeVo);
            list.add(areaCodeVo);
        }
         return list1;
     }
}
