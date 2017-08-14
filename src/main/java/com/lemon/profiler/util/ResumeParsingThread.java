package com.lemon.profiler.util;

import static gate.Utils.stringFor;
import gate.Annotation;
import gate.AnnotationSet;
import gate.Corpus;
import gate.Document;
import gate.Factory;
import gate.FeatureMap;
import gate.Gate;
import gate.util.GateException;
import gate.util.Out;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.ToXMLContentHandler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;


public class ResumeParsingThread implements Runnable {
	private static final Logger log = Logger.getLogger(ResumeParsingThread.class);
	ProcessMate pm;
	File file; 
	public ResumeParsingThread(){
		
	}
	
	public ResumeParsingThread(ProcessMate pm, File file){
		this.pm = pm;
		this.file = file;		
	}
	public void run() {
		
		pm.ProcessResume(file);
	}

	//public static void main(String args[]) {
	public void initiateProcess(String inputFileLocation){	
		ProcessMate pm2 = new ProcessMate();
		ResumeParsingThread t1;
		//t1.start();
		
		
		//log.info("Main process started..."+inputFileLocation);
		String processingFile = "";
		int oz =1; 
		//find how many files
		File folder = new File(inputFileLocation);
		File[] listOfFiles = folder.listFiles();
		
		//trigger those many threads!
		file = new File(inputFileLocation);
		String outputFileName =  file.getPath()+"\temp";
		boolean b = false;
		File outPutFolder = new File(outputFileName);
		 if (!outPutFolder.exists()) {
			 b = outPutFolder.mkdirs();
			 log.info("Temp folder constructed");
		 } 
		 
		for (File file : listOfFiles) {
			if (file.isFile()) {
				log.info("Processing file : " + file.getName() + " " + oz + " th file out of " + listOfFiles.length);
				processingFile = file.getName();
				t1 = new ResumeParsingThread(pm2, file);
				t1.run();
				log.info("<------------------- Processin complete ------------------->");
			}
			oz++;
		}
	}
}

class ProcessMate {
	private static final Logger log = Logger.getLogger(ProcessMate.class);
	static Annie annie = new Annie();
	ProcessMate(){
		try{
		Out.prln("Initialising basic system...");
		System.setProperty("gate.home", "C:\\Noushad\\rnd\\code downloads\\ResumeParser-master\\GATEFiles");
		System.out.println("Sys property set as"+System.getProperty("gate.home"));
		Gate.init();
		Out.prln("...basic system initialised");

		// initialise ANNIE (this may take several minutes)
		
		annie.initAnnie();
		}catch(Exception e){
			
		}
	}
	
	synchronized void ProcessResume(File file){
		//ResumeParserProgram rpp = new ResumeParserProgram();
		
		
		try {
//			System.out.println("process started " +file.getPath());
			
			File tikkaConvertedFile =  parseToHTMLUsingApacheTikka(file);
			System.out.println("Pat is ::"+tikkaConvertedFile.getPath());
			if (tikkaConvertedFile != null) {
				JSONObject parsedJSON =  loadGateAndAnnie(tikkaConvertedFile);
				//remove the html file
				tikkaConvertedFile.delete();
//				Out.prln("Writing to output..."+ FilenameUtils.getFullPath(file.getPath())+"temp\\"+file.getName()+".json");
				File jsonFile = new File(FilenameUtils.getFullPath(file.getPath())+"temp\\"+file.getName()+".json");
				// creates the file
				jsonFile.getParentFile().mkdirs();
				//jsonFile.createNewFile();
//				log.info("File Created");
				FileWriter jsonFileWriter = new FileWriter(jsonFile);
				Jsoup.parse(parsedJSON.toJSONString()).text();
				log.info("Double Ceck :>>\n"+parsedJSON.toJSONString());
				jsonFileWriter.write(parsedJSON.toJSONString());
				jsonFileWriter.flush();
				jsonFileWriter.close();
//				Out.prln("Output written to file " +jsonFile.getAbsolutePath());
				
			
			}
//			System.out.println("process completed");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Sad Face :( .Something went wrong.");
			e.printStackTrace();
		}
	}
	
