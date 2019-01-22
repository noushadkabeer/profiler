package com.lemon.profiler.util.doc2pdf;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.apache.log4j.Logger;
import org.docx4j.Docx4J;
import org.docx4j.convert.in.Doc;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;


public class DocToPDFConverter extends Converter {

	private static final Logger log = Logger.getLogger(DocToPDFConverter.class);


	public DocToPDFConverter(InputStream inStream, OutputStream outStream, boolean showMessages, boolean closeStreamsWhenComplete) {
		super(inStream, outStream, showMessages, closeStreamsWhenComplete);
		log.info("-------Conversion complete-------------->");
	}


	@Override
	public void convert() throws Exception{
		log.info("Starting with loading");
		loading();
		InputStream iStream = inStream;
		WordprocessingMLPackage wordMLPackage = getMLPackage(iStream);
		processing();
		log.info("Processing..");
		Docx4J.toPDF(wordMLPackage, outStream);
		finished();
		log.info("finished");
	}

	protected WordprocessingMLPackage getMLPackage(InputStream iStream) throws Exception{
		PrintStream originalStdout = System.out;
		
		//Disable stdout temporarily as Doc convert produces alot of output
		System.setOut(new PrintStream(new OutputStream() {
			public void write(int b) {
				//DO NOTHING
			}
		}));

		WordprocessingMLPackage mlPackage = Doc.convert(iStream);
		log.info("Doc Convert in process");
		System.setOut(originalStdout);
		return mlPackage;
	}

}
