package com.lemon.profiler.util.doc2pdf;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import com.lemon.profiler.action.BulkUploadProcessorAction;



public class Doc2PDFInitiator{


	private static final Logger log = Logger.getLogger(Doc2PDFInitiator.class);

	public static final String VERSION_STRING = "\nDocs to PDF Converter Version 1.7 (8 Dec 2013)\n\nThe MIT License (MIT)\nCopyright (c) 2013-2014 Yeo Kheng Meng";
	public enum DOC_TYPE {
		DOC,
		DOCX,
		PPT,
		PPTX,
		ODT
	}
	
//	public static void main(String[] args){
//		Converter converter;
//
//		try{
//			converter = processArguments(args);
//		} catch (Exception e){
//			System.out.println("\n\nInput\\Output file not specified properly.");
//			return;
//		}
//
//
//		if(converter == null){
//			System.out.println("Unable to determine type of input file.");
//		} else {
//			try {
//				converter.convert();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//	}


//	public static Converter processArguments(String[] args) throws Exception{
//		CommandLineValues values = new CommandLineValues();
//		CmdLineParser parser = new CmdLineParser(values);
//
//		Converter converter = null;
//		try {
//			parser.parseArgument(args);
//
//			boolean version = values.version;
//			
//			if(version){
//				System.out.println(VERSION_STRING);
//				System.exit(0);
//			}
//
//
//			String inPath = values.inFilePath;
//			String outPath = values.outFilePath;
//			boolean shouldShowMessages = values.verbose;
//
//			inPath = "C:\\NSD\\work\\profiler\\Resumes\\Sampledocs\\Aysha CV-November 2014-3-4.doc";
//			if(inPath == null){
//				parser.printUsage(System.err);
//				throw new IllegalArgumentException();
//			}
//
//			outPath = "C:\\NSD\\work\\profiler\\Resumes\\Sampledocs\\Aysha CV-November 2014-3-4\\t.pdf";
//			if(outPath == null){
//				outPath = changeExtensionToPDF(inPath);
//			}
//
//
//			String lowerCaseInPath = inPath.toLowerCase();
//			
//			InputStream inStream = getInFileStream(inPath);
//			OutputStream outStream = getOutFileStream(outPath);
//			
//			if(values.type == null){
//				if(lowerCaseInPath.endsWith("doc")){
//					converter = new DocToPDFConverter(inStream, outStream, shouldShowMessages, true);
//				} else if (lowerCaseInPath.endsWith("docx")){
//					converter = new DocxToPDFConverter(inStream, outStream, shouldShowMessages, true);
//				} else if(lowerCaseInPath.endsWith("ppt")){
//					converter = new PptToPDFConverter(inStream, outStream, shouldShowMessages, true);
//				} else if(lowerCaseInPath.endsWith("pptx")){
//					converter = new PptxToPDFConverter(inStream, outStream, shouldShowMessages, true);
//				} else if(lowerCaseInPath.endsWith("odt")){
//					converter = new OdtToPDF(inStream, outStream, shouldShowMessages, true);
//				} else {
//					converter = null;
//				}
//
//
//			} else {
//
//				switch(values.type){
//				case DOC: converter = new DocToPDFConverter(inStream, outStream, shouldShowMessages, true);
//				break; 
//				case DOCX: converter = new DocxToPDFConverter(inStream, outStream, shouldShowMessages, true);
//				break;
//				case PPT:  converter = new PptToPDFConverter(inStream, outStream, shouldShowMessages, true);
//				break;
//				case PPTX: converter = new PptxToPDFConverter(inStream, outStream, shouldShowMessages, true);
//				break;
//				case ODT: converter = new OdtToPDF(inStream, outStream, shouldShowMessages, true);
//				break;
//				default: converter = null;
//				break;
//
//				}
//
//
//			}
//			
//		} catch (CmdLineException e) {
//			// handling of wrong arguments
//			System.err.println(e.getMessage());
//			parser.printUsage(System.err);
//		}
//
//		return converter;
//
//	}
	
	
	public static Converter processDocuments(String inPath, String outPath, String type, boolean shouldShowMsgs) throws Exception{

		Converter converter = null;
	//	String atrs ;
		try {


			boolean shouldShowMessages = shouldShowMsgs;
			
			String inputPath = inPath;
			String outPutPath = outPath;

//			inPath = "C:\\NSD\\work\\profiler\\Resumes\\Sampledocs\\Aysha CV-November 2014-3-4.doc";
			if(inPath == null){
				throw new IllegalArgumentException();
			}

//			outPath = "C:\\NSD\\work\\profiler\\Resumes\\Sampledocs\\Aysha CV-November 2014-3-4\\t.pdf";
			if(outPath == null){
				outPath = changeExtensionToPDF(inPath);
			}


			String lowerCaseInPath = inPath.toLowerCase();
			
			InputStream inStream = getInFileStream(inPath);
			OutputStream outStream = getOutFileStream(outPath);
//			log.info(type+" Converting the file "+inPath);
		//	if(type == null){
				if(lowerCaseInPath.endsWith("doc")){
					converter = new DocToPDFConverter(inStream, outStream, shouldShowMessages, true);
					log.info("Doc Con");
				} else if (lowerCaseInPath.endsWith("docx")){
					converter = new DocxToPDFConverter(inStream, outStream, shouldShowMessages, true);
				} else if(lowerCaseInPath.endsWith("ppt")){
					converter = new PptToPDFConverter(inStream, outStream, shouldShowMessages, true);
				} else if(lowerCaseInPath.endsWith("pptx")){
					converter = new PptxToPDFConverter(inStream, outStream, shouldShowMessages, true);
				} else if(lowerCaseInPath.endsWith("odt")){
					converter = new OdtToPDF(inStream, outStream, shouldShowMessages, true);
				} else {
					converter = null;
				}

//				log.info("B4 convert");
				if(converter !=null) converter.convert();
//				log.info("Convert complete");
//			} else {
//				int o=1;
//				switch(o){
//				case "DOC": converter = new DocToPDFConverter(inStream, outStream, shouldShowMessages, true);
//				break; 
//				case "DOCX": converter = new DocxToPDFConverter(inStream, outStream, shouldShowMessages, true);
//				break;
//				case "PPT":  converter = new PptToPDFConverter(inStream, outStream, shouldShowMessages, true);
//				break;
//				case "PPTX": converter = new PptxToPDFConverter(inStream, outStream, shouldShowMessages, true);
//				break;
//				case "ODT": converter = new OdtToPDF(inStream, outStream, shouldShowMessages, true);
//				break;
//				default: converter = null;
//				break;
//
//				}


			//}
			
		} catch (Exception e) {
			// handling of wrong arguments
			System.err.println(e.getMessage());
//			parser.printUsage(System.err);
		}

		return converter;

	}


//	public static class CommandLineValues {
//
//		@Option(name = "-type", aliases = "-t", required = false, usage = "Specifies doc converter. Leave blank to let program decide by input extension.")
//		public DOC_TYPE type = null;
//
//
//		@Option(name = "-inputPath", aliases = {"-i", "-in", "-input"}, required = false,  metaVar = "<path>",
//				usage = "Specifies a path for the input file.")
//		public String inFilePath = null;
//
//
//		@Option(name = "-outputPath", aliases = {"-o", "-out", "-output"}, required = false, metaVar = "<path>",
//				usage = "Specifies a path for the output PDF.")
//		public String outFilePath = null;
//
//		@Option(name = "-verbose", aliases = {"-v"}, required = false, usage = "To see intermediate processing messages.")
//		public boolean verbose = false;
//
//		@Option(name = "-version", aliases = {"-ver"}, required = false, usage = "To view version code.")
//		public boolean version = false;
//
//
//	}

