package com.lemon.profiler.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.lemon.profiler.common.ProfilerUtil;
import com.lemon.profiler.service.AlfrescoFileService;
import com.lemon.profiler.service.AuthenticationService;
import com.lemon.profiler.service.PropertyReaderService;
import com.lemon.profiler.service.impl.AuthenticationServiceImpl;
import com.lemon.profiler.service.impl.FileUploadImpl;
import com.lemon.profiler.service.impl.PropertyReaderServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

public class PDFPreview extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5492473667323315847L;
	private static final Logger log = Logger.getLogger(PDFPreview.class);
	
	public String fileUrl;
	public String actionUrl;
	public String profileId;
	AuthenticationService authService = new AuthenticationServiceImpl().getInstance();
	PropertyReaderService propS = PropertyReaderServiceImpl.getInstance();
	
	@Override
	public String execute(){
		try{
			log.info("Request received to render >>"+profileId);
			String actionPath = "/pdfjs/viewer.jsp";
			fileService = new FileUploadImpl();
			String transformedNode = fileService.transformForPreview(profileId);
			if(!"failure".equals(transformedNode)){
				
//			PropertyReaderService propS = PropertyReaderServiceImpl.getInstance();
//			transformedNode = transformedNode.replace(" ", "%20");
//			
//			String authString = "admin:admin";
//			log.info("auth string: " + authString);
//			byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
//			String authStringEnc = new String(authEncBytes);
//			log.info("Base64 encoded auth string: " + authStringEnc);
//
//		//	URL url = new URL(webPage);
////			URLConnection urlConnection = url.openConnection();
////			urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
//			//InputStream is = urlConnection.getInputStream();
//			
//			
//			log.info("Result URL >>"+propS.getKeyValue("contentServerURL")+propS.getKeyValue("alfrescocontext")+"/d/a/workspace/SpacesStore/"+transformedNode);
//		URL url = new URL(propS.getKeyValue("contentServerURL")+propS.getKeyValue("alfrescocontext")+"/d/a/workspace/SpacesStore/"+transformedNode);
//		String myUrl = URLDecoder.decode(url.toString(), "UTF-8");
//        String fileUrl = myUrl.replace("\\/", "/").replace(" ", "%20");
//	//	String tDir = System.getProperty("java.io.tmpdir"); 
//        
//		URLConnection urlConnection = url.openConnection();
//		urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
//
//		String ext = "pdf";
//		// ServletActionContext.getRequest();
//		File dir = new File( ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/pdfjs"));
//		String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
//		File file = new File(dir, name);
//		String filePath = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/");
//		File fileToCreate = new File(filePath, "test");
//		//String path = getText("tempResumeDownloadURL") + "tmp" + ".pdf"; 
//		//File file = new File(path); 
//		//file.deleteOnExit(); 
//		//String contextPath = request.getContextPath() +  "/pdfjs";
//		FileUtils.copyURLToFile(url, file);
//		log.info(url+" >> O_o  :> "+ file);
//		fileUrl = file.getPath();
//		log.info(fileUrl);
//		//FileOutputStream file = new FileOutputStream(new File("/pdfjs/temp/001.pdf"));
//		//FileUtils.copyURLToFile(url, file);
		actionUrl = actionPath+"?file="+savePDF(transformedNode);
				log.info(" Returning : "+actionUrl);
				return "success";
			}
			else{
				return "failure";
			}
		}catch(Exception e){
//			log.info(e.getStackTrace());
			e.printStackTrace();
		}
		return "success";
	}

	public String getActionUrl() {
		return actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	
	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	AlfrescoFileService fileService;
	
	public String savePDF(String uId){
		String filename = "";
		 try
	        {
				String strURL = propS.getKeyValue("contentServerURL")+propS.getKeyValue("alfrescocontext")+"/service/api/node/content/workspace/SpacesStore/"+uId+"?alf_ticket="+new ProfilerUtil().getAlfTicket(propS.getKeyValue("adminuser"), propS.getKeyValue("adminpassword"));
				HttpClient client = new HttpClient();
				GetMethod method = new GetMethod(strURL); 
				int statusCode = client.executeMethod(method);
				log.info(statusCode);
				if (statusCode != -1) { 
		            BufferedInputStream bis = new BufferedInputStream(method.getResponseBodyAsStream());
		         
		         //   File dir = new File("C:\\Noushad\\Personal\\Profiler\\src\\main\\webapp\\pdfjs");
		            File dir = new File( ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/pdfjs"));
		    		 filename = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), "pdf");
		    		File file = new File(dir, filename);
		    		
		    		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
		   	     //       BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
		   	            int inByte;
		   	            while((inByte = bis.read()) != -1) bos.write(inByte);
		   	            bis.close();
		   	            bos.close();

		   	            log.info( "File Saved :"+file.getAbsolutePath());
		   	
				}
			} catch (Exception e)
	        {
	            e.printStackTrace();
	        } 
		 return filename;
	}
	
	public static void main(String args[]){
		HttpClient client = new HttpClient();
        try
        {
			String strURL = "http://127.0.0.1:8080/alfresco/service/api/node/content/workspace/SpacesStore/ad34a51e-ab14-41fd-ab89-4bdd0c3cff9f?alf_ticket="+new ProfilerUtil().getAlfTicket("admin", "admin");
			
			GetMethod method = new GetMethod(strURL);
			// Add any parameter if u want to send it with Post req.  
			// method.addParameter("u", "admin");
			// method.addParameter("p", "admin");
		//	method.getParams().setParameter("alf_ticket", new ProfilerUtil().getAlfTicket("admin", "admin")); 
			int statusCode = client.executeMethod(method);
			log.info(statusCode);
			if (statusCode != -1) {
				//HttpEntity entity = dresponse.getEntity();
	            BufferedInputStream bis = new BufferedInputStream(method.getResponseBodyAsStream());
	         
	            File dir = new File("C:\\Noushad\\Personal\\Profiler\\src\\main\\webapp\\pdfjs");
	            //File dir = new File( ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/pdfjs"));
	    		String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), "pdf");
	    		File file = new File(dir, name);
	    		
	    		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
	   	     //       BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
	   	            int inByte;
	   	            while((inByte = bis.read()) != -1) bos.write(inByte);
	   	            bis.close();
	   	            bos.close();

	   	            log.info( "C:\\Noushad\\Personal\\Profiler\\src\\main\\webapp\\pdfjs"+name  );
	   	
			}
		} catch (Exception e)
        {
            e.printStackTrace();
        } 
	}
	
	
	public static void main2(String args[]){
		
		 DefaultHttpClient dhttpclient = new DefaultHttpClient();

	        String paramname ="alf_ticket";// propS.getKeyValue("adminuser");
	        String ticket = "admin";//propS.getKeyValue("adminpassword");
	        ProfilerUtil pU = new ProfilerUtil();
	        ticket = pU.getAlfTicket("admin", "admin");
	        String host = "http://127.0.0.1:8080/";
	        String uri = "http://127.0.0.1:8080/alfresco/d/a/workspace/SpacesStore/b2696b9c-69ee-48af-9b2f-e3bfcdf1a5dc/Noushad%20Kabeer(30-06-1983).pdf";

	        try
	        {
//	            dhttpclient.getCredentialsProvider().setCredentials(new AuthScope(host, AuthScope.ANY_PORT), new UsernamePasswordCredentials(username, password));
	            HttpGet dhttpget = new HttpGet(uri);

	            URI urid = new URIBuilder(dhttpget.getURI()).addParameter("alf_ticket",
	            		ticket).build();
	            ((HttpRequestBase) dhttpget).setURI(urid);
	            log.info(">>>>>>>>>>> "+urid.toString());
	            log.info("executing request " + dhttpget.getRequestLine());
	            HttpResponse dresponse = dhttpclient.execute(dhttpget);
	            HttpEntity entity = dresponse.getEntity();
	            BufferedInputStream bis = new BufferedInputStream(entity.getContent());
	         
	            File dir = new File("C:\\Noushad\\Personal\\Profiler\\src\\main\\webapp\\pdfjs");
	            //File dir = new File( ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/pdfjs"));
	    		String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), "pdf");
	    		File file = new File(dir, name);
	    	//	String filePath = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/");
	    //		File fileToCreate = new File(filePath, "test");
	    		
	    		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
	     //       BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
	            int inByte;
	            while((inByte = bis.read()) != -1) bos.write(inByte);
	            bis.close();
	            bos.close();

	            log.info(dresponse.getStatusLine()  +"C:\\Noushad\\Personal\\Profiler\\src\\main\\webapp\\pdfjs"+name  );
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        finally
	        {
	            dhttpclient.getConnectionManager().shutdown();
	        }
	}

	public static void mains(String args[]){
		try{
		String actionPath = "/pdfjs/viewer.jsp";
		//URL url = new URL("http://127.0.0.1:8080/alfresco/d/a/workspace/SpacesStore/2a25c849-701f-411e-aafb-1a292b4394e9/982604980Arslan_Resume_.pdf");
		//URL url = new URL("http://127.0.0.1:8080/alfresco/d/a/workspace/SpacesStore/b2696b9c-69ee-48af-9b2f-e3bfcdf1a5dc/Noushad%20Kabeer(30-06-1983).pdf");
		//URL url = new URL("http://127.0.0.1:8080/alfresco/d/a/workspace/SpacesStore/f59f4a18-c196-4f11-bdb6-2d1319856205/982604980Arslan_Resume_.pdf");
		URL url = new URL("http://127.0.0.1:8080/alfresco/d/a/workspace/SpacesStore/d26f891f-fc4d-4c24-8015-ff6735484b1c/9826661CV.pdf");
		
		String myUrl = URLDecoder.decode(url.toString(), "UTF-8");
        String fileUrl = myUrl.replace("\\/", "/").replace(" ", "%20");
	//	String tDir = System.getProperty("java.io.tmpdir"); 

		String ext = "pdf";
		File dir = new File("C:\\Noushad\\Personal\\Profiler\\src\\main\\webapp\\pdfjs");
		String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
		File file = new File(dir, name);
		
		//String path = getText("tempResumeDownloadURL") + "tmp" + ".pdf"; 
		//File file = new File(path); 
		//file.deleteOnExit(); 
		FileUtils.copyURLToFile(url, file);
		fileUrl = file.getPath();
		log.info(fileUrl);
		//FileOutputStream file = new FileOutputStream(new File("/pdfjs/temp/001.pdf"));
		//FileUtils.copyURLToFile(url, file);
		//actionUrl = actionPath+"?file="+fileUrl;
		log.info(fileUrl + " %% ");
		}catch(Exception e){
			log.info(e.getStackTrace());
		}
	}
}
