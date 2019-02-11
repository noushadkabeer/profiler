package com.lemon.profiler.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;

import com.lemon.profiler.constants.ProfilerConstants;
import com.lemon.profiler.model.Organization;
import com.lemon.profiler.model.User;
import com.lemon.profiler.service.ApplicationSetupService;
import com.lemon.profiler.service.AuthenticationService;
import com.lemon.profiler.service.PropertyReaderService;

public class ApplicationSetupServiceImpl implements ApplicationSetupService {

	private static final Logger log = Logger.getLogger(ApplicationSetupServiceImpl.class);
	AuthenticationService authService = new AuthenticationServiceImpl().getInstance();
	PropertyReaderService propS = PropertyReaderServiceImpl.getInstance();

	@Override
	public boolean setupApplication(Organization org, User user) {
log.info("-------------------------------------------\n[Application Setup Started]\n---------------------------------------------------");
		String ticket = authService.readTicket(ProfilerConstants.USERTYPE_ADMIN);
		String strURL = propS.getKeyValue("contentServerURL") + propS.getKeyValue("serviceURL") + "/api/sites";
		PostMethod post = new PostMethod(strURL);
		if (ticket != null && !ticket.isEmpty()) {
			//strURL = strURL + "?alf_ticket=" + ticket;
			String JSON_STRING = createSiteParameters(org.getTitle(), org.getDescription(), org.getShortName());
			String auth = propS.getKeyValue(ProfilerConstants.ADMIN_USERNAME) + ":" + propS.getKeyValue(ProfilerConstants.ADMIN_PASSWORD);
			byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
			String authHeader = "Basic " + new String(encodedAuth);
			post.setRequestHeader(HttpHeaders.AUTHORIZATION, authHeader);
			
			log.info(JSON_STRING);
			try {
				log.info("Ticket generated.. creating site " + strURL);								
				StringRequestEntity requestEntity = new StringRequestEntity(JSON_STRING, "application/json", "UTF-8");
				post.setRequestEntity(requestEntity);
				InputStream in = null;
				HttpClient client = new HttpClient();
				post.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
						new DefaultHttpMethodRetryHandler(3, false));
				int statusCode = client.executeMethod(post);
				log.info(statusCode);
				if (statusCode == 200) {
					log.info("Site created! "+post.getResponseBodyAsString());
					log.info("User :: "+user.getUserName());
					strURL = propS.getKeyValue("contentServerURL") + propS.getKeyValue("serviceURL") + "/api/sites/"+org.getShortName()+"/memberships";
				//	JSON_STRING = "{\"person\":{\"userName\":\""+user.getUserName()+"\"},\"role\":\"SiteManager\"}";
//					post.setRequestEntity(requestEntity);
//					post.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
//					log.info(strURL+" :: New Contents to Post "+JSON_STRING);
//					statusCode = client.executeMethod(post);
					HttpPost postRequest = new HttpPost(strURL+"?alf_ticket="+ticket);
					
					 JSON_STRING = "{\"person\":{\"userName\":\""+user.getUserName()+"\"},\"role\":\"SiteManager\"}";
					System.out.println(JSON_STRING);
					StringEntity input = new StringEntity(JSON_STRING);
					input.setContentType("application/json");
					postRequest.setEntity(input);
					DefaultHttpClient httpClient = new DefaultHttpClient();
					HttpResponse response = httpClient.execute(postRequest);
					
					log.info(response.getStatusLine().getStatusCode() +" \n Response Update the user");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	

	   private String createSiteParameters(String title,
	         String description, String shortName) {
	      String site = "{\"visibility\":\"PRIVATE\", "
	            + "\"sitePreset\":\"site-dashboard\", " + "\"title\":\""
	            + title + "\", " + "\"description\":\"" + description + "\", "
	            + "\"shortName\":\"" + shortName + "\"}";
	      System.out.println(site);
	      return site;
	   }

	@Override
	public Organization getOrganizationAllSites(Organization org) {

		JSONParser parser = new JSONParser();
		String ticket = authService.readTicket(ProfilerConstants.USERTYPE_ADMIN);
		if (ticket != null && !ticket.isEmpty()) {
			try {
				String strURL = propS.getKeyValue("contentServerURL") + propS.getKeyValue("serviceURL") + "/api/sites"
						+ "?alf_ticket=" + ticket;
				HttpClient client = new HttpClient();
				GetMethod method = new GetMethod(strURL);
				int statusCode = client.executeMethod(method);
				log.info(statusCode + " for the request :" + strURL);
				if (statusCode != -1) {
					BufferedInputStream bis = new BufferedInputStream(method.getResponseBodyAsStream());
					BufferedReader streamReader = new BufferedReader(new InputStreamReader(bis, "UTF-8"));
					StringBuilder responseStrBuilder = new StringBuilder();
					String inputStr;
					while ((inputStr = streamReader.readLine()) != null)
						responseStrBuilder.append(inputStr);
					JSONObject sitesJson = (JSONObject) parser.parse(responseStrBuilder.toString());
					log.info("Json contents \n" + sitesJson);

					if (sitesJson.get("shortName").equals(org.getShortName())) {
						return org;
					}

				}
			} catch (Exception e) {
				log.error("Error in processing getUser " + e.getMessage());
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Organization getOrganizationalSite(Organization org) {
		JSONParser parser = new JSONParser();
		String ticket = authService.readTicket(ProfilerConstants.USERTYPE_ADMIN);
		if (ticket != null && !ticket.isEmpty()) {
			try {
				String strURL = propS.getKeyValue("contentServerURL") + propS.getKeyValue("serviceURL") + "/api/sites/"
						+ org.getShortName() + "?alf_ticket=" + ticket;
				HttpClient client = new HttpClient();
				GetMethod method = new GetMethod(strURL);
				int statusCode = client.executeMethod(method);
				log.info(statusCode + " for the request :" + strURL);
				if (statusCode != -1) {
					BufferedInputStream bis = new BufferedInputStream(method.getResponseBodyAsStream());
					BufferedReader streamReader = new BufferedReader(new InputStreamReader(bis, "UTF-8"));
					StringBuilder responseStrBuilder = new StringBuilder();
					String inputStr;
					while ((inputStr = streamReader.readLine()) != null)
						responseStrBuilder.append(inputStr);
					JSONObject sitesJson = (JSONObject) parser.parse(responseStrBuilder.toString());
					log.info("Json contents \n" + sitesJson + " :: " + sitesJson.get("message"));
					if (sitesJson.get("shortName") != null && sitesJson.get("shortName").equals(org.getShortName())) {
						return org;
					} else if (sitesJson.get("message").toString().endsWith("does not exist")) {
						org.setShortName("noSite");
						return org;
					}

				}
			} catch (Exception e) {
				log.error("Error in processing getUser " + e.getMessage());
				e.printStackTrace();
			}
		}
		return null;
	}

}
