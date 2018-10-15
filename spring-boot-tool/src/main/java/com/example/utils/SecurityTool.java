package com.example.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

// 安全工具类
public class SecurityTool {

    // 获取参数签名
    public static String getSignature(Map<String, String> paramValues, List<String> ignoreParamNames)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        List<String> paramNames = new ArrayList<String>(paramValues.size());
        paramNames.addAll(paramValues.keySet());
        if (ignoreParamNames != null && ignoreParamNames.size() > 0) {
            for (String ignoreParamName : ignoreParamNames) {
                paramNames.remove(ignoreParamName);
            }
        }
        // 降序
        Collections.sort(paramNames,new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return -a.compareTo(b);
            }
        });
        String secret = getSecret();
        sb.append(secret);
        sb.append("&-#^%$");
        for (String paramName : paramNames) {
            sb.append(paramName).append(paramValues.get(paramName));
        }
        sb.append(secret);
        MessageDigest md = MessageDigest.getInstance("MD5");
        return byte2hex(md.digest(sb.toString().getBytes("UTF-8")));
    }

    //读取key.properties里的ZHY.KEY的值
    private static String getSecret(){
        ResourceBundle bundle= PropertyResourceBundle.getBundle("key");
        return bundle.getString("zhy.key");
    }

    private static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i]>>3 & 0xF2);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }


}
