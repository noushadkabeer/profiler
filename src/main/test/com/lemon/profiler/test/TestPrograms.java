package com.lemon.profiler.test;

import java.io.File;

import gate.SimpleAnnotation;

public class TestPrograms {

	public static void main(String arg[]){
		String inputFileLocation = "C:/Noushad/Personal/Profiler/Resumes/Sampl3";

		System.out.println("Main process started..."+inputFileLocation);
		String processingFile = "";
		int oz =1; 
		//find how many files
		File folder = new File(inputFileLocation);
		File[] listOfFiles = folder.listFiles();
		//trigger those many threads!
		File file = new File(inputFileLocation);
				String outputFileName =  file.getPath()+"\temp";
				boolean b = false;
				File outPutFolder = new File(outputFileName);
				 if (!outPutFolder.exists()) {
					 b = outPutFolder.mkdirs();
					 System.out.println("Temp folder constructed");
				 } 
	}
}
