package com.lemon.profiler.model;

public class User {
	
	public String userId;
	public String userName;
	public String password;
	public String userHome;
	public String userCompanyID;
	public String userOrganization;
	public String userJobTitle;
	public String userLocation;
	
	public User(){}	
	
	public User(String userId, String userName, String password,
			String userHome, String userCompanyID, String userOrganization,
			String userJobTitle, String userLocation) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.userHome = userHome;
		this.userCompanyID = userCompanyID;
		this.userOrganization = userOrganization;
		this.userJobTitle = userJobTitle;
		this.userLocation = userLocation;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserHome() {
		return userHome;
	}
	public void setUserHome(String userHome) {
		this.userHome = userHome;
	}
	public String getUserCompanyID() {
		return userCompanyID;
	}
	public void setUserCompanyID(String userCompanyID) {
		this.userCompanyID = userCompanyID;
	}
	public String getUserOrganization() {
		return userOrganization;
	}
	public void setUserOrganization(String userOrganization) {
		this.userOrganization = userOrganization;
	}
	public String getUserJobTitle() {
		return userJobTitle;
	}
	public void setUserJobTitle(String userJobTitle) {
		this.userJobTitle = userJobTitle;
	}
	public String getUserLocation() {
		return userLocation;
	}
	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}
}
