package com.lemon.idportal.service.impl;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import com.lemon.profiler.service.AuthenticationService;
import com.lemon.profiler.service.PropertyReaderService;
import com.lemon.profiler.service.impl.PropertyReaderServiceImpl;
import com.opensymphony.xwork2.ActionContext;

public class DBAuthenticationServiceImpl implements AuthenticationService{
	
	private static final Logger log = Logger.getLogger(DBAuthenticationServiceImpl.class);
	
	public static DBAuthenticationServiceImpl authenticationService;
	PropertyReaderService propS = PropertyReaderServiceImpl.getInstance();
	public DBAuthenticationServiceImpl getInstance(){
		if(authenticationService!=null)
			return authenticationService;
		else
			return new DBAuthenticationServiceImpl();
	}
	
	public String validateTicket(String ticket){
		URL url = null;				
		HttpURLConnection connection = null;
		try {  
			String adminTicket = readTicket("admin");
			String query = String.format("alf_ticket=%s",
					URLEncoder.encode(adminTicket, "UTF-8"));
			url = new URL(propS.getKeyValue("contentServerURL")+ "alfresco/service/api/login/ticket/"+ ticket+ "?"+query);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.connect();
			InputStream is = connection.getInputStream();
			DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document xmlDoc = docBuilder.parse(is);
			log.info("Validated Ticket =>" + xmlDoc.getElementsByTagName("ticket").item(0).getTextContent());
			return xmlDoc.getElementsByTagName("ticket").item(0).getTextContent();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return null;		
	}
	
	public String readTicket(String userType) {
		log.info("Trying to readTicket()");
		URL url = null;
		HttpURLConnection connection = null;
		try { 
			String query = String.format("u=%s&pw=%s",
					URLEncoder.encode(propS.getKeyValue("adminuser"), "UTF-8"),
					URLEncoder.encode(propS.getKeyValue("adminpassword"), "UTF-8"));
			url = new URL(propS.getKeyValue("contentServerURL")+"alfresco/service/api/login?"
					+ query);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.connect(); 
			InputStream is = connection.getInputStream();
			DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document xmlDoc = docBuilder.parse(is);
			log.info("Ticket =>"
					+ xmlDoc.getElementsByTagName("ticket").item(0)
							.getTextContent());
			return xmlDoc.getElementsByTagName("ticket").item(0)
					.getTextContent();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return null;
	}
	
	public String authenticate(String username, String password) {
		log.info("Trying to readTicket()");
		URL url = null;
		HttpURLConnection connection = null;
		String sql = "select * from users where username= and password =";
		try { 
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return null;
	}
	public boolean invalidatelogin(String ticket){
		URL url = null;
		HttpURLConnection connection = null;
		try{
			String adminTicket = readTicket("admin");
			String query = String.format("alf_ticket=%s",
					URLEncoder.encode(adminTicket, "UTF-8"));
			url = new URL(propS.getKeyValue("contentServerURL")+ "alfresco/service/api/login/ticket/"+ ticket+ "?"+query);
			
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded" );
			connection.setRequestMethod("DELETE");
			int responseCode = connection.getResponseCode();
						
			connection.connect();
			log.info("<<<<>>>>" +responseCode);
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			if (connection != null) {
				connection.disconnect();
			}
		}		 
		return true;
	}
	
	public boolean hasValidTicket(){
		String ticket = (String)ActionContext.getContext().getSession().get("alf_ticket"); 
		if(ticket==null || ticket.isEmpty()){
			return false;
		}else{
			return ticket.equals(validateTicket(ticket));
		}
	}
	
	public String getSessionTicket(){
		String ticket = (String)ActionContext.getContext().getSession().get("alf_ticket"); 
		log.info("Returing session ticket : "+ticket);
		return ticket; 
	}
}
