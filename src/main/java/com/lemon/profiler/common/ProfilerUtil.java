package com.lemon.profiler.common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Node;

import com.lemon.profiler.service.AlfrescoFileService;
import com.lemon.profiler.service.PropertyReaderService;
import com.lemon.profiler.service.impl.AuthenticationServiceImpl;
import com.lemon.profiler.service.impl.FileUploadImpl;
import com.lemon.profiler.service.impl.PropertyReaderServiceImpl;

public class ProfilerUtil {
	AlfrescoFileService fileService = new FileUploadImpl();
	static PropertyReaderService propertyService = new PropertyReaderServiceImpl();
	
	public ProfilerUtil(){}	
	public static ProfilerUtil profileUtil;
	
	public static ProfilerUtil getInstance(){
		if(profileUtil!=null)
			return profileUtil;
		else
			return new ProfilerUtil();
	}
	public String generatedInteger(){
		return (new Random().nextInt(Integer.MAX_VALUE)+1)+"" ;		
	}	
	
	public String serviceURL(){
		return propertyService.getKeyValue("contentServerURL") + propertyService.getKeyValue("serviceURL");		
	}
	
	public String alfrescoContextURL(){
		return propertyService.getKeyValue("contentServerURL") + propertyService.getKeyValue("alfrescocontext");
	}
	 
	public static boolean notEmpty(String s) {
		 return (s != null && s.length() > 0);
	}
	
	public static boolean nodeNotEmpty(Node n) {
		 return (n != null);
	}
	
	
	public String getAlfTicket(String _userName, String _password)
	   {
	      String _ticket = "";

	      URL url;
	      HttpURLConnection connection = null;
	      try
	      {
	         String urlParameters = "{ \"username\" : \"" + _userName +"\", \"password\" : \"" + _password +"\" }";
	         
	         // Create connection
	         url = new URL("http://localhost:8080/alfresco/service/api/login");
	         connection = (HttpURLConnection) url.openConnection();

	         connection.setRequestMethod("POST");
	         connection.setRequestProperty("Content-Type", "application/json");
	         connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
	         connection.setRequestProperty("Content-Language", "en-US");
	         connection.setUseCaches(false);
	         connection.setDoInput(true);
	         connection.setDoOutput(true);

	         // Send request
	         DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
	         wr.writeBytes(urlParameters);
	         wr.flush();
	         wr.close();

	         // Get Response
	         InputStream is = connection.getInputStream();
	         BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	         String line;
	         StringBuffer response = new StringBuffer();
	         while ((line = rd.readLine()) != null)
	         {
	            response.append(line);
	            response.append('\r');
	         }
	         rd.close();
	         String _jsonResponse = response.toString();
	         
	         JSONObject _jsonResponseObject = (JSONObject)new JSONParser().parse(_jsonResponse);
	          JSONObject jsonDataObject = (JSONObject)new JSONParser().parse(_jsonResponseObject.get("data").toString());
	          _ticket = jsonDataObject.get("ticket").toString();

	      }
	      catch (Exception e)
	      {

	         e.printStackTrace();
	         return null;

	      }
	      finally
	      {

	         if (connection != null)
	         {
	            connection.disconnect();
	         }
	      }
	      return _ticket;
	   }
	
	public static String readSessionConfig(String key){
		String value = "";
		HttpSession session=ServletActionContext.getRequest().getSession(false);
		if(session==null || session.getAttribute(key)==null){
			value = propertyService.getKeyValue(key);
			session.setAttribute(key, value);
		}else{
			value = (String)session.getAttribute(key);
		}
		value = value==null? "": value;
		return value;
	}

}
