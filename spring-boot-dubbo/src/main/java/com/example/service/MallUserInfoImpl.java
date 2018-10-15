package com.example.service;

import com.echinacoop.uia.dubbo.client.auth.consumer.UserRegisterServicePrx;
import com.example.dto.Constants;
import com.example.mapper.AuthMapper;
import com.example.mapper.MallUserInfoMapper;
import com.example.mapper.UiaExternalUserMapper;
import com.example.mapper.UserBindingMapper;
import com.example.model.Auth;
import com.example.model.MallUserInfo;
import com.example.model.UiaExternalUser;
import com.example.model.UserBinding;
import com.example.utils.*;
import com.example.utils.keygen.SerialGeneratorMgr;
import com.example.vo.json.UserVo;
import com.example.vo.parameter.uia.UiaUserInfoVp;
import com.example.vo.parameter.uia.UiaVp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.*;

@Service("mallUserInfoService")
@Transactional
public class MallUserInfoImpl implements MallUserInfoService {

    protected static final Logger logger = LoggerFactory.getLogger(MallUserInfoImpl.class);
    protected static final JsonUtils jsonUtils = new JsonUtils(JsonUtils.JSON).ignoreEmpty();

    @Autowired
    private MallUserInfoMapper mallUserInfoMapper;
    @Autowired
    private AuthMapper authMapper;
    @Autowired
    private UserBindingMapper userBindingMapper;
    @Autowired
    private UiaExternalUserMapper uiaExternalUserMapper;
    @Resource
    private UserRegisterServicePrx userRegisterServicePrx;

    @Override
    public UserVo userRegister(String mobile, String custPwd) throws Exception{
        //校验该手机号本地库是否存在
        MallUserInfo mallUserInfo = mallUserInfoMapper.selectByMobile(mobile);
        if(mallUserInfo != null){//若存在
            logger.info("手机号:{},存在userId:{}",mobile,mallUserInfo.getUserid());
            Auth au = authMapper.selectByPrimaryKey(mallUserInfo.getUserid());
            return new UserVo(AESUtil.Encrypt(au.getUserid()),au.getPassword());
        }
        SerialGeneratorMgr serialGeneratorMgr = new SerialGeneratorMgr();
        String newUserId = serialGeneratorMgr.getSerialKey(Constants.AUTH_USERID).trim();
        Auth auth = addAuth(newUserId,custPwd);
        addUserInfo(mobile,newUserId);
        addUserBingding(mobile,newUserId);
        //syncUia(auth,custPwd,mobile);
        return new UserVo(AESUtil.Encrypt(auth.getUserid()),auth.getPassword());
    }

    /**
     * 插入UserBingding表
     * @param userId
     * @param custPwd
     * @throws Exception
     */
    private Auth addAuth(String userId,String custPwd) throws Exception{
        Auth auth = new Auth();
        auth.setUserid(userId);
        boolean flag = false;
        String loginId = "";
        while (!flag){
            loginId = LoginIdGenerator.getRandomLoginId();
            int count = authMapper.countByLoginId(loginId);
            flag = count > 0 ? false : true;
        }
        auth.setLoginId(loginId);
        auth.setPassword(Crypt.encrypte(userId,custPwd));
        auth.setUserType(Constants.USER_TYPE);//0:个人会员
        auth.setIsEnable(Constants.IS_ENABLE);//1:可用
        auth.setIsLock(Constants.IS_LOCK);//是否锁定：0 否；1 是
        authMapper.insertSelective(auth);
        logger.info("注册inserAuth:{}",jsonUtils.serialize(auth));
        return auth;
    }

    /**
     * 插入UserBingding表
     * @param mobile
     * @param userId
     * @throws Exception
     */
    private void addUserBingding(String mobile,String userId) throws Exception{
        UserBinding ub = new UserBinding();
        ub.setBindingMobile(mobile);
        ub.setUserid(userId);
        ub.setCreateTime(new Date());
        ub.setUpdateTime(new Date());
        userBindingMapper.insertSelective(ub);
        logger.info("注册UserBingding:{}",jsonUtils.serialize(ub));
    }

