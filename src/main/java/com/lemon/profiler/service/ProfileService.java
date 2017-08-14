package com.lemon.profiler.service;

import java.util.List;

import com.lemon.profiler.mappers.pagination.ProfileSearchCriteria;
import com.lemon.profiler.mappers.pagination.ProfileSearchResults;
import com.lemon.profiler.model.Profile;

public interface ProfileService {
	public List<Profile> obtainAllProfiles(String pagenum, String pageSize);
	public Profile pullProfile(String id);
	public Profile pullProfileByName(String name);
    public void update(Profile profile);
    public void insert(Profile profile);
    public String delete(String id);
    public List<Profile> searchProfile(String searchString, String pageNum, String pageSize);
    public List<Profile> advSearchProfile(Profile profile, String pageNum, String pageSize);
    
    
    public ProfileSearchResults<Profile> obtainAllProfiles(ProfileSearchCriteria psc);
    
}
