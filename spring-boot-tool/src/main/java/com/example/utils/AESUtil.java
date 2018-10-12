package com.example.utils;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class AESUtil {

    private static final String ALGORITHM = "AES";
    private static final String ENCODING = "UTF-8";

    /**
     * AEC加密
     * @param sSrc
     * @return
     * @throws Exception
     */
    public static String Encrypt(String sSrc) throws Exception {
        byte[] raw = getSecret().getBytes(ENCODING);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, ALGORITHM);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes(ENCODING));
        return new Base64().encodeToString(encrypted);
    }

    /**
     * AEC解密
     * @param sSrc
     * @return
     * @throws Exception
     */
    public static String Decrypt(String sSrc) throws Exception {
        byte[] raw = getSecret().getBytes(ENCODING);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, ALGORITHM);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] encrypted1 = new Base64().decode(sSrc);
        byte[] original = cipher.doFinal(encrypted1);
        String originalString = new String(original,ENCODING);
        return originalString;

    }

    //读取key.properties里的ZHY.AES.KEY的值
    private static String getSecret(){
        ResourceBundle bundle= PropertyResourceBundle.getBundle("key");
        return bundle.getString("zhy.aes.key");
    }

}

