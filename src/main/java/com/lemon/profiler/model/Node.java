package com.lemon.profiler.model;


public class Node
{
  private String id;
  private String name;
  private boolean space;
  private String createdDate;
  private String modifier;
  private String modifiedDate;
  private String mimetype;
  private String size;
  private String author;
  private String creator;
  private String path;
  private String icon16;
  private String icon32;
  private String encoding;

public String getEncoding() {
	return encoding;
}

public void setEncoding(String encoding) {
	this.encoding = encoding;
}

public String getIcon16() {
	return icon16;
}

public void setIcon16(String icon16) {
	this.icon16 = icon16;
}

public String getIcon32() {
	return icon32;
}

public void setIcon32(String icon32) {
	this.icon32 = icon32;
}

public String getPath() {
	return path;
}

public void setPath(String path) {
	this.path = path;
}

public Node(){}

  public Node(String id, String name, boolean space){}
  



public Node(String id, String name, boolean space, String createdDate,
		String modifier, String modifiedDate, String mimetype, String size,
		String author, String creator, String path) {
	super();
	this.id = id;
	this.name = name;
	this.space = space;
	this.createdDate = createdDate;
	this.modifier = modifier;
	this.modifiedDate = modifiedDate;
	this.mimetype = mimetype;
	this.size = size;
	this.author = author;
	this.creator = creator;
	this.path = path;
}

public String getCreator() {
	return creator;
}
public void setCreator(String creator) {
	this.creator = creator;
}
public String getCreatedDate() {
	return createdDate;
}
public void setCreatedDate(String createdDate) {
	this.createdDate = createdDate;
}
public String getModifier() {
	return modifier;
}
public void setModifier(String modifier) {
	this.modifier = modifier;
}
public String getModifiedDate() {
	return modifiedDate;
}
public void setModifiedDate(String modifiedDate) {
	this.modifiedDate = modifiedDate;
}
public String getMimetype() {
	return mimetype;
}
public void setMimetype(String mimetype) {
	this.mimetype = mimetype;
}
public String getSize() {
	return size;
}
public void setSize(String size) {
	this.size = size;
}
public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}


  public void setId(String id)
  {
    this.id = id;
  }

  public String getId() {
    return this.id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public boolean isSpace() {
    return this.space;
  }

  public void setSpace(boolean space) {
    this.space = space;
  }
}