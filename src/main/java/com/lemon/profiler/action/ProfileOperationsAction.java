package com.lemon.profiler.action;

import com.lemon.profiler.service.ProfileService;
import com.lemon.profiler.service.impl.ProfileServiceImpl;
 

public class ProfileOperationsAction {
	
	private static ProfileService profileService = new ProfileServiceImpl(); 
	public String getAllProfiles(){ 
   	 return "success";
   }


}
