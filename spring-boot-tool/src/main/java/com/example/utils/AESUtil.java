package com.example.utils;

import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {

    private final static String KEY = "1234567812345678";

    /**
     * @desc AES解密（安卓）
     * @param content
     * @return
     */
    public static String decrypt4AES(String content) {
        try {
            byte[] decryptFrom = parseHexStr2Byte(content);
            byte[] iv = KEY.getBytes("utf-8");
            IvParameterSpec zeroIv = new IvParameterSpec(iv);
            SecretKeySpec key1 = new SecretKeySpec(iv,"AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key1, zeroIv);
            byte decryptedData[] = cipher.doFinal(decryptFrom);
            return new String(decryptedData); // 加密
        } catch (Exception e) {
            e.printStackTrace();
            return"";
        }
    }

    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }


    /**
     * @desc AES解密（IOS）
     * @param content
     * @return
     * @throws Exception
     */
    public static String desEncrypt(String content) {
        try{
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(content);
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keyspec = new SecretKeySpec(KEY.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(KEY.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original);
            return originalString;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
