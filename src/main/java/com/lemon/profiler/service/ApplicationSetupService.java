package com.lemon.profiler.service;

import com.lemon.profiler.model.Organization;
import com.lemon.profiler.model.User;

public interface ApplicationSetupService {
	//setup site, initialize 
    public boolean setupApplication(Organization org, User user);
    public Organization getOrganizationAllSites(Organization org);
    public Organization getOrganizationalSite(Organization org);

}
