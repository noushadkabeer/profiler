package com.lemon.profiler.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;

public class TestShareAddSiteUsers {

   public static void main(String[] args) throws Exception {
      System.out.println(createSite());
   }

   private static String createSite() {
	   try {
		   HttpURLConnection connection = null;
		   URL url = null;
				String query = String.format("u=%s&pw=%s",
						URLEncoder.encode("admin", "UTF-8"),
						URLEncoder.encode("admin", "UTF-8"));
				url = new URL("http://localhost:8080/alfresco/service/api/login?"
						+ query);
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setDoOutput(true);
				connection.connect(); 
				InputStream is = connection.getInputStream();
				DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance()
						.newDocumentBuilder();
				Document xmlDoc = docBuilder.parse(is);
				System.out.println("Ticket =>"
						+ xmlDoc.getElementsByTagName("ticket").item(0)
								.getTextContent());
				
				String ticket = xmlDoc.getElementsByTagName("ticket").item(0).getTextContent();
			 

				DefaultHttpClient httpClient = new DefaultHttpClient();

				String strURL = "http://localhost:8080/alfresco/service/api/sites/JavaTest-001/memberships";
			HttpPost postRequest = new HttpPost(
				strURL+"?alf_ticket="+ticket);

			String JSON_STRING = "{\"person\":{\"userName\":\"nash\"},\"role\":\"SiteManager\"}";
			//post.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
			//log.info(strURL+" :: New Contents to Post "+JSON_STRING);
			//statusCode = client.executeMethod(post);
			//log.info(statusCode);
			System.out.println(JSON_STRING);
			StringEntity input = new StringEntity(JSON_STRING);
			input.setContentType("application/json");
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);

			if (response.getStatusLine().getStatusCode() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(
	                        new InputStreamReader((response.getEntity().getContent())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			httpClient.getConnectionManager().shutdown();


		  } catch (ClientProtocolException e) {
		
			e.printStackTrace();

		  } catch (Exception e) {
		
			e.printStackTrace();
		  }
return null;
		}   

   private static String createSiteParameters(String title,
         String description, String shortName) {
      String site = "{\"visibility\":\"PRIVATE\", "
            + "\"sitePreset\":\"site-dashboard\", " + "\"title\":\""
            + title + "\", " + "\"description\":\"" + description + "\", "
            + "\"shortName\":\"" + shortName + "\"}";
      System.out.println(site);
      return site;
   }
}