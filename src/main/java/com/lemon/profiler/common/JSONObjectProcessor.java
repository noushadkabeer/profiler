package com.lemon.profiler.common;

import java.io.FileReader;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.lemon.profiler.model.User;

public class JSONObjectProcessor {
	private static final Logger log = Logger.getLogger(JSONObjectProcessor.class);
	
	public static void main(String args[]){
		JSONParser parser = new JSONParser();
		try{
			Object obj = parser.parse(new FileReader("C:\\Noushad\\rnd\\resumeparser\\code4goal-resume-parser-master\\c4g v2\\compiled\\178863526Nancy Buan CV.doc.json"));
			JSONObject jsonObject =(JSONObject) obj;
			String fname = (String) jsonObject.get("firstname");
			String email = (String) jsonObject.get("email");
			log.info(fname + " :: "+ email);
		}catch(Exception e){
			
		}
	}
	
	public User returnProcessedUser(JSONObject jsonObject) {
		User u = null;
		try {
			//Object obj = parser.parse(new FileReader("C:\\Noushad\\rnd\\resumeparser\\code4goal-resume-parser-master\\c4g v2\\compiled\\178863526Nancy Buan CV.doc.json"));
			//JSONObject jsonObject =(JSONObject) obj;
			u = new User();
		//	String name = jsonObject.get("firstName") == null? "": jsonObject.get("firstName").toString() + " "+  jsonObject.get("lastName") == null? "": jsonObject.get("lastName").toString() ;
			u.setFirstName(jsonObject.get("firstName") == null? "": jsonObject.get("firstName").toString() );
			u.setLastName(jsonObject.get("lastName") == null? "": jsonObject.get("lastName").toString());
			u.setUserName(jsonObject.get("userName")  == null? "": jsonObject.get("userName").toString());
			u.setUserId(jsonObject.get("userName")  == null? "": jsonObject.get("userName").toString());
			u.setUserCompanyID(jsonObject.get("organizationId")  == null? "": jsonObject.get("organizationId").toString());
			u.setUserOrganization(jsonObject.get("organization")  == null? "": jsonObject.get("organization").toString());
			u.setUserJobTitle(jsonObject.get("jobtitle")  == null? "": jsonObject.get("jobtitle").toString());
			u.setUserLocation(jsonObject.get("location")  == null? "": jsonObject.get("location").toString());
			u.setUserEmail(jsonObject.get("email")  == null? "": jsonObject.get("email").toString());
			u.setMobile(jsonObject.get("mobile")  == null? "": jsonObject.get("mobile").toString());
			//TODO fix this read as jsonObject.get??
			u.setIsAdmin(jsonObject.get("capabilities.isAdmin")  == null? "": jsonObject.get("capabilities.isAdmin").toString());
			
			
			u.setCompanyemail(jsonObject.get("companyemail")  == null? "": jsonObject.get("companyemail").toString());
			u.setPersondescription(jsonObject.get("persondescription")  == null? "": jsonObject.get("persondescription").toString());
			u.setInstantmsg(jsonObject.get("instantmsg")  == null? "": jsonObject.get("instantmsg").toString());
			u.setCompanyTelephone(jsonObject.get("companytelephone")  == null? "": jsonObject.get("companytelephone").toString());
			u.setCompanypostcode(jsonObject.get("companypostcode")  == null? "": jsonObject.get("companypostcode").toString());
			u.setCompanyaddress1(jsonObject.get("companyaddress1")  == null? "": jsonObject.get("companyaddress1").toString());
			u.setCompanyaddress2(jsonObject.get("companyaddress2")  == null? "": jsonObject.get("companyaddress2").toString());
			
			u.setAvatar(jsonObject.get("avatar")  == null? "": jsonObject.get("avatar").toString());
			u.setUserStatusTime(jsonObject.get("userStatusTime")  == null? "": jsonObject.get("userStatusTime").toString());
			u.setUserStatus(jsonObject.get("userStatus")  == null? "": jsonObject.get("userStatus").toString());
			
//			u.setUserName(jsonObject.get("username")  == null? "": jsonObject.get("username").toString());
			log.info(" :: ");
			return u;
			
		}catch(Exception e) {
			log.error("Error in converting user Object from JSONObject");;
		}
		return null;
	}
}
