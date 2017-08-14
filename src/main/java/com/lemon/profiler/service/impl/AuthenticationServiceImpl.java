package com.lemon.profiler.service.impl;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.w3c.dom.Document;

import com.lemon.profiler.constants.ProfilerConstants;
import com.lemon.profiler.service.AuthenticationService;
import com.lemon.profiler.service.PropertyReaderService;
import com.opensymphony.xwork2.ActionContext;

public class AuthenticationServiceImpl implements AuthenticationService{
	
	private static final Logger log = Logger.getLogger(AuthenticationServiceImpl.class);
	public static AuthenticationServiceImpl authenticationService;
	PropertyReaderService propS = PropertyReaderServiceImpl.getInstance();
	public AuthenticationServiceImpl getInstance(){
		if(authenticationService!=null)
			return authenticationService;
		else
			return new AuthenticationServiceImpl();
	}

	
//	//Authenticate from remote server
//	public String authenticate(){
//		return "success";
//	}
	
	public String validateTicket(String ticket){
		URL url = null;				
		HttpURLConnection connection = null;
		try {  
			String adminTicket = readTicket("");
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
		
		log.info("Checking if ticket is in session");
		HttpSession session=ServletActionContext.getRequest().getSession(false);  
        if(session!=null || session.getAttribute("adminTicket")!=null){  
        	if(userType.equals(ProfilerConstants.USERTYPE_ADMIN))
        		return (String)session.getAttribute("adminTicket");  
        	else if(userType.equals(ProfilerConstants.USERTYPE_USER))
        		return(String)session.getAttribute(ProfilerConstants.PROPERTY_ALF_TICKET); 
        }  
        else{  
           
        
		log.info("Trying to readTicket for Admin");
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
			String ticket = xmlDoc.getElementsByTagName("ticket").item(0).getTextContent();
			addTicketToSession("adminTicket", ticket);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		} 
		
        } 
		return null;
	}
	
	public String authenticate(String username, String password) {
		log.info("Authenticating..");
		URL url = null;
		if((null!=getSessionTicket()) && !getSessionTicket().equals("")){
			return getSessionTicket();
		}else{
		HttpURLConnection connection = null;
		try { 
			String query = String.format("u=%s&pw=%s",
					URLEncoder.encode(username, "UTF-8"),
					URLEncoder.encode(password, "UTF-8"));
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
			
			String ticket = xmlDoc.getElementsByTagName("ticket").item(0).getTextContent();
			addTicketToSession(ProfilerConstants.PROPERTY_ALF_TICKET, ticket);
			return ticket;
		 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		}
		return null;
	}
	public boolean invalidatelogin(String ticket){
		URL url = null;
		HttpURLConnection connection = null;
		try{
			String adminTicket = readTicket(ProfilerConstants.USERTYPE_ADMIN);
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
	public boolean addTicketToSession(String key, String ticket){
		HttpSession session=ServletActionContext.getRequest().getSession(false);  
        if(session!=null){  
           session.setAttribute(key, ticket);
           return true;
        }  
        
		return false;
	}
	public boolean hasValidTicket(){
		String ticket = (String)ActionContext.getContext().getSession().get(ProfilerConstants.PROPERTY_ALF_TICKET); 
		if(ticket==null || ticket.isEmpty()){
			return false;
		}else{
			return ticket.equals(validateTicket(ticket));
		}
	}
	
	public String getSessionTicket(){
		String ticket = (String)ActionContext.getContext().getSession().get(ProfilerConstants.PROPERTY_ALF_TICKET); 
		log.info("Returing session ticket : "+ticket);
		return ticket; 
	}
}
