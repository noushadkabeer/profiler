package com.lemon.profiler.model;

import java.io.Serializable;
import java.util.List;

public class Profile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String id;	
	public String name;
	//21/11 Added
	public String email;
	public String contactNo;
	
	public String experience;
	public String education;
	public String dob;
	
	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}
	public List<String> skills;
	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	public String interests;
	public String location;
	public String address;
	public String resumeSummary;
	
	public Attachment attachments;


	public Profile(){}
	
//	public Profile(String id, String name, String experience, String education,
//			String skills, String interests, String location, String address,
//			String resumeSummary) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.experience = experience;
//		this.education = education;
//		this.skills = skills;
//		this.interests = interests;
//		this.location = location;
//		this.address = address;
//		this.resumeSummary = resumeSummary;
//	}
	
	
	

	public String getId() {
		return id;
	}

	public Profile(String id, String name, String email, String contactNo, String dob, String experience, String education,
			List<String> skills, String interests, String location, String address, String resumeSummary,
			Attachment attachments) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.contactNo = contactNo;
		this.dob = dob;
		this.experience = experience;
		this.education = education;
		this.skills = skills;
		this.interests = interests;
		this.location = location;
		this.address = address;
		this.resumeSummary = resumeSummary;
		this.attachments = attachments;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}

	public String getInterests() {
		return interests;
	}
	public void setInterests(String interests) {
		this.interests = interests;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getResumeSummary() {
		return resumeSummary;
	}
	public void setResumeSummary(String resumeSummary) {
		this.resumeSummary = resumeSummary;
	}	
	public Attachment getAttachments() {
		return attachments;
	}
	public void setAttachments(Attachment attachments) {
		this.attachments = attachments;
	}
}
