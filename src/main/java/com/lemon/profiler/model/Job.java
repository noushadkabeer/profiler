package com.lemon.profiler.model;

import java.io.Serializable;

public class Job implements Serializable{
	/**
	 * ID , Title,
Start Date,
Duration,
Company, 
Type - Contract, C2h, Freelance, Full Time, Part time
Status - Active, On hold, Closed/Cancelled, Coming, All
Recruiter
Owner
Department
Salary
City
State
Country
Maximum Rate:
Openings
Company Job ID
Hot
Public
Description
Comments
	 */
	private static final long serialVersionUID = 3345034734855825983L;
	public String jobId;
	public String jobExperience;
	public String jobTitle;
	public String company;
	public String location;
	public String jobType;
	public String salary;
	public String referenceCode;
	public String contactInfo;
	public String aboutJob;
	public String noOfVaccancies;
	
	public Job(){}
	
	public Job(String jobId, String jobExperience, String jobTitle,
			String company, String location, String jobType, String salary,
			String referenceCode, String contactInfo, String aboutJob,
			String noOfVaccancies) {
		super();
		this.jobId = jobId;
		this.jobExperience = jobExperience;
		this.jobTitle = jobTitle;
		this.company = company;
		this.location = location;
		this.jobType = jobType;
		this.salary = salary;
		this.referenceCode = referenceCode;
		this.contactInfo = contactInfo;
		this.aboutJob = aboutJob;
		this.noOfVaccancies = noOfVaccancies;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getJobExperience() {
		return jobExperience;
	}
	public void setJobExperience(String jobExperience) {
		this.jobExperience = jobExperience;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getReferenceCode() {
		return referenceCode;
	}
	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}
	public String getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
	public String getAboutJob() {
		return aboutJob;
	}
	public void setAboutJob(String aboutJob) {
		this.aboutJob = aboutJob;
	}
	public String getNoOfVaccancies() {
		return noOfVaccancies;
	}
	public void setNoOfVaccancies(String noOfVaccancies) {
		this.noOfVaccancies = noOfVaccancies;
	}

}
