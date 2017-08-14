package com.lemon.profiler.test;

import java.io.OutputStream;


public class Docx2PdfConverter {

	public static void main2(String[] args) {
		// TODO Auto-generated method stub

//		InputStream is = new FileInputStream(new File("your Docx PAth"));
//		WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage
//				.load(is);
//		List sections = wordMLPackage.getDocumentModel().getSections();
//		for (int i = 0; i < sections.size(); i++) {
//			wordMLPackage.getDocumentModel().getSections().get(i)
//					.getPageDimensions();
//		}
//		Mapper fontMapper = new IdentityPlusMapper();
//		PhysicalFont font = PhysicalFonts.getPhysicalFonts().get(
//				"Comic Sans MS");// set your desired font
//		fontMapper.getFontMappings().put("Algerian", font);
//		wordMLPackage.setFontMapper(fontMapper);
//		PdfSettings pdfSettings = new PdfSettings();
//		PdfConversion conversion = new Conversion(
//				wordMLPackage);
//		// To turn off logger
//		List<Logger> loggers = Collections.<Logger> list(LogManager
//				.getCurrentLoggers());
//		loggers.add(LogManager.getRootLogger());
//		for (Logger logger : loggers) {
//			logger.setLevel(Level.OFF);
//		}
//		OutputStream out = new FileOutputStream(
//				new File("Your OutPut PDF path"));
//		conversion.output(out, pdfSettings);
//		System.out.println("DONE!!");

	}

	// For demo/debugging purposes, save the intermediate XSL FO
	// Don't do this in production!
	static boolean saveFO;
	public static void main(String[] args) throws Exception {
/*
		String inputfilepath = null; // to generate a docx (and PDF output)
										// containing font samples
		inputfilepath = System.getProperty("user.dir")
				+ "/sample-docs/word/sample-docx.docx";

		saveFO = true;
		// Font regex (optional)
		// Set regex if you want to restrict to some defined subset of fonts
		// Here we have to do this before calling createContent,
		// since that discovers fonts
		String regex = null;
		PhysicalFonts.setRegex(regex);
		WordprocessingMLPackage wordMLPackage;
		System.out.println("Loading file from " + inputfilepath);
		wordMLPackage = WordprocessingMLPackage.load(new java.io.File(
				inputfilepath));
		FieldUpdater updater = new FieldUpdater(wordMLPackage);
		updater.update(true);

		String outputfilepath;
		if (inputfilepath == null) {
			outputfilepath = System.getProperty("user.dir")
					+ "/OUT_FontContent.pdf";
		} else {
			outputfilepath = inputfilepath + ".pdf";
		}

		// All methods write to an output stream
		OutputStream os = new java.io.FileOutputStream(outputfilepath);

		if (!Docx4J.pdfViaFO()) {
			// Since 3.3.0, Plutext's PDF Converter is used by default
			System.out.println("Using Plutext's PDF Converter; add docx4j-export-fo if you don't want that");
			Docx4J.toPDF(wordMLPackage, os);
			System.out.println("Saved: " + outputfilepath);
			return;
		}

		System.out.println("Attempting to use XSL FO");
		// Set up font mapper (optional)
		Mapper fontMapper = new IdentityPlusMapper();
		wordMLPackage.setFontMapper(fontMapper);
		PhysicalFont font = PhysicalFonts.get("Arial Unicode MS");
		FOSettings foSettings = Docx4J.createFOSettings();
		if (saveFO) {
			foSettings.setFoDumpFile(new java.io.File(inputfilepath + ".fo"));
		}
		foSettings.setWmlPackage(wordMLPackage);
		Docx4J.toFO(foSettings, os, Docx4J.FLAG_EXPORT_PREFER_XSL);
		System.out.println("Saved: " + outputfilepath);
		if (wordMLPackage.getMainDocumentPart().getFontTablePart() != null) {
			wordMLPackage.getMainDocumentPart().getFontTablePart()
					.deleteEmbeddedFontTempFiles();
		}
		// This would also do it, via finalize() methods
		updater = null;
		foSettings = null;
		wordMLPackage = null;
		*/

	}

}
