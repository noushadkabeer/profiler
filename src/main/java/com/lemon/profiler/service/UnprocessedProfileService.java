package com.lemon.profiler.service;

import java.util.List;

import com.lemon.profiler.mappers.pagination.UnprocessedProfileSearchCriteria;
import com.lemon.profiler.mappers.pagination.UnprocessedProfileSearchResults;
import com.lemon.profiler.model.Profile;
 

public interface UnprocessedProfileService {
	public List<Profile> obtainUnProcessedProfileList(String textToSearch, String pageNum, String pageSize);
	public String obtainUnProcessedProfileCount(String textToSearch, String pageNum, String pageSize);
    public void insertUPProfile(Profile profile);
    
    
    //New replacement for obtainUnprocessedprofilelist metod
    public UnprocessedProfileSearchResults<Profile> obtainUnProcessedProfileList(UnprocessedProfileSearchCriteria upsc);
}
