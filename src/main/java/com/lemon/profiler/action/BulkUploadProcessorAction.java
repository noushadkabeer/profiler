package com.lemon.profiler.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.io.FilenameUtils;
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
				// Assume Convertion is complete by now
				// Call Py to parse
				log.info("<<=====================Proceeding with PYTHON=======================>");

				String[] cmd = { "py", "C:/Users/emper/PycharmProjects/profileR/venv/One.py", filesPath + "temp\\", };
				Process p = Runtime.getRuntime().exec(cmd);
				String s = null;
				BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

				BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

				// read the output from the command
				// System.out.println("Here is the standard output of the command:\n");
				// while ((s = stdInput.readLine()) != null) {
				// log.info("Output(Op) from py: "+s);
				// }

				// read any errors from the attempted command
				// System.out.println("Here is the standard error of the command (if any):\n");
				// while ((s = stdError.readLine()) != null) {
				// log.info("Output(Err) from py: "+s);
				// }
				// p.waitFor();
				log.info("Py execution complete " + s);

				try {
					ProcessBuilder pb = new ProcessBuilder(cmd);
					Process p2 = pb.start();
					BufferedReader stdInput2 = new BufferedReader(new InputStreamReader(p.getInputStream()));
					BufferedReader stdError2 = new BufferedReader(new InputStreamReader(p.getErrorStream()));
					StringBuffer response = new StringBuffer();
					StringBuffer errorStr = new StringBuffer();
					boolean alreadyWaited = false;
					while (p.isAlive()) {
						try {
							if (alreadyWaited) {

								// read the output from the command because
								// if we don't then the buffers fill up and
								// the command stops and doesn't return
								String temp;

								while ((temp = stdInput.readLine()) != null) {
									response.append(temp);
								}

								String errTemp;
								while ((errTemp = stdError.readLine()) != null) {
									errorStr.append(errTemp);
								}
							}
							Thread.sleep(1000);
							alreadyWaited = true;
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						log.info("Response is " + response);
						log.info("Error is: " + errorStr);
					}

				} catch (IOException e) {
					log.error("Error running system command", e);
				}

				// Finally Upload the files
				ResumeUploader ru = new ResumeUploader();
				// ru.uploadFiles(file, processed);
				File jsonFile;
				File folder = new File(filesPath);
				File[] listOfFiles = folder.listFiles();
				int oz = 1;
				for (File file : listOfFiles) {
					if (file.isFile()) {
						jsonFile = new File(folder + "\\temp\\" + FilenameUtils.removeExtension(file.getName()) + "\\"
								+ FilenameUtils.removeExtension(file.getName()) + ".pdf.json");
						// if(jsonFile.isFile()) System.out.println("Processed JSON file found for
						// "+jsonFile.getName());
						ru.tryParsing(jsonFile);
						ru.uploadFiles(file, jsonFile);
					}
					oz++;
				}

				return "success";
			}


		} catch (Exception e) {
			return "connection-failed";
		}
	}

}
