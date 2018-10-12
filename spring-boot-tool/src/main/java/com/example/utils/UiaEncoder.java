package com.example.utils;

import java.io.File;
import java.net.URLDecoder;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import com.echinacoop.uia.crypto.encoder.Encoder;

public class UiaEncoder {

	private static Encoder encoder;

	public static Encoder getEncoder() throws Exception{
		if(null==encoder){
			synchronized (UiaEncoder.class) {
				if(null==encoder){
					String path = UiaEncoder.class.getClassLoader().getResource("").getPath();
					File file = new File(path+ "public.key");
					String publicKey = file.getAbsolutePath();
					publicKey = URLDecoder.decode(publicKey, "UTF-8");
					ResourceBundle bundle= PropertyResourceBundle.getBundle("key");
					String a = bundle.getString("uia.key");
					encoder = new Encoder(publicKey, bundle.getString("uia.key"));
				}
			}
		}
		return encoder;
	}

	
}
