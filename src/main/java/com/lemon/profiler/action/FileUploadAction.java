package com.lemon.profiler.action;


import java.io.File;
import java.io.InputStream;
import java.io.StringBufferInputStream;

import org.apache.log4j.Logger;

import com.lemon.profiler.exceptions.ConnectionFailedException;
import com.lemon.profiler.exceptions.UnauthorizedException;
import com.lemon.profiler.model.Profile;
import com.lemon.profiler.service.AlfrescoFileService;
import com.lemon.profiler.service.PropertyReaderService;
import com.lemon.profiler.service.impl.FileUploadImpl;
import com.lemon.profiler.service.impl.PropertyReaderServiceImpl;

public class FileUploadAction {
	
	
	private static final Logger log = Logger.getLogger(FileUploadAction.class);
	public String fileContentType; 
	
	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	

//	public File fileUpload;
//	public File getFileUpload() {
//		return fileUpload;
//	}
//
//	public void setFileUpload(File fileUpload) {
//		this.fileUpload = fileUpload;
//	}


	public Profile profile;
	
	
	
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}


	public File file;
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String fileFileName;
	
	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	
//	public String fileUploadFileName;
//
//	public String getFileUploadFileName() {
//		return fileUploadFileName;
//	}
//
//	public void setFileUploadFileName(String fileUploadFileName) {
//		this.fileUploadFileName = fileUploadFileName;
//	}



	public String getFilesPath() {
		return filesPath;
	}

	public void setFilesPath(String filesPath) {
		this.filesPath = filesPath;
	}

	public String filesPath;
	private AlfrescoFileService fileService;
	private PropertyReaderService propertyService;
	private InputStream inputStream;
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String uploadDoc() throws Exception {
		log.info("Recieved file of type"+fileContentType);
		String returnV = "failure";
		// String status = "";
		fileService = new FileUploadImpl();
		propertyService = new PropertyReaderServiceImpl();
		//InputStream inputStream;
		try {
			//returnV = fileService.uploadDocument(getFileUpload(),getFileUploadFileName(), filesPath);
			returnV = fileService.uploadDocument(profile.getId(), getFile(),getFileFileName(), fileContentType, filesPath);
			log.info(returnV);
			returnV = propertyService.getKeyValue("contentServerURL") + propertyService.getKeyValue("contentFolderURL")
					+ returnV;
			log.info(returnV);
			inputStream = new StringBufferInputStream(returnV);

		} catch (UnauthorizedException e) {
			return "unauthorized";
		} catch (ConnectionFailedException e) {
			return "connection-failed";
		}

		return "success";
	}
}
