package com.lemon.profiler.action;

import java.io.File;

import org.apache.log4j.Logger;

import com.lemon.profiler.util.ResumeParsingThread;
import com.lemon.profiler.util.ResumeUploader;

public class BulkUploadProcessorAction {
	
	private static final Logger log = Logger.getLogger(BulkUploadProcessorAction.class);

	public String filesPath;

	public String getFilesPath() {
		return filesPath;
	}

	public void setFilesPath(String filesPath) {
		this.filesPath = filesPath;
	}

	public String bulkUploadProcessor() throws Exception {
		log.info("Filespath >>" + filesPath);
		try {
			if ((filesPath == null || filesPath.equals(""))) {
				return "input";
			} else {
				log.info("Processing files in " + filesPath);
				ResumeParsingThread rpT = new ResumeParsingThread();
				rpT.initiateProcess(filesPath);
				// log.info("Generated target files in "+outputPath);
				
			
				//Finally Upload the files
				ResumeUploader ru = new ResumeUploader();
			//	ru.uploadFiles(file, processed);
				File jsonFile; 
				File folder = new File(filesPath);
				File[] listOfFiles = folder.listFiles();
				int oz =1;
				for (File file : listOfFiles) {
					if (file.isFile()) {
						System.out.println("Processing file : " + file.getName() + " " + oz + " th file out of " + listOfFiles.length);
						System.out.println("Normal file? "+folder+"\\temp\\"+file.getName()+".json");
						jsonFile = new File(folder+"\\temp\\"+file.getName()+".json");
						if(jsonFile.isFile()) System.out.println("Processed JSON file found for "+jsonFile.getName());
						ru.tryParsing(jsonFile);
						ru.uploadFiles(file, jsonFile);
//						 BufferedReader br = new BufferedReader(new FileReader(jsonFile));
//						 String line = null;
//						 while ((line = br.readLine()) != null) {
//						   System.out.println(line);
//						 }
					}
					oz++;
				}
				
				return "success";
			}

			// log.info("Recieved file of type"+fileContentType);
			// String returnV = "failure";
			// // String status = "";
			// fileService = new FileUploadImpl();
			// propertyService = new PropertyReaderServiceImpl();
			// //InputStream inputStream;
			// try {
			// //returnV =
			// fileService.uploadDocument(getFileUpload(),getFileUploadFileName(),
			// filesPath);
			// returnV = fileService.uploadDocument(profile.getId(),
			// getFile(),getFileFileName(), fileContentType, filesPath);
			// log.info(returnV);
			// returnV = propertyService.getKeyValue("contentServerURL") +
			// propertyService.getKeyValue("contentFolderURL")
			// + returnV;
			// log.info(returnV);
			// inputStream = new StringBufferInputStream(returnV);

		} catch (Exception e) {
			return "connection-failed";
		}
	}

}