	//From http://stackoverflow.com/questions/941272/how-do-i-trim-a-file-extension-from-a-string-in-java
	public static String changeExtensionToPDF(String originalPath) {

//		String separator = System.getProperty("file.separator");
		String filename = originalPath;

//		// Remove the path upto the filename.
//		int lastSeparatorIndex = originalPath.lastIndexOf(separator);
//		if (lastSeparatorIndex == -1) {
//			filename = originalPath;
//		} else {
//			filename = originalPath.substring(lastSeparatorIndex + 1);
//		}

		// Remove the extension.
		int extensionIndex = filename.lastIndexOf(".");

		String removedExtension;
		if (extensionIndex == -1){
			removedExtension =  filename;
		} else {
			removedExtension =  filename.substring(0, extensionIndex);
		}
		String addPDFExtension = removedExtension + ".pdf";

		return addPDFExtension;
	}
	
	
	protected static InputStream getInFileStream(String inputFilePath) throws FileNotFoundException{
		File inFile = new File(inputFilePath);
		FileInputStream iStream = new FileInputStream(inFile);
		return iStream;
	}
	
	protected static OutputStream getOutFileStream(String outputFilePath) throws IOException{
		File outFile = new File(outputFilePath);
		
		try{
			//Make all directories up to specified
			outFile.getParentFile().mkdirs();
		} catch (NullPointerException e){
			//Ignore error since it means not parent directories
		}
		
		outFile.createNewFile();
		FileOutputStream oStream = new FileOutputStream(outFile);
		return oStream;
	}











}
