package com.lemon.profiler.util;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class ValidationHelper {
	
	public static boolean isValidEmailAddress(String email) {
		System.out.print("Validating :"+email);
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   System.out.println(">>"+result);
		   return result;
		}
}
