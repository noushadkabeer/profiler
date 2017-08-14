package com.lemon.profiler.test;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lemon.profiler.common.ProfilerUtil;
import com.lemon.profiler.service.AuthenticationService;
import com.lemon.profiler.service.PropertyReaderService;
import com.lemon.profiler.service.impl.AuthenticationServiceImpl;
import com.lemon.profiler.service.impl.PropertyReaderServiceImpl;


public class JsonReader {

  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }

  public static void main(String[] args) throws IOException, JSONException {
	  File f = new File("C:\\Noushad\\Personal\\Profiler\\Resumes\\json\\Mansoor_Admin.doc.json");
      if (f.exists()){
          InputStream is = new FileInputStream(f);
          String jsonTxt = IOUtils.toString(is);
          System.out.println(jsonTxt);
          JSONObject json = new JSONObject(jsonTxt);       
//          String a = json.getString("basics");
//          JSONObject json1 = new JSONObject(a);  
//          String a2 = json1.getString("email");
//          System.out.println(a.toString()+" LL "+a2);
//          
//          JSONArray dataJsonArray = json.getJSONArray("misc");
//          for(int i=0; i<dataJsonArray.length(); i++) {
//             JSONObject dataObj = (JSONObject)dataJsonArray.get(i);
//             String id = dataObj.getString("Personal Strength");
//             System.out.println(id.toString());
             //Similarly you can extract for other fields.
 //         }
      }
    
    
    //Method 2
    
  }
  
  
  public String savePDF(String uId){
		String filename = "";
		 try
	        {
				String strURL = propS.getKeyValue("contentServerURL")+propS.getKeyValue("alfrescocontext")+"/service/api/node/content/workspace/SpacesStore/"+uId+"?alf_ticket="+new ProfilerUtil().getAlfTicket("admin", "admin");
				HttpClient client = new HttpClient();
				GetMethod method = new GetMethod(strURL); 
				int statusCode = client.executeMethod(method);
				System.out.println(statusCode);
				if (statusCode != -1) { 
		            BufferedInputStream bis = new BufferedInputStream(method.getResponseBodyAsStream());
		         
		         //   File dir = new File("C:\\Noushad\\Personal\\Profiler\\src\\main\\webapp\\pdfjs");
		            File dir = new File( System.getProperty("java.io.tmpdir") );
		    		 filename = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), "pdf");
		    		File file = new File(dir, filename);
		    		
		    		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
		   	     //       BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
		   	            int inByte;
		   	            while((inByte = bis.read()) != -1) bos.write(inByte);
		   	            bis.close();
		   	            bos.close();

		   	            System.out.println( "File Saved :"+file.getAbsolutePath());
		   	
				}
			} catch (Exception e)
	        {
	            e.printStackTrace();
	        } 
		 return filename;
	}AuthenticationService authService = new AuthenticationServiceImpl().getInstance();
	PropertyReaderService propS = PropertyReaderServiceImpl.getInstance();
}