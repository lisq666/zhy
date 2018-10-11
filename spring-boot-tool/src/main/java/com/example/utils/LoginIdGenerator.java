package com.example.utils;

import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginIdGenerator {
	private final static String FORBIDDEN_NAMES = "UnallowedMallLoginIds";
	public final static Logger log = LoggerFactory.getLogger(LoginIdGenerator.class);

	/**
	 * 此方法已做数据库验证重复
	 */
	public static String getRandomLoginId() throws Exception{
		String loginId="gxyj_"+getStringRandom(12);
		try {
			//未通过验证就重新生成loginId
			while(!hasCheckedLoginId(loginId)){
				loginId="gxyj_"+getStringRandom(12);
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new Exception(e.getMessage());
		}
		return loginId;	
	}


	/**
	 * 随机数字、字母产生器
	 */
	private static String getStringRandom(int length) {
		String val = "";
		Random random = new Random();
		//参数length，表示生成几位随机数
		for(int i = 0; i < length; i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			//输出字母还是数字
			if( "char".equalsIgnoreCase(charOrNum) ) {
				//输出小写字母
				val += (char)(random.nextInt(26) + 97);
			} else if( "num".equalsIgnoreCase(charOrNum) ) {
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}

	/**
	 * 通过验证重复、禁用名单 就返回true 否则false
	 */
	private static boolean hasCheckedLoginId(String loginId) throws Exception{
		//检查生成loginId是否被禁用
		//String forbiddenNames = ApplicationConfigUtils.getInstance().getPropertiesValue(FORBIDDEN_NAMES);
		String forbiddenNames = "admin;system;master;i2f";
		String[] names =null;
		if (forbiddenNames != null)
			names = forbiddenNames.split(";");
		for(String name:names){
			if(loginId.equals(name)){
				return false;
			}
		}
		return true;
	}
}
