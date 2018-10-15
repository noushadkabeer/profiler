package com.lemon.profiler.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.lemon.profiler.common.JSONObjectProcessor;
import com.lemon.profiler.common.ProfilerUtil;
import com.lemon.profiler.constants.ProfilerConstants;
import com.lemon.profiler.model.Task;
import com.lemon.profiler.model.User;
import com.lemon.profiler.service.AuthenticationService;
import com.lemon.profiler.service.PropertyReaderService;
import com.lemon.profiler.service.UserService;

public class UserServiceImpl implements UserService{
	
	PropertyReaderService propS = new PropertyReaderServiceImpl();
	AuthenticationService authService = new AuthenticationServiceImpl();
	private static final Logger log = Logger.getLogger(UserServiceImpl.class);
	
	private static ArrayList<User> userList;

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUser(User emp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(User id) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public User getUser(String userId) {
		JSONParser parser = new JSONParser(); 
		String ticket = authService.readTicket(ProfilerConstants.USERTYPE_USER);
		if (ticket != null && !ticket.isEmpty()) {
			 try
		        {
					String strURL = propS.getKeyValue("contentServerURL")
							+ "alfresco/service/api/people/"+userId+"?alf_ticket="+ticket;
				 HttpClient client = new HttpClient();
					GetMethod method = new GetMethod(strURL); 
					int statusCode = client.executeMethod(method);
					log.info(statusCode + " for the request :"+strURL);
					if (statusCode != -1) { 
			            BufferedInputStream bis = new BufferedInputStream(method.getResponseBodyAsStream());
			            BufferedReader streamReader = new BufferedReader(new InputStreamReader(bis, "UTF-8"));
			            StringBuilder responseStrBuilder = new StringBuilder();
			            String inputStr;
			            while ((inputStr = streamReader.readLine()) != null)
			                responseStrBuilder.append(inputStr);			          
			        	JSONObject userJson = (JSONObject) parser.parse(responseStrBuilder.toString()); 
			        	log.info("Json contents \n"+userJson);
			        return new JSONObjectProcessor().returnProcessedUser(userJson);
			   	
					}
				} catch (Exception e)
		        {
					log.error("Error in processing getUser "+e.getMessage());
		            e.printStackTrace();
		        } 
		}
		return null;
	}

	
	@Override
	public JSONObject createUser(User user) {
		// TODO Auto-generated method stub/api/people

		
		String ticket = authService.readTicket(ProfilerConstants.USERTYPE_ADMIN);	
		 String strURL =  ProfilerUtil.getInstance().alfrescoContextURL()+ "/service/api/people?alf_ticket="+ticket;  
			PostMethod post = new PostMethod(strURL); 
			
			//Before insert generate an unique id for the record
		//	String tempUserName =ProfilerUtil.getInstance().generatedInteger(); 
			user.setUserId(user.getUserEmail());
			user.setUserName(user.getUserEmail());
			log.info("Creating a new profile "+strURL+"\n"+user.getUserName()+user.getFirstName()+user.getLastName()+user.getPassword()+user.getUserEmail());
			//post.addParameter("alf_ticket", ticket);
			try { 

				JSONObject json = new JSONObject();
				json.put("userName", user.getUserName());
				json.put("firstName", user.getFirstName());    
				json.put("lastName", user.getLastName());  
				json.put("email", user.getFirstName()); 
				json.put("password", user.getPassword());

				CloseableHttpClient httpClient = HttpClientBuilder.create().build();

				try {
				    HttpPost request = new HttpPost(strURL);
				    StringEntity params = new StringEntity(json.toString());
				    request.addHeader("X-Requested-With", "application/json");
				    request.addHeader("Content-Type", "application/json");
				    request.setEntity(params);
//				    httpClient.execute(request);
				    
				    HttpResponse result = httpClient.execute(request);

		            String jsons = EntityUtils.toString(result.getEntity(), "UTF-8");
		            try {
		                JSONParser parser = new JSONParser();
		                Object resultObject = parser.parse(jsons);

//		                if (resultObject instanceof JSONArray) {
//		                    JSONArray array=(JSONArray)resultObject;
//		                    for (Object object : array) {
//		                        JSONObject obj =(JSONObject)object;
//		                        System.out.println(obj.get("firstName"));
//		                        System.out.println(obj.get("lastName"));
//		                    }
//
//		                }else 
		                	
		                if (resultObject instanceof JSONObject) {
		                    JSONObject obj =(JSONObject)resultObject;
		                   return obj;
		                }

		            } catch (Exception e) {
		                // TODO: handle exception
		            	log.info("Error in processing the result "+e.getMessage());
		            	return new JSONObject();
		            }

				// handle response here...
				} catch (Exception ex) {
				    // handle exception here
					log.info("Error in building the Post request ");
					ex.printStackTrace();
				} finally {
				    httpClient.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				post.releaseConnection();
			}        		
		
		log.info("Created user :"+user.getUserName());		
	return null;	
	}
	

}
