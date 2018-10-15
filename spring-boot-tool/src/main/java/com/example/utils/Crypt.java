package com.example.utils;

import java.util.Arrays;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;


public class Crypt {

    private final static String[] PASSWORDS = {"1","2","3","4","5","6","7","8","9","0",
            "a","b","c","d","e","f","g","h","j",
            "Q","W","E","R","T","Y","U","P","K"};
    /**
     * 密码加密规则
     * @param userId
     * @param password
     * @return
     */
    public static String encrypte(String userId,String password){
        String encryptedByte = Md5Util.MD5((Md5Util.MD5(userId.getBytes()) + Md5Util.MD5(password.getBytes())).getBytes());
        String encryptedData = Base64.encodeBase64String(encryptedByte.getBytes());
        return encryptedData;
    }

    /**
     * 生成随机密码
     * @return
     */
    public static String createRandomPwd() {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        String[] englishArrays = Arrays.copyOfRange(PASSWORDS, 10, 25);
        sb.append(englishArrays[random.nextInt(englishArrays.length)]);
        for (int i = 0; i < 6; i++) {
            sb.append(PASSWORDS[random.nextInt(PASSWORDS.length)]);
        }
        sb.append(PASSWORDS[random.nextInt(9)]);
        return sb.toString();
    }



}
