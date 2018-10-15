package com.lemon.profiler.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import com.lemon.profiler.constants.ProfilerConstants;
import com.lemon.profiler.exceptions.ConnectionFailedException;
import com.lemon.profiler.model.Organization;
import com.lemon.profiler.service.ApplicationSetupService;
import com.lemon.profiler.service.AuthenticationService;
import com.lemon.profiler.service.PropertyReaderService;

public class ApplicationSetupServiceImpl implements ApplicationSetupService{

	private static final Logger log = Logger.getLogger(ApplicationSetupServiceImpl.class);
	AuthenticationService authService = new AuthenticationServiceImpl().getInstance();
	PropertyReaderService propS = PropertyReaderServiceImpl.getInstance();


	@Override
	public boolean setupApplication(Organization org) {
		String strURL = propS.getKeyValue("contentServerURL")
				+ propS.getKeyValue("serviceURL")+"api/sites";
		String ticket = "";
		PostMethod post = new PostMethod(strURL);
		String uploadedPath = "";
		ticket = authService.readTicket(ProfilerConstants.USERTYPE_USER);
		if (ticket != null && !ticket.isEmpty()) {			
			post.addParameter("alf_ticket", ticket);
			try {
				log.info("Ticket generated.. uploading file "+  strURL);
				Part[] parts = { new StringPart("shortName", org.shortName),
						new StringPart("title", org.title),
						new StringPart("description", org.description),
						new StringPart("visibility", "PRIVATE")}; //default
				post.setRequestEntity(new MultipartRequestEntity(parts, post
						.getParams()));
				InputStream in = null;
				HttpClient client = new HttpClient();
				Credentials loginCreds = new UsernamePasswordCredentials(ProfilerConstants.ADMIN_USERNAME, ProfilerConstants.ADMIN_PASSWORD);
				client.getState().setCredentials(AuthScope.ANY, loginCreds);
				post.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
						new DefaultHttpMethodRetryHandler(3, false));
				int statusCode = client.executeMethod(post);
				log.info(statusCode);
				if (statusCode != -1) {
					in = post.getResponseBodyAsStream();

					DocumentBuilderFactory factory2 = DocumentBuilderFactory
							.newInstance();
					DocumentBuilder builder2 = factory2.newDocumentBuilder();
					Document doc3 = builder2.parse(in);
					TransformerFactory factory7 = TransformerFactory
							.newInstance();
					Transformer xform = factory7.newTransformer();
					xform.transform(new DOMSource(doc3), new StreamResult(
							System.out));
					uploadedPath = doc3.getElementsByTagName("id").item(0).getTextContent();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
		return false;
	}

}
