package com.lemon.profiler.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class SecurityHelper {
	
	//currently the key is set as organizationid
	public String encryptPassword(String password, String key) {
		try {
			// Create key and cipher
			Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			// encrypt the text
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			byte[] encrypted = cipher.doFinal(password.getBytes());
			System.out.println("Encrypted "+password +" to "+new String(encrypted));
			return new String(encrypted);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	//currently the key is set as organizationid
	public String decryptPassword(String password, String key) {
		try {
			Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			// decrypt the text
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			String decrypted = new String(cipher.doFinal(password.getBytes()));
			System.out.println("Decrypted password is "+decrypted+" and the key was "+key);
			return decrypted;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
