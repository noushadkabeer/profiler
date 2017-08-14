package com.lemon.profiler.util;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.google.gson.Gson;
import com.lemon.profiler.model.Profile;

public class ProfileJSON2JavaMapper {
	static Gson gson = new Gson();

	 public static void main(String[] args) {

	        
	        try {
	        	Reader reader = new FileReader("C:\\Noushad\\rnd\\resumeparser\\testvalues\\profile.json");
				// Convert JSON to Java Object
	        	Profile profile = gson.fromJson(reader, Profile.class);
	            System.out.println(profile.education);

				// Convert JSON to JsonElement, and later to String
	            /*JsonElement json = gson.fromJson(reader, JsonElement.class);
	            String jsonInString = gson.toJson(json);
	            System.out.println(jsonInString);*/

	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	    }
	 
	 public Profile mappedProfile(String jsonStringContent){
		 Profile profile = gson.fromJson(jsonStringContent, Profile.class);
		 return profile;
	 }
}
