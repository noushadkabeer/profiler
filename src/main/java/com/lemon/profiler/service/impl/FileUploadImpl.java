package com.lemon.profiler.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;

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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.lemon.profiler.common.ProfilerUtil;
import com.lemon.profiler.constants.ProfilerConstants;
import com.lemon.profiler.exceptions.ConnectionFailedException;
import com.lemon.profiler.model.Attachment;
import com.lemon.profiler.model.Profile;
import com.lemon.profiler.service.AlfrescoFileService;
import com.lemon.profiler.service.AuthenticationService;
import com.lemon.profiler.service.PropertyReaderService;

public class FileUploadImpl implements AlfrescoFileService {
	
	private static final Logger log = Logger.getLogger(FileUploadImpl.class);
	AuthenticationService authService = new AuthenticationServiceImpl().getInstance();
	PropertyReaderService propS = PropertyReaderServiceImpl.getInstance();
	
	@Override
	public String uploadDocument(String profileId, File file, String fileName, String fileContentType,
			String filesDirectory) throws ConnectionFailedException,
			IOException {
		String strURL = propS.getKeyValue("contentServerURL")
				+ propS.getKeyValue("serviceURL")+"uploadNewProfile";
		String ticket = "";
		PostMethod post = new PostMethod(strURL);
		String uploadedPath = "";
		ticket = authService.readTicket(ProfilerConstants.USERTYPE_USER);
		if (ticket != null && !ticket.isEmpty()) {			
			post.addParameter("alf_ticket", ticket);
			try {
				log.info("Ticket generated.. uploading file "+fileName + strURL);
				Part[] parts = { new StringPart("fileName", fileName),
						new StringPart("filesDirectory", filesDirectory),
						new FilePart("file", file),
						new StringPart("mimetype",fileContentType),
						new StringPart("id", profileId)};
				post.setRequestEntity(new MultipartRequestEntity(parts, post
						.getParams()));
				InputStream in = null;
				HttpClient client = new HttpClient();
				Credentials loginCreds = new UsernamePasswordCredentials(
						"admin", "admin");
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
		return "success";
	}
	

	@Override
	public String transformForPreview(String profileId)
			throws ConnectionFailedException, IOException {
		// TODO Auto-generated method stub
		try{
			String strURL = propS.getKeyValue("contentServerURL")
					+ propS.getKeyValue("serviceURL")+"transformProfile";
			String ticket = "";
			
			//InputStream in4 = null;
			com.lemon.profiler.model.Node node = null;
			ArrayList<com.lemon.profiler.model.Node> workList = new ArrayList<com.lemon.profiler.model.Node>();
			ticket = authService.readTicket(ProfilerConstants.USERTYPE_USER);
			if (ticket != null && !ticket.isEmpty()) {		
				strURL = strURL+"?alf_ticket=" + ticket;
				//post.addParameter("alf_ticket", ticket);				
				PostMethod post = new PostMethod(strURL); 			
				try {
					
					InputStream in2 = null;
					String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?><id>"+profileId+"</id>"; 
					log.info("Xml : " + xmlString);
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder;
					builder = factory.newDocumentBuilder();
					InputSource is = new InputSource();
					is.setCharacterStream(new StringReader(xmlString));
					Document document = null;
					document = builder.parse(is);
					String message = document.getDocumentElement().getTextContent();
					log.info("Got the msg and displaying it :" + message); 
					Part[] parts = { new StringPart("id", profileId),
							new StringPart("id", profileId)				
					}; 
					HttpClient client = new HttpClient(); 			
					post.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
							new DefaultHttpMethodRetryHandler(3, false));
					post.setRequestEntity(new MultipartRequestEntity(parts, post
							.getParams()));
					log.info(post);
					int statusCode = client.executeMethod(post);
					//log.info("Status after delete :"+statusCode);
					if (statusCode != -1) {
						in2 = post.getResponseBodyAsStream();
						DocumentBuilderFactory factory2 = DocumentBuilderFactory
								.newInstance();
						DocumentBuilder builder2 = factory2.newDocumentBuilder();
						Document doc3 = builder2.parse(in2);
						log.info(in2 + "  -------"); 
						TransformerFactory factory7 = TransformerFactory.newInstance();
						Transformer xform = factory7.newTransformer(); 
						xform.transform(new DOMSource(doc3), new StreamResult(
								System.out));
						NodeList itemLst = doc3.getElementsByTagName("node");
						try {
//							for (int i = 0; i < itemLst.getLength(); i++) {
//								Node item = itemLst.item(i);
//								if (item.getNodeType() == Node.ELEMENT_NODE) {
//									Element ielem = (Element) item;
//									NodeList nodelist = ielem
//											.getElementsByTagName("node");
									for (int gti = 0; gti < itemLst
											.getLength(); gti++) {
										node = new com.lemon.profiler.model.Node();
										Node taskItem = itemLst.item(gti);
										if (taskItem.getNodeType() == Node.ELEMENT_NODE) {
											Element taskelem = (Element) taskItem;
											node.setId(taskelem.getElementsByTagName("id").item(0).getTextContent());
											node.setName(taskelem.getElementsByTagName("name").item(0).getTextContent()); 										
										}
										workList.add(node);
										log.info("Added transformed node "+node.getId());
									}

						} catch (Exception e) {
							log.info("Error in transformation! ");
							e.printStackTrace();
						}
					}
					 
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					post.releaseConnection();
					if(workList.size()>0){
						log.info("Everything fine, returning ::> "+ workList.get(0).getId()+"/"+workList.get(0).getName());
						return workList.get(0).getId();
					}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "failure";
	}
	
}