    /**
     * 注册用户
     * @return
     * @throws Exception
     */
    private String addUserInfo(String mobile,String userId) throws Exception{
        MallUserInfo mallUserInfo = new MallUserInfo();
        mallUserInfo.setMallId(Constants.MALL_ID);
        mallUserInfo.setUserid(userId);
        mallUserInfo.setMobile(mobile);
        mallUserInfo.setUserType(Constants.USER_TYPE);//0-非实名认证
        mallUserInfo.setUserLevel("");
        mallUserInfo.setRegisterWay(Constants.REGISTER_WAY);//15-智慧云注册方式
        mallUserInfo.setRegisterChannels(Constants.REGISTER_CHANNEL);//1-移动互联网注册渠道
        mallUserInfo.setPost("");
        mallUserInfo.setIsVisitedMember(Constants.IS_VISITED);//0 -未访问过我的商城
        mallUserInfo.setIsCanEdit(Constants.IS_CANEDIT);//0 - 可以编辑
        mallUserInfo.setRegisterTime(new Date());
        mallUserInfo.setModifyTime(new Date());
        mallUserInfoMapper.insertSelective(mallUserInfo);
        logger.info("注册insertUserInfo:{}",jsonUtils.serialize(mallUserInfo));
        return userId;
    }

    /**
     * 新注册用户信息同步至uia
     * @throws Exception
     */
    private void syncUia(Auth auth,String custPwd,String mobile) throws Exception{
        String pwd = UiaEncoder.getEncoder().encrypt(custPwd);
        //添加用户信息到uia中间表
        UiaExternalUser ui = new UiaExternalUser();
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        ui.setId(id);
        ui.setCreatedtime(new Date());
        ui.setLoginId(auth.getLoginId());
        ui.setMobile(mobile);
        ui.setPassword(pwd);
        ui.setStatus("3");//可疑状态
        ui.setUserId(auth.getUserid());
        ui.setUserType("0");//商城用户
        ui.setMallid(Constants.MALL_ID);
        uiaExternalUserMapper.insertSelective(ui);
        logger.info("注册insertUiaExternalUser:{}",jsonUtils.serialize(ui));
        //同步信息到UIA系统
        UiaVp uiaVp = new UiaVp();
        uiaVp.setCurUserId("0");
        uiaVp.setCurSysCode(Constants.CUR_SYS_CODE);
        uiaVp.setSyncType("0");
        List<UiaUserInfoVp> dataList = new ArrayList<UiaUserInfoVp>();
        UiaUserInfoVp uiaUserInfoVp = new UiaUserInfoVp();
        uiaUserInfoVp.setLoginUserName(auth.getLoginId());
        uiaUserInfoVp.setLoginMobilePhone(mobile);
        uiaUserInfoVp.setUserType("1");//外部用户
        uiaUserInfoVp.setMobilePhone(mobile);
        uiaUserInfoVp.setPwd(pwd);
        dataList.add(uiaUserInfoVp);
        uiaVp.setDataList(dataList);
        logger.info("同步uia用户信息参数" + jsonUtils.serialize(uiaVp));

        String rtn = userRegisterServicePrx.userSync(jsonUtils.serialize(uiaVp));
        Map mp = jsonUtils.deserialize(rtn,Map.class);
        String rtnCode = (String) mp.get("rtnCode");
        logger.info("同步uia用户信息返回结果rtnCode" + rtnCode);
        if(!"000000".equals(rtnCode)){//同步失败
            logger.error("注册用户同步到UIA失败！" + rtn);
            throw new Exception(rtnCode+"同步到UIA失败");
        }
        //同步成功则变更uia中间表
        List list = (List) mp.get("succDataList");
        Map m = (Map) list.get(0);
        UiaExternalUser ui1 = new UiaExternalUser();
        ui1.setId(id);
        ui1.setUiaId((String) m.get("userId"));
        ui1.setStatus("1");
        uiaExternalUserMapper.updateByPrimaryKeySelective(ui1);

    }



}