	public static File parseToHTMLUsingApacheTikka(File file) throws IOException, SAXException, TikaException {
		// determine extension
		String ext = FilenameUtils.getExtension(file.getPath());
		String outputFileFormat = "";
		// ContentHandler handler;
		if (ext.equalsIgnoreCase("html") | ext.equalsIgnoreCase("pdf")
				| ext.equalsIgnoreCase("doc") | ext.equalsIgnoreCase("docx")) {
			outputFileFormat = ".html";
			// handler = new ToXMLContentHandler();
		} else if (ext.equalsIgnoreCase("txt") | ext.equalsIgnoreCase("rtf")) {
			outputFileFormat = ".txt";
		} else {
			System.out.println("Input format of the file " + file
					+ " is not supported.");
			return null;
		}
		String OUTPUT_FILE_NAME = FilenameUtils.removeExtension(file.getName())
				+ outputFileFormat;
		ContentHandler handler = new ToXMLContentHandler();
		// ContentHandler handler = new BodyContentHandler();
		// ContentHandler handler = new BodyContentHandler(
		// new ToXMLContentHandler());
		InputStream stream = new FileInputStream(file);
		AutoDetectParser parser = new AutoDetectParser();
		Metadata metadata = new Metadata();
		try {
			parser.parse(stream, handler, metadata);
			File htmlFile = new File(FilenameUtils.getFullPath(file.getPath())+"html\\"+OUTPUT_FILE_NAME);
			htmlFile.getParentFile().mkdirs();
			FileWriter htmlFileWriter = new FileWriter(htmlFile);
			htmlFileWriter.write(handler.toString());
			htmlFileWriter.flush();
			htmlFileWriter.close();
			//return new File(OUTPUT_FILE_NAME);
			return htmlFile;
		} finally {
			stream.close();
		}
	}
	
