package com.lemon.profiler.service.impl;

import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.lemon.profiler.common.ProfilerUtil;
import com.lemon.profiler.constants.ProfilerConstants;
import com.lemon.profiler.mappers.pagination.UnprocessedProfileSearchCriteria;
import com.lemon.profiler.mappers.pagination.UnprocessedProfileSearchResults;
import com.lemon.profiler.model.Attachment;
import com.lemon.profiler.model.Profile;
import com.lemon.profiler.service.AuthenticationService;
import com.lemon.profiler.service.PropertyReaderService;
import com.lemon.profiler.service.UnprocessedProfileService;
import com.lemon.profiler.util.ProfileJSON2JavaMapper;


public class UnprocessedProfileServiceImpl implements UnprocessedProfileService{
	PropertyReaderService propS = new PropertyReaderServiceImpl();
	AuthenticationService authService = new AuthenticationServiceImpl();
	private static final Logger log = Logger.getLogger(UnprocessedProfileServiceImpl.class);
	private ArrayList<String> resultList;

    public ArrayList<String> getResultList() {
		return resultList;
	}
	public void setResultList(ArrayList<String> resultList) {
		this.resultList = resultList;
	}

	Log logger = LogFactory.getLog(this.getClass());
	
	
	@Override
	public ArrayList<Profile> obtainUnProcessedProfileList(String textToSearch, String pageNum, String pageSize) { 
		String ticket = authService.readTicket(ProfilerConstants.USERTYPE_USER);
		String strURL = propS.getKeyValue("contentServerURL") + propS.getKeyValue("applicationServiceURL")+"getUPProfileContent";
		InputStream in3 = null;
		com.lemon.profiler.model.Node node = null;
		ArrayList<com.lemon.profiler.model.Node> workList = new ArrayList<com.lemon.profiler.model.Node>();
		ArrayList<Profile> profiles = new ArrayList<Profile>(); 
		Profile profile;
		PostMethod post = new PostMethod(strURL);
		try { 
			//if (ticket != null && ticket.isEmpty() && ticket.equals(authService.validateTicket(ticket))) { 
				HttpClient client = new HttpClient();
				PostMethod method = new PostMethod(strURL);
				method.addParameter("stringToSearch", textToSearch);
				log.info("Finding Unprocessed Resumes: search >> "+textToSearch+" with "+pageNum+" and "+pageSize);
				method.addParameter("alf_ticket", ticket);
				method.addParameter("pagenum", pageNum);
				method.addParameter("pagesize", pageSize);
				int statusCode = client.executeMethod(method);
				log.info(statusCode);
				if (statusCode != -1) {
					in3 = method.getResponseBodyAsStream();
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					Document doc = builder.parse(in3);
					NodeList attachmentLst;
					Node attachment;
					doc.getDocumentElement().normalize();
					TransformerFactory factory7 = TransformerFactory.newInstance();
					Transformer xform = factory7.newTransformer();
					xform.transform(new DOMSource(doc), new StreamResult(System.out));
					NodeList itemLst = doc.getElementsByTagName("node");
					NodeList profilesList;
					Node profileNode;					
					try {
							for (int gti = 0; gti < itemLst.getLength(); gti++) {
								log.info("Processing.. "+gti+" th Node");
								node = new com.lemon.profiler.model.Node();
								Node uppNode = itemLst.item(gti);
								if (uppNode.getNodeType() == Node.ELEMENT_NODE) {									
									Element taskelem = (Element) uppNode;
									profile = new Profile();
									node.setId(taskelem.getElementsByTagName("id").item(0).getTextContent());
									node.setName(taskelem.getElementsByTagName("name").item(0).getTextContent()); 
									
									
									
									profilesList = taskelem.getElementsByTagName("profile");
							//		profilesList = getDirectChild(taskelem, "profile").getChildNodes();
									for (int j = 0; j < profilesList.getLength(); j++) {
										log.info("Processing.. "+j+" th profile");
										profileNode = profilesList.item(j);
										if (profileNode.getNodeType() == Node.ELEMENT_NODE) {
											Element element2 = (Element) profilesList.item(j); 
											
											String id = ProfilerUtil.nodeNotEmpty(element2.getElementsByTagName("id").item(0))
													?element2.getElementsByTagName("id").item(0).getTextContent():"";
											String name =ProfilerUtil.nodeNotEmpty(element2.getElementsByTagName("name").item(0))
													?element2.getElementsByTagName("name").item(0).getTextContent():"";
											String title = ProfilerUtil.nodeNotEmpty(element2.getElementsByTagName("title").item(0))
													?element2.getElementsByTagName("title").item(0).getTextContent():"";
											String url=ProfilerUtil.getInstance().alfrescoContextURL()+ (ProfilerUtil.nodeNotEmpty(element2.getElementsByTagName("url").item(0))
													?element2.getElementsByTagName("url").item(0).getTextContent():"");
																							
											profile.setId(id);
											log.info("ID set for profile >> "+id +" and Name as "+name);
											profile.setName(name);	log.info("Added new ID :"+profile.id);												
										}
										
									}	
									
									attachmentLst = taskelem.getElementsByTagName("jsoncontent");  
									//attachmentLst = attachmentLst.item(gti).getChildNodes();
									for (int j1 = 0; j1 < attachmentLst.getLength(); j1++) {
										log.info("Processing.. "+j1+" th jsoncontent");
										attachment = attachmentLst.item(j1);
										if (attachment.getNodeType() == Node.ELEMENT_NODE) {
											Element element3 = (Element) attachmentLst.item(j1);
											
											String jsonCid = ProfilerUtil.nodeNotEmpty(element3.getElementsByTagName("id").item(0))
													?element3.getElementsByTagName("id").item(0).getTextContent():"";
											String jsonCname =ProfilerUtil.nodeNotEmpty(element3.getElementsByTagName("name").item(0))
													?element3.getElementsByTagName("name").item(0).getTextContent():"";
											String jsonCtitle = ProfilerUtil.nodeNotEmpty(element3.getElementsByTagName("title").item(0))
													?element3.getElementsByTagName("title").item(0).getTextContent():"";
											String jsonCurl=ProfilerUtil.getInstance().alfrescoContextURL()+ (ProfilerUtil.nodeNotEmpty(element3.getElementsByTagName("url").item(0))
													?element3.getElementsByTagName("url").item(0).getTextContent():"");
									//		profile.setAttachments(new Attachment(jsonCid,jsonCname, jsonCtitle,jsonCurl));
											log.info("Attachment set for profile >> "+jsonCid +" and Name as "+jsonCname);
											profile.setAttachments(new Attachment(jsonCid,jsonCname, jsonCtitle,jsonCurl));
										}
									}
									profiles.add(profile);
									log.info("Added profile ::> "+profile.getId());
								} 
								
							}
							//}
						//}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				log.info("total worklist size :" + workList.size());
				//}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				post.releaseConnection();
			}
		return profiles;
	} 
	 
	@Override
	public String obtainUnProcessedProfileCount(String textToSearch, String pageNum, String pageSize) { 
		String ticket = authService.readTicket(ProfilerConstants.USERTYPE_USER);
		String strURL = propS.getKeyValue("contentServerURL") + propS.getKeyValue("applicationServiceURL")+"getUPProfileCount";
		InputStream in3 = null;  
		PostMethod post = new PostMethod(strURL);
		String totalSize ="";
		try { 
			//if (ticket != null && ticket.isEmpty() && ticket.equals(authService.validateTicket(ticket))) { 
				HttpClient client = new HttpClient();
				PostMethod method = new PostMethod(strURL);
				method.addParameter("stringToSearch", textToSearch);
				log.info("Finding UP Profiles..");
				log.info("Added String to search.. "+textToSearch+" with "+pageNum+" and "+pageSize);
				method.addParameter("alf_ticket", ticket);
				method.addParameter("pagenum", pageNum);
				method.addParameter("pagesize", pageSize);
				int statusCode = client.executeMethod(method);
				log.info(statusCode);
				if (statusCode != -1) {
					in3 = method.getResponseBodyAsStream();
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					Document doc = builder.parse(in3); 
					doc.getDocumentElement().normalize();
					TransformerFactory factory7 = TransformerFactory.newInstance();
					Transformer xform = factory7.newTransformer();
					xform.transform(new DOMSource(doc), new StreamResult(System.out));
					
					totalSize =ProfilerUtil.nodeNotEmpty(doc.getElementsByTagName("totalSize").item(0))
							?doc.getElementsByTagName("totalSize").item(0).getTextContent():""; 
					
				}
				log.info("total worklist size :" + totalSize);
				//}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				post.releaseConnection();
			}
		return totalSize;
	} 
	 
	@Override
	public void insertUPProfile(Profile profile) {
		log.info("Started UP Profile insertion");
		String ticket = authService.readTicket(ProfilerConstants.USERTYPE_USER);	
		 String strURL =  ProfilerUtil.getInstance().serviceURL()+ "uploadMoveDeleteUPProfile?alf_ticket="+ticket;  
			PostMethod post = new PostMethod(strURL); 
			log.info("Creating a new profile "+strURL);
			//Before insert generate an unique id for the record
			
			//profile.setId(ProfilerUtil.getInstance().generatedInteger());	
			//post.addParameter("alf_ticket", ticket);
			try { 
				InputStream in2 = null;
				String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?><storeid>"+profile.getId()+"</storeid>"; 
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
				Part[] parts = { new StringPart("id", ProfilerUtil.getInstance().generatedInteger()),
						new StringPart("education", profile.getEducation()),
						new StringPart("experience", profile.getExperience()),
						new StringPart("interests", profile.getInterests()),
						new StringPart("skills", profile.getSkills()),
						new StringPart("name", profile.getName().trim()),
						new StringPart("location", profile.getLocation()),
						new StringPart("address", profile.getAddress()),
						new StringPart("resumesummary", profile.getResumeSummary()),
						new StringPart("nodeid", profile.getId())
				};

				log.info(parts.length + parts.toString()+ profile.getAddress()+profile.getResumeSummary());
				HttpClient client = new HttpClient();
				// PostMethod method = new PostMethod(strURL);
				//Credentials loginCreds = new UsernamePasswordCredentials("admin",	"admin");
				//client.getState().setCredentials(AuthScope.ANY, loginCreds);
				
				post.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
						new DefaultHttpMethodRetryHandler(3, false));
				post.setRequestEntity(new MultipartRequestEntity(parts, post.getParams()));
				int statusCode = client.executeMethod(post);
				log.info(statusCode);
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
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				post.releaseConnection();
			}        		
		
		//log.info("Added one and now size is :"+profileList.size());		
		
	}
	@Override
	public UnprocessedProfileSearchResults<Profile> obtainUnProcessedProfileList(
			UnprocessedProfileSearchCriteria upsc) {
		UnprocessedProfileSearchResults<Profile> searchResults = new UnprocessedProfileSearchResults<Profile>();
		String ticket = authService.readTicket(ProfilerConstants.USERTYPE_USER);
		String strURL = propS.getKeyValue("contentServerURL") + propS.getKeyValue("applicationServiceURL")+"getUPProfileContent";
		InputStream in3 = null;
		com.lemon.profiler.model.Node node = null;
		ArrayList<com.lemon.profiler.model.Node> workList = new ArrayList<com.lemon.profiler.model.Node>();
		ArrayList<Profile> profiles = new ArrayList<Profile>(); 
		Profile profile;
		PostMethod post = new PostMethod(strURL);
		try { 
			//if (ticket != null && ticket.isEmpty() && ticket.equals(authService.validateTicket(ticket))) { 
				HttpClient client = new HttpClient();
				PostMethod method = new PostMethod(strURL);
				String searchQ = upsc.getSearchQuery() == null? ProfilerConstants.PROP_SEARCH_UP_LOC: upsc.getSearchQuery();
				method.addParameter("stringToSearch", searchQ);
				log.info("Finding UP Profiles..");
				log.info("Added String to search.. "+searchQ+" with "+upsc.getPageNum()+" and "+upsc.getPageSize());
				method.addParameter("alf_ticket", ticket);
				method.addParameter("pagenum", ""+upsc.getPageNum());
				method.addParameter("pagesize", ""+upsc.getPageSize());
				int statusCode = client.executeMethod(method);
				StringWriter sw = new StringWriter();
				log.info(statusCode);
				if (statusCode != -1) {
					in3 = method.getResponseBodyAsStream();
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					Document doc = builder.parse(in3);
					NodeList attachmentLst;
					Node attachment;
					doc.getDocumentElement().normalize();
					TransformerFactory factory7 = TransformerFactory.newInstance();
					Transformer xform = factory7.newTransformer();
					xform.transform(new DOMSource(doc), new StreamResult(sw));
					NodeList itemLst = doc.getElementsByTagName("node");
					NodeList profilesList;
					Node profileNode;
					Profile mappedLocalProfile = null;
					try {
							for (int gti = 0; gti < itemLst.getLength(); gti++) {								
								node = new com.lemon.profiler.model.Node();
								Node uppNode = itemLst.item(gti);
								if (uppNode.getNodeType() == Node.ELEMENT_NODE) {									
									Element taskelem = (Element) uppNode;
									profile = new Profile();
									node.setId(taskelem.getElementsByTagName("id").item(0).getTextContent());
									node.setName(taskelem.getElementsByTagName("name").item(0).getTextContent()); 
									
									log.info("Processing.. "+gti+" th UP Node :: Name(" +node.getName()+") & ID ("+node.getId()+")");
									
									
									profilesList = taskelem.getElementsByTagName("profile");
							//		profilesList = getDirectChild(taskelem, "profile").getChildNodes();
									for (int j = 0; j < profilesList.getLength(); j++) {
										//log.info("Processing.. "+j+" th profile");
										profileNode = profilesList.item(j);
										if (profileNode.getNodeType() == Node.ELEMENT_NODE) {
											Element element2 = (Element) profilesList.item(j); 
											
											String id = ProfilerUtil.nodeNotEmpty(element2.getElementsByTagName("id").item(0))
													?element2.getElementsByTagName("id").item(0).getTextContent():"";
											String name =ProfilerUtil.nodeNotEmpty(element2.getElementsByTagName("name").item(0))
													?element2.getElementsByTagName("name").item(0).getTextContent():"";
											String title = ProfilerUtil.nodeNotEmpty(element2.getElementsByTagName("title").item(0))
													?element2.getElementsByTagName("title").item(0).getTextContent():"";
											String url=ProfilerUtil.getInstance().alfrescoContextURL()+ (ProfilerUtil.nodeNotEmpty(element2.getElementsByTagName("url").item(0))
													?element2.getElementsByTagName("url").item(0).getTextContent():"");
											log.info("Identified Profile : "+id);												
											profile.setId(id);
											//log.info("ID set for profile >> "+id +" and Name as "+name);
											profile.setName(name);	//log.info("Added new ID :"+profile.id);												
										}
										
									}	
									
									attachmentLst = taskelem.getElementsByTagName("jsoncontent");  
									//attachmentLst = attachmentLst.item(gti).getChildNodes();
									for (int j1 = 0; j1 < attachmentLst.getLength(); j1++) {
										
										attachment = attachmentLst.item(j1);
										if (attachment.getNodeType() == Node.ELEMENT_NODE) {
											Element element3 = (Element) attachmentLst.item(j1);
											
											String jsonCid = ProfilerUtil.nodeNotEmpty(element3.getElementsByTagName("id").item(0))
													?element3.getElementsByTagName("id").item(0).getTextContent():"";
											String jsonCname =ProfilerUtil.nodeNotEmpty(element3.getElementsByTagName("name").item(0))
													?element3.getElementsByTagName("name").item(0).getTextContent():"";
											String jsonCtitle = ProfilerUtil.nodeNotEmpty(element3.getElementsByTagName("title").item(0))
													?element3.getElementsByTagName("title").item(0).getTextContent():"";
											String jsonCurl=ProfilerUtil.getInstance().alfrescoContextURL()+ (ProfilerUtil.nodeNotEmpty(element3.getElementsByTagName("url").item(0))
													?element3.getElementsByTagName("url").item(0).getTextContent():"");
											String jsonContent = ProfilerUtil.nodeNotEmpty(element3.getElementsByTagName("jcontent").item(0))
													?element3.getElementsByTagName("jcontent").item(0).getTextContent():"";
											log.info("Read content as such "+jsonContent);
											mappedLocalProfile = new ProfileJSON2JavaMapper().mappedProfile(jsonContent);
											System.out.println("Values ::>"+mappedLocalProfile.getAddress()+", "+mappedLocalProfile.getInterests()+", "
													+mappedLocalProfile.getSkills()+", "+mappedLocalProfile.getLocation()+", "+mappedLocalProfile.getEducation()+", ");
									//		profile.setAttachments(new Attachment(jsonCid,jsonCname, jsonCtitle,jsonCurl));
											log.info("Attachment set for profile("+profile.getId()+") >> "+jsonCid +" and Name as "+jsonCname);
											profile.setAttachments(new Attachment(jsonCid,jsonCname, jsonCtitle,jsonCurl));
											
											if(mappedLocalProfile.getAddress()!=null)
												profile.setAddress(mappedLocalProfile.getAddress());
											if(mappedLocalProfile.getInterests()!=null)
												profile.setInterests(mappedLocalProfile.getInterests());
											if(mappedLocalProfile.getSkills()!=null)
												profile.setSkills(mappedLocalProfile.getSkills());
											if(mappedLocalProfile.getLocation()!=null)
												profile.setLocation(mappedLocalProfile.getLocation());											
											if(mappedLocalProfile.getEducation()!=null)
												profile.setEducation(mappedLocalProfile.getEducation());
											log.info("Update complete");
										}
									}
									profiles.add(profile);
									//log.info("Added profile ::> "+profile.getId());
								} 
								
							}
							//}
						//}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				log.info("total worklist size :" + profiles.size());
				searchResults.setResults(profiles);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				post.releaseConnection();
			}
		return searchResults;
	}
}
