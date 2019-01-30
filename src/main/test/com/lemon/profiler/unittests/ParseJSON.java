package com.lemon.profiler.unittests;

import com.google.gson.Gson;
import com.lemon.profiler.model.Profile;

public class ParseJSON {
	static Gson gson = new Gson();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String jsonStringContent= "{\"name\": \"Sudhanan\", \"contactNo\": \"34342323\", \"email\": \"m.sudhanan1982@gmail.com\", \"dob\": \"1970-01-20\", \"skills\": []}";
		Profile profile = gson.fromJson(jsonStringContent, Profile.class);
		System.out.println(profile.getName());
		System.out.println(profile.getContactNo());

	}

}
