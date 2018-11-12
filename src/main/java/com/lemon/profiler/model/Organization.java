package com.lemon.profiler.model;

public class Organization {
//	shortName
//	the shortName of the web site, mandatory, must be unique
//	sitePreset
//	the sitePreset
//	title
//	the title of the web site
//	description
//	the description for the web site
//	visibility
//	the site visibility, one of (PUBLIC,MODERATED,PRIVATE), defaults to PUBLIC
//	type
//	the type of site to create, optional
	
	public String shortName;
	public String title;
	public String description;
	public String type;
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}

