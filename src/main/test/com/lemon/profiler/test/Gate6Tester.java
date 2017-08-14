package com.lemon.profiler.test;

import java.io.File;

import com.lemon.profiler.util.ResumeParsingThread;

public class Gate6Tester {
	public static void main(String args[]) {
		System.out.println("Startin te process..");
		
		ProcessMate pm2 = new ProcessMate();
		ResumeParsingThread t1;
		//t1.start();
		
		
		System.out.println("Main process started...");
		String processingFile = "";
		int oz =1; 
		//find how many files
		File folder = new File("C:\\Noushad\\Personal\\Profiler\\Resumes\\Sampl3\\");
		File[] listOfFiles = folder.listFiles();
		
		//trigger those many threads!
		
		for (File file : listOfFiles) {
			if (file.isFile()) {
				System.out.println("Processing file : " + file.getName() + " " + oz + " th file out of " + listOfFiles.length);
				new ResumeParsingThread().initiateProcess(file.getPath());
//				processingFile = file.getName();
//				t1 = new ResumeParsingThread(pm2, file);
//				t1.run();
			}
			oz++;
		}
	}

}
