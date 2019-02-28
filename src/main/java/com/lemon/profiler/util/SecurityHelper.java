package com.lemon.profiler.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class SecurityHelper {
	
	//OBSOLETE!! currently the key is set as organizationid
	public String encryptPassword(String password, String key) {
		try {
			// Create key and cipher
			Key aesKey = new SecretKeySpec(key.getBytes(), "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			// encrypt the text
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			byte[] encrypted = cipher.doFinal(password.getBytes());
			System.out.println("Encrypted "+password +" to "+Base64.getEncoder().encodeToString(encrypted) );
			return new String(encrypted);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	//OBSOLETE!! currently the key is set as organizationid
	public String decryptPassword(String password, String key) {
		try {
			Key aesKey = new SecretKeySpec(key.getBytes(), "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
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
	
    public String initiateSecurityByPasswordEncryption(String passwordToEncrypt, String keytoEncrypt) {
    	try {
    		System.out.println("Encryption initiated ");
        if (keytoEncrypt != null) {
	        // The salt (probably) can be stored along with the encrypted data
	        byte[] salt = new String("12345678").getBytes();
	        // Decreasing this speeds down startup time and can be useful during testing, but it also makes it easier for brute force attackers
	        int iterationCount = 40000;
	        // Other values give me java.security.InvalidKeyException: Illegal key size or default parameters
	        int keyLength = 128;
	        SecretKeySpec key = createSecretKey(keytoEncrypt.toCharArray(),
	                salt, iterationCount, keyLength);
	        String originalPassword = passwordToEncrypt;
	        System.out.println("Original password: " + originalPassword);
	        return encrypt(originalPassword, key);
        }}catch(Exception e) {
        	e.printStackTrace();
        }
        return "";
    }
	
    public String completeSecurityByPasswordDecryption(String passwordToDecrypt, String keytoDecrypt){
    	System.out.println("Decrpting");
    	try {
        if (keytoDecrypt != null) {
	        // The salt (probably) can be stored along with the encrypted data
	        byte[] salt = new String("12345678").getBytes();
	        // Decreasing this speeds down startup time and can be useful during testing, but it also makes it easier for brute force attackers
	        int iterationCount = 40000;
	        // Other values give me java.security.InvalidKeyException: Illegal key size or default parameters
	        int keyLength = 128;
	        SecretKeySpec key = createSecretKey(keytoDecrypt.toCharArray(),
	                salt, iterationCount, keyLength);
	        return  decrypt(passwordToDecrypt, key); 
        }}catch(Exception e){
        	e.printStackTrace();
        }
        
        return "";
    }
    
	 private static SecretKeySpec createSecretKey(char[] password, byte[] salt, int iterationCount, int keyLength) throws NoSuchAlgorithmException, InvalidKeySpecException {
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
	        PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
	        SecretKey keyTmp = keyFactory.generateSecret(keySpec);
	        return new SecretKeySpec(keyTmp.getEncoded(), "AES");
	    }

	    private static String encrypt(String property, SecretKeySpec key) throws GeneralSecurityException, UnsupportedEncodingException {
	        Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        pbeCipher.init(Cipher.ENCRYPT_MODE, key);
	        AlgorithmParameters parameters = pbeCipher.getParameters();
	        IvParameterSpec ivParameterSpec = parameters.getParameterSpec(IvParameterSpec.class);
	        byte[] cryptoText = pbeCipher.doFinal(property.getBytes("UTF-8"));
	        byte[] iv = ivParameterSpec.getIV();
	        return base64Encode(iv) + ":" + base64Encode(cryptoText);
	    }

	    private static String base64Encode(byte[] bytes) {
	        return Base64.getEncoder().encodeToString(bytes);
	    }

	    private static String decrypt(String string, SecretKeySpec key) throws GeneralSecurityException, IOException {
	        String iv = string.split(":")[0];
	        String property = string.split(":")[1];
	        Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        pbeCipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(base64Decode(iv)));
	        return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
	    }

	    private static byte[] base64Decode(String property) throws IOException {
	        return Base64.getDecoder().decode(property);
	    }
}
