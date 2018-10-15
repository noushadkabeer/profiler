package com.lemon.profiler.service;

import com.lemon.profiler.model.Organization;

public interface ApplicationSetupService {
	//setup site, initialize 
    public boolean setupApplication(Organization user);

}
