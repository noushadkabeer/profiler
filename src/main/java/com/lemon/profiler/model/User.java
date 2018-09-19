package com.lemon.profiler.model;

public class User {
	
	public String userId;
	public String userName;
	public String firstName;
	public String lastName;
	public String password;
	public String userHome;
	public String userCompanyID;
	public String userOrganization;
	public String userJobTitle;
	public String userLocation;
	
	public String userEmail;
	public String mobile;
	public String isAdmin;
	public String companyemail;
	public String companyfax;
	public String companyTelephone;
	public String companypostcode;
	public String persondescription;
	public String companyaddress2;
	public String companyaddress1;
	public String instantmsg;
	public String avatar;
	public String userStatusTime;
	public String userStatus;
	
	//new fields
	public String language;
	public String country;
	public String timezone;
	public String timeformat;
	public String salutation;

	public String memberSince;


	public User(){}


	public User(String userId, String userName, String firstName, String lastName, String password, String userHome,
			String userCompanyID, String userOrganization, String userJobTitle, String userLocation, String userEmail,
			String mobile, String isAdmin, String companyemail, String companyfax, String companyTelephone,
			String companypostcode, String persondescription, String companyaddress2, String companyaddress1,
			String instantmsg, String avatar, String userStatusTime, String userStatus, String language, String country,
			String timezone, String timeformat, String salutation, String memberSince) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.userHome = userHome;
		this.userCompanyID = userCompanyID;
		this.userOrganization = userOrganization;
		this.userJobTitle = userJobTitle;
		this.userLocation = userLocation;
		this.userEmail = userEmail;
		this.mobile = mobile;
		this.isAdmin = isAdmin;
		this.companyemail = companyemail;
		this.companyfax = companyfax;
		this.companyTelephone = companyTelephone;
		this.companypostcode = companypostcode;
		this.persondescription = persondescription;
		this.companyaddress2 = companyaddress2;
		this.companyaddress1 = companyaddress1;
		this.instantmsg = instantmsg;
		this.avatar = avatar;
		this.userStatusTime = userStatusTime;
		this.userStatus = userStatus;
		this.language = language;
		this.country = country;
		this.timezone = timezone;
		this.timeformat = timeformat;
		this.salutation = salutation;
		this.memberSince = memberSince;
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


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
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


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getIsAdmin() {
		return isAdmin;
	}


	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}


	public String getCompanyemail() {
		return companyemail;
	}


	public void setCompanyemail(String companyemail) {
		this.companyemail = companyemail;
	}


	public String getCompanyfax() {
		return companyfax;
	}


	public void setCompanyfax(String companyfax) {
		this.companyfax = companyfax;
	}


	public String getCompanyTelephone() {
		return companyTelephone;
	}


	public void setCompanyTelephone(String companyTelephone) {
		this.companyTelephone = companyTelephone;
	}


	public String getCompanypostcode() {
		return companypostcode;
	}


	public void setCompanypostcode(String companypostcode) {
		this.companypostcode = companypostcode;
	}


	public String getPersondescription() {
		return persondescription;
	}


	public void setPersondescription(String persondescription) {
		this.persondescription = persondescription;
	}


	public String getCompanyaddress2() {
		return companyaddress2;
	}


	public void setCompanyaddress2(String companyaddress2) {
		this.companyaddress2 = companyaddress2;
	}


	public String getCompanyaddress1() {
		return companyaddress1;
	}


	public void setCompanyaddress1(String companyaddress1) {
		this.companyaddress1 = companyaddress1;
	}


	public String getInstantmsg() {
		return instantmsg;
	}


	public void setInstantmsg(String instantmsg) {
		this.instantmsg = instantmsg;
	}


	public String getAvatar() {
		return avatar;
	}


	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}


	public String getUserStatusTime() {
		return userStatusTime;
	}


	public void setUserStatusTime(String userStatusTime) {
		this.userStatusTime = userStatusTime;
	}


	public String getUserStatus() {
		return userStatus;
	}


	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}	
	
	

	public String getLanguage() {
		return language;
	}



	public void setLanguage(String language) {
		this.language = language;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	public String getTimezone() {
		return timezone;
	}



	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}



	public String getTimeformat() {
		return timeformat;
	}



	public void setTimeformat(String timeformat) {
		this.timeformat = timeformat;
	}



	public String getSalutation() {
		return salutation;
	}



	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getMemberSince() {
		return memberSince;
	}


	public void setMemberSince(String memberSince) {
		this.memberSince = memberSince;
	}



}
