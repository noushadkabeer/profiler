package com.lemon.profiler.mappers.pagination;

import com.lemon.profiler.core.pagination.GenericSearchCriteria;

public class ProfileSearchCriteria extends GenericSearchCriteria {
	
	public enum ProfileSearchType {
		BY_ID, BY_NAME
	};

	private ProfileSearchType searchType = ProfileSearchType.BY_NAME;
	private int id;
	private String username;

	public ProfileSearchType getSearchType() {
		return searchType;
	}

	public void setSearchType(ProfileSearchType searchType) {
		this.searchType = searchType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}