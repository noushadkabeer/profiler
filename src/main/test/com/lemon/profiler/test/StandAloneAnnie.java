package com.lemon.profiler.test;

import gate.AnnotationSet;
import gate.Corpus;
import gate.Document;
import gate.Factory;
import gate.Gate;
import gate.LanguageAnalyser;
import gate.Utils;
import gate.creole.ANNIEConstants;
import gate.util.persistence.PersistenceManager;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;

/**
 * This class illustrates how to use ANNIE as a sausage machine
 * in another application - put ingredients in one end (URLs pointing
 * to documents) and get sausages (e.g. Named Entities) out the
 * other end.
 * <P><B>NOTE:</B><BR>
 * For simplicity's sake, we don't do any exception handling.
 */
public class StandAloneAnnie  {
	
	  public static void main(String[] args) throws Exception {
		  
		  File file = new File("C:\\Noushad\\Personal\\Profiler\\Resumes\\H M Waqas - Maintenance Planner.doc");
			String current = new File(".").getAbsolutePath();
			URL u = file.toURI().toURL();
			
	        Gate.setGateHome(new File("C:\\Noushad\\softwares\\installables\\nlp\\gate-8.4-build5748-ALL"));
	        Gate.init();

	        LanguageAnalyser controller = (LanguageAnalyser) PersistenceManager
	                .loadObjectFromFile(new File(new File(Gate.getPluginsHome(),
	                        ANNIEConstants.PLUGIN_DIR), ANNIEConstants.DEFAULT_FILE));

	        Corpus corpus = Factory.newCorpus("corpus");
	        Document document = Factory.newDocument(u);
	            //    "Michael Jordan is a professor at the University of California, Berkeley.");
	        corpus.add(document); controller.setCorpus(corpus); 
	        controller.execute();

//
//	        document.getAnnotations().get(new HashSet<String>(Arrays.asList("Person", "Organization", "Location", "DOB", "NameFinder", "Date", "Email", "Qualification")))
//	            .forEach(a -> System.err.format("%s - \"%s\" [%d to %d]\n", 
//	                    a.getType(), Utils.stringFor(document, a),
//	                    a.getStartNode().getOffset(), a.getEndNode().getOffset()));

	        //Don't forget to release GATE resources 
	        Factory.deleteResource(document); Factory.deleteResource(corpus); Factory.deleteResource(controller);
	    }

} // class StandAloneAnnie