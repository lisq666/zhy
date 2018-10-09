package com.example.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class BigDecimalUtil {

	//整数格式
	static DecimalFormat dfNum = new DecimalFormat("#");
	
	/**
	 * 将BigDecimal 格式化两位小数
	 * @param amount
	 * @return
	 */
	public static String format(BigDecimal amount) {
		if(null != amount) {
			return new DecimalFormat("0.00").format(amount);
		}
		return new BigDecimal("0.00").toString();
    }
	//非金额费率类转化
	public static String formateToNum(BigDecimal num){
		if(num != null){
			return dfNum.format(num);
		}else{
			return "0";
		}
	}
	/**
	 * 初始化为null的BigDecimal，将BigDecimal 格式化两位小数
	 * @param amount
	 * @return BigDecimal
	 */
	public static BigDecimal initialization(BigDecimal amount) {
		if(null != amount) {
			return new BigDecimal(new DecimalFormat("0.00").format(amount));
		}
		return new BigDecimal("0.00");
	}
	
	/**
	 * 将BigDecimal转成成字符串
	 * @param amount
	 * @return
	 */
	public static String toString(BigDecimal amount) {
		if(null != amount) {
			return new DecimalFormat("#.##").format(amount);
		}
		return null;
	}
	
	/**
	 * 将元为单位的转换为分 （乘100）
	 * @param amount
	 * @return
	 */
	public static int changeY2F(BigDecimal amount) {
		if(null != amount) {
			return amount.multiply(new BigDecimal(100)).intValue();
		}
		return 0;
	}
	

	
}
