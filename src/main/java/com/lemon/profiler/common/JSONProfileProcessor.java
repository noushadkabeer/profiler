package com.lemon.profiler.common;

import java.io.FileReader;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONProfileProcessor {
	private static final Logger log = Logger.getLogger(JSONProfileProcessor.class);
	
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
}