	public static JSONObject loadGateAndAnnie(File file) throws GateException, IOException {
		

		// create a GATE corpus and add a document for each command-line
		// argument
		Corpus corpus = Factory.newCorpus("Annie corpus");
		String current = new File(".").getAbsolutePath();
		URL u = file.toURI().toURL();
		FeatureMap params = Factory.newFeatureMap();
		params.put("sourceUrl", u);
		params.put("preserveOriginalContent", new Boolean(true));
		params.put("collectRepositioningInfo", new Boolean(true));
		Out.prln("Creating doc for " + u);
		Document resume = (Document) Factory.createResource(
				"gate.corpora.DocumentImpl", params);
		corpus.add(resume);

		// tell the pipeline about the corpus and run it
		annie.setCorpus(corpus);
		annie.execute();

		Iterator iter = corpus.iterator();
		JSONObject parsedJSON = new JSONObject();
		JSONObject profileJSON2 = new JSONObject();
//		Out.prln("Started parsing...");
		// while (iter.hasNext()) {
		if (iter.hasNext()) { // should technically be while but I am just
								// dealing with one document
			JSONObject profileJSON = new JSONObject();
			
			Document doc = (Document) iter.next();
			AnnotationSet defaultAnnotSet = doc.getAnnotations();

			AnnotationSet curAnnSet;
			Iterator it;
			Annotation currAnnot;

			// Name
			curAnnSet = defaultAnnotSet.get("NameFinder");
			if (curAnnSet.iterator().hasNext()) { // only one name will be
													// found.
				currAnnot = (Annotation) curAnnSet.iterator().next();
				String gender = (String) currAnnot.getFeatures().get("gender");
				if (gender != null && gender.length() > 0) {
					profileJSON.put("gender", gender);
					profileJSON2.put("gender", gender);
				}

				// Needed name Features
				JSONObject nameJson = new JSONObject();
				String[] nameFeatures = new String[] { "firstName",
						"middleName", "surname" };
				String constructedName = "";
				for (String feature : nameFeatures) {
					String s = (String) currAnnot.getFeatures().get(feature);
					if (s != null && s.length() > 0) {
						nameJson.put(feature, s);
						constructedName = constructedName+ " "+s;
					}
				}
				profileJSON.put("name", nameJson);
				profileJSON2.put("name", constructedName);
			} // name

			// title
			curAnnSet = defaultAnnotSet.get("TitleFinder");
			if (curAnnSet.iterator().hasNext()) { // only one title will be
													// found.
				currAnnot = (Annotation) curAnnSet.iterator().next();
				String title = stringFor(doc, currAnnot);
				if (title != null && title.length() > 0) {
					profileJSON.put("title", title);
					profileJSON2.put("title", title);
				}
				
			}// title

			// email,address,phone,url
			String[] annSections = new String[] { "EmailFinder",
					"AddressFinder", "PhoneFinder", "URLFinder" };
			String[] annKeys = new String[] { "email", "address", "phone",
					"url" };
			for (short i = 0; i < annSections.length; i++) {
				String annSection = annSections[i];
				curAnnSet = defaultAnnotSet.get(annSection);
				it = curAnnSet.iterator();
				JSONArray sectionArray = new JSONArray();
				while (it.hasNext()) { // extract all values for each
										// address,email,phone etc..
					currAnnot = (Annotation) it.next();
					String s = stringFor(doc, currAnnot);
					if (s != null && s.length() > 0) {
						sectionArray.add(s);
					}
				}
				if (sectionArray.size() > 0) {
					profileJSON.put(annKeys[i], sectionArray);
					profileJSON2.put(annKeys[i], sectionArray);
				}
			}
			if (!profileJSON.isEmpty()) {
				parsedJSON.put("basics", profileJSON);
			}

			// awards,credibility,education_and_training,extracurricular,misc,skills,summary
			String[] otherSections = new String[] { "summary",
					"education_and_training", "skills", "accomplishments",
					"awards", "credibility", "extracurricular", "misc" };
			for (String otherSection : otherSections) {
				curAnnSet = defaultAnnotSet.get(otherSection);
				it = curAnnSet.iterator();
				JSONArray subSections = new JSONArray();
				while (it.hasNext()) {
					JSONObject subSection = new JSONObject();
					currAnnot = (Annotation) it.next();
					String key = (String) currAnnot.getFeatures().get(
							"sectionHeading");
					String value = stringFor(doc, currAnnot);
					if (!StringUtils.isBlank(key)
							&& !StringUtils.isBlank(value)) {
						subSection.put(key, value);
					}
					if (!subSection.isEmpty()) {
						subSections.add(subSection);
					}
				}
				if (!subSections.isEmpty()) {
					parsedJSON.put(otherSection, subSections);
					profileJSON2.put(otherSection, subSections);
				}
			}

			// work_experience
			curAnnSet = defaultAnnotSet.get("work_experience");
			it = curAnnSet.iterator();
			JSONArray workExperiences = new JSONArray();
			while (it.hasNext()) {
				JSONObject workExperience = new JSONObject();
				currAnnot = (Annotation) it.next();
				String key = (String) currAnnot.getFeatures().get(
						"sectionHeading");
				if (key.equals("work_experience_marker")) {
					// JSONObject details = new JSONObject();
					String[] annotations = new String[] { "date_start",
							"date_end", "jobtitle", "organization" };
					for (String annotation : annotations) {
						String v = (String) currAnnot.getFeatures().get(
								annotation);
						if (!StringUtils.isBlank(v)) {
							// details.put(annotation, v);
							workExperience.put(annotation, v);
						}
					}
					// if (!details.isEmpty()) {
					// workExperience.put("work_details", details);
					// }
					key = "text";

				}
				String value = stringFor(doc, currAnnot);
				if (!StringUtils.isBlank(key) && !StringUtils.isBlank(value)) {
					workExperience.put(key, value);
				}
				if (!workExperience.isEmpty()) {
					workExperiences.add(workExperience);
				}

			}
			if (!workExperiences.isEmpty()) {
				parsedJSON.put("work_experience", workExperiences);
				profileJSON2.put("work_experience", workExperiences);
			}

		}// if
//		Out.prln("Completed parsing...");
		Jsoup.parse(parsedJSON.toString()).text();
		System.out.println(profileJSON2);
		try{
		String jsonString = "{\"id\":\"000\",\"name\":\"mkyong\",\"experience\":35,\"education\":\"Founder\",\"skills\":\"programmer\",\"interests\":\"\",\"location\":\"Chennai\",\"address\":\"address\",\"resumeSummary\":\"resumeSummary\"}";
		JSONParser parser = new JSONParser();
		profileJSON2 = (JSONObject) parser.parse(jsonString);
		}catch(Exception e){}
		//profileJSON2 = new JSONObject();
		return profileJSON2;

	}
	
}