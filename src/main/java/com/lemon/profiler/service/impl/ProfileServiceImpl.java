package com.lemon.profiler.service.impl;

import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import org.apache.commons.lang.StringUtils;
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
import com.lemon.profiler.mappers.pagination.ProfileSearchCriteria;
import com.lemon.profiler.mappers.pagination.ProfileSearchCriteria.ProfileSearchType;
import com.lemon.profiler.mappers.pagination.ProfileSearchResults;
import com.lemon.profiler.model.Attachment;
import com.lemon.profiler.model.Profile;
import com.lemon.profiler.service.AuthenticationService;
import com.lemon.profiler.service.ProfileService;
import com.lemon.profiler.service.PropertyReaderService;
import com.opensymphony.xwork2.ActionContext;

public class ProfileServiceImpl implements ProfileService {

	PropertyReaderService propS = new PropertyReaderServiceImpl();
	AuthenticationService authService = new AuthenticationServiceImpl();
	private static final Logger log = Logger.getLogger(ProfileServiceImpl.class);
	private static ArrayList<Profile> profileList;
	StringWriter sw = new StringWriter();

	static {
		profileList = new ArrayList<Profile>();
		// profileList.add(new
		// Profile("001","Name","Experience","+9762343243","t@t.com","B.Sc","Skills","Interest","Abudhabi","SO14
		// OAT","Resume Summary", new Attachment("id", "attach", "ds", "DD")));
		// profileList.add(new
		// Profile("002","Name2","Experience","+9762343243","t@t.com","B.Sc","Skills","Interest","Abudhabi","SO14
		// OAT","Resume Summary", new Attachment("id1", "attachs", "ds", "DD")));
	}

	Log logger = LogFactory.getLog(this.getClass());

	@Override
	public List<Profile> obtainAllProfiles(String pageNum, String pageSize) {
		String organization = ActionContext.getContext().getSession().get(ProfilerConstants.PROPERTY_USER_ORGANIZATION)
				.toString();
		String luceneQuery = "PATH:\"/app:company_home/st:sites/" + organization
				+ "/cm:documentLibrary/cm:ProfileR//*\" AND TYPE:\"pf:profile\"";
		log.debug("Query :" + luceneQuery + " with PageNum " + pageNum + " and PageSize :" + pageSize);
		return searchInRepository(luceneQuery, pageNum, pageSize, false);
	}

	@Override
	public Profile pullProfileByName(String name) {
		String organization = ActionContext.getContext().getSession().get(ProfilerConstants.PROPERTY_USER_ORGANIZATION)
				.toString();
		String luceneQuery = "PATH:\"/app:company_home/st:sites/" + organization
				+ "/cm:documentLibrary/cm:ProfileR//*\" AND TYPE:\"pf:profile\" AND @cm:name:" + name + "\"";
		List<Profile> profiles = searchInRepository(luceneQuery, "undefined", "undefined", false);
		Profile profile = profiles.get(0);
		// TODO Auto-generated method stub
		return profile;
	}

	@Override
	public Profile pullProfile(String id) {
		List<Profile> profiles = new ArrayList<Profile>();
		String strURL = ProfilerUtil.getInstance().serviceURL() + "findProfileById";
		InputStream in4 = null;
		Profile profile = new Profile();
		PostMethod post = new PostMethod(strURL);
		String ticket = authService.readTicket(ProfilerConstants.USERTYPE_USER);
		log.debug("Ticket Prepared & ready to Search :" + id);
		String organization = ActionContext.getContext().getSession().get(ProfilerConstants.PROPERTY_USER_ORGANIZATION)
				.toString();
		if (ticket != null && !ticket.isEmpty()) {
			try {
				HttpClient client = new HttpClient();
				PostMethod method = new PostMethod(strURL);
				// Add any parameter if u want to send it with Post req.
				method.addParameter("stringToSearch", id);
				// method.addParameter("u", "admin");
				// method.addParameter("p", "admin");
				method.addParameter("organizationid", organization);
				method.addParameter("alf_ticket", ticket);
				int statusCode = client.executeMethod(method);
				log.debug(statusCode);
				if (statusCode != -1) {
					in4 = method.getResponseBodyAsStream();
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					Document doc = builder.parse(in4);
					log.debug("Bfr normalize \n" + doc.getTextContent());
					doc.getDocumentElement().normalize();
					log.debug("Just normalized \n" + doc.getTextContent());
					TransformerFactory factory7 = TransformerFactory.newInstance();
					Transformer xform = factory7.newTransformer();
					xform.transform(new DOMSource(doc), new StreamResult(sw));
					log.debug(doc.getTextContent());
					NodeList itemLst = doc.getElementsByTagName("profile");
					NodeList attachmentLst;
					Node attachment;
					for (int i = 0; i < itemLst.getLength(); i++) {
						Node node = itemLst.item(i);
						profile = new Profile();
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							Element element = (Element) itemLst.item(i);
							log.debug(element.getElementsByTagName("profileId").item(0).getTextContent());
							profile.setId(element.getElementsByTagName("id").item(0).getTextContent());
							profile.setName(element.getElementsByTagName("profileName").item(0).getTextContent());
							profile.setExperience(
									element.getElementsByTagName("profileExperience").item(0).getTextContent());
							profile.setEducation(
									element.getElementsByTagName("profileEducation").item(0).getTextContent());
							profile.setSkills(Arrays.asList(element.getElementsByTagName("profileSkills").item(0)
									.getTextContent().split("\\s*,\\s*")));
							profile.setInterests(
									element.getElementsByTagName("profileInterests").item(0).getTextContent());
							profile.setLocation(
									element.getElementsByTagName("profileLocation").item(0).getTextContent());
							profile.setAddress(element.getElementsByTagName("profileAddress").item(0).getTextContent());
							profile.setResumeSummary(
									element.getElementsByTagName("profileSummary").item(0).getTextContent());
							attachmentLst = doc.getElementsByTagName("attachments");
							for (int j = 0; j < attachmentLst.getLength(); j++) {
								attachment = attachmentLst.item(j);
								if (attachment.getNodeType() == Node.ELEMENT_NODE) {
									Element element2 = (Element) attachmentLst.item(i);

									String sid = ProfilerUtil.nodeNotEmpty(element2.getElementsByTagName("id").item(0))
											? element2.getElementsByTagName("id").item(0).getTextContent()
											: "";
									String name = ProfilerUtil
											.nodeNotEmpty(element2.getElementsByTagName("name").item(0))
													? element2.getElementsByTagName("name").item(0).getTextContent()
													: "";
									String title = ProfilerUtil
											.nodeNotEmpty(element2.getElementsByTagName("title").item(0))
													? element2.getElementsByTagName("title").item(0).getTextContent()
													: "";
									String url = ProfilerUtil.getInstance().alfrescoContextURL()
											+ (ProfilerUtil.nodeNotEmpty(element2.getElementsByTagName("url").item(0))
													? element2.getElementsByTagName("url").item(0).getTextContent()
													: "");
									profile.setAttachments(new Attachment(sid, name, title, url));
								}
							}

							profiles.add(profile);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				post.releaseConnection();
			}
		}
		log.debug("Total Profiles on Search by ID Returned :" + profiles.size());

		profile = profiles.get(0);
		// TODO Auto-generated method stub
		return profile;
	}

	@Override
	public void update(Profile profile) {
		// From here
		String ticket = authService.readTicket(ProfilerConstants.USERTYPE_USER);
		String strURL = ProfilerUtil.getInstance().serviceURL() + "updateProfile?alf_ticket=" + ticket;
		PostMethod post = new PostMethod(strURL);
		String organization = ActionContext.getContext().getSession().get(ProfilerConstants.PROPERTY_USER_ORGANIZATION)
				.toString();
		post.addParameter("alf_ticket", ticket);
		try {
			InputStream in2 = null;
			String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?><storeid>" + profile.getId() + "</storeid>";
			log.debug("Xml : " + xmlString);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlString));
			Document document = null;
			document = builder.parse(is);
			String message = document.getDocumentElement().getTextContent();
			log.debug("Got the msg and displaying it :" + message);
			Part[] parts = { new StringPart("id", profile.getId()), new StringPart("education", profile.getEducation()),
					new StringPart("email", profile.getEmail()),
					new StringPart("contactNo", profile.getContactNo()),
					new StringPart("dob", profile.getDob()),
					new StringPart("experience", profile.getExperience()),
					new StringPart("interests", profile.getInterests()),
					new StringPart("skills", StringUtils.join(profile.getSkills(), ',')),
					new StringPart("name", profile.getName().trim()), new StringPart("location", profile.getLocation()),
					new StringPart("address", profile.getAddress()), new StringPart("organizationid", organization),
					new StringPart("resumeSummary", profile.getResumeSummary()) };

			log.debug(parts.length + parts.toString());
			HttpClient client = new HttpClient();
			// PostMethod method = new PostMethod(strURL);
			// Credentials loginCreds = new UsernamePasswordCredentials("admin","admin");
			// client.getState().setCredentials(AuthScope.ANY, loginCreds);

			post.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
			post.setRequestEntity(new MultipartRequestEntity(parts, post.getParams()));
			log.debug(post);
			int statusCode = client.executeMethod(post);
			log.debug(statusCode);
			if (statusCode != -1) {
				in2 = post.getResponseBodyAsStream();

				DocumentBuilderFactory factory2 = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder2 = factory2.newDocumentBuilder();
				Document doc3 = builder2.parse(in2);
				log.debug(in2 + "  -------");
				TransformerFactory factory7 = TransformerFactory.newInstance();
				Transformer xform = factory7.newTransformer();
				xform.transform(new DOMSource(doc3), new StreamResult(sw));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			post.releaseConnection();
		}
	}

	@Override
	public void insert(Profile profile) {

		String ticket = authService.readTicket(ProfilerConstants.USERTYPE_USER);
		String strURL = ProfilerUtil.getInstance().serviceURL() + "createProfile?alf_ticket=" + ticket;
		PostMethod post = new PostMethod(strURL);
		log.debug("Creating a new profile " + strURL);
		// Before insert generate an unique id for the record
		String organization = ActionContext.getContext().getSession().get(ProfilerConstants.PROPERTY_USER_ORGANIZATION)
				.toString();
		profile.setId(ProfilerUtil.getInstance().generatedInteger());
		// post.addParameter("alf_ticket", ticket);
		try {
			InputStream in2 = null;
			String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?><storeid>" + profile.getId() + "</storeid>";
			log.debug("Xml : " + xmlString);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlString));
			Document document = null;
			document = builder.parse(is);
			String message = document.getDocumentElement().getTextContent();
			log.debug("Got the msg and displaying it :" + message);
			Part[] parts = { new StringPart("id", profile.getId()), new StringPart("education", profile.getEducation()),
					new StringPart("email", profile.getEmail()),
					new StringPart("contactNo", profile.getContactNo()),
					new StringPart("dob", profile.getDob()),
					new StringPart("experience", profile.getExperience()),
					new StringPart("interests", profile.getInterests()),
					new StringPart("skills", StringUtils.join(profile.getSkills(), ',')),
					new StringPart("name", profile.getName().trim()), new StringPart("location", profile.getLocation()),
					new StringPart("address", profile.getAddress()), new StringPart("organizationid", organization),
					new StringPart("resumeSummary", profile.getResumeSummary()) };

			log.debug(parts.length + parts.toString() + profile.getAddress() + profile.getResumeSummary());
			HttpClient client = new HttpClient();
			// PostMethod method = new PostMethod(strURL);
			// Credentials loginCreds = new UsernamePasswordCredentials("admin", "admin");
			// client.getState().setCredentials(AuthScope.ANY, loginCreds);

			post.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
			post.setRequestEntity(new MultipartRequestEntity(parts, post.getParams()));
			int statusCode = client.executeMethod(post);
			log.debug(statusCode);
			if (statusCode != -1) {
				in2 = post.getResponseBodyAsStream();
				DocumentBuilderFactory factory2 = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder2 = factory2.newDocumentBuilder();
				Document doc3 = builder2.parse(in2);
				log.debug(in2 + "  -------");
				TransformerFactory factory7 = TransformerFactory.newInstance();
				Transformer xform = factory7.newTransformer();
				xform.transform(new DOMSource(doc3), new StreamResult(sw));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			post.releaseConnection();
		}

		log.debug("Added one and now size is :" + profileList.size());

	}

	@Override
	public String delete(String id) {
		String result = "success";
		log.debug("Deleting the Profile :" + id);
		// From here
		String ticket = authService.readTicket(ProfilerConstants.USERTYPE_USER);
		String strURL = ProfilerUtil.getInstance().serviceURL() + "deleteProfile?alf_ticket=" + ticket;
		PostMethod post = new PostMethod(strURL);
		post.addParameter("alf_ticket", ticket);
		try {
			InputStream in2 = null;
			String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?><storeid>" + id + "</storeid>";
			log.debug("Xml : " + xmlString);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlString));
			Document document = null;
			document = builder.parse(is);
			String message = document.getDocumentElement().getTextContent();
			log.debug("Got the msg and displaying it :" + message);
			Part[] parts = { new StringPart("id", id), new StringPart("id", id) };
			HttpClient client = new HttpClient();
			post.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
			post.setRequestEntity(new MultipartRequestEntity(parts, post.getParams()));
			log.debug(post);
			int statusCode = client.executeMethod(post);
			log.debug("Status after delete :" + statusCode);
			if (statusCode != -1) {
				in2 = post.getResponseBodyAsStream();
				DocumentBuilderFactory factory2 = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder2 = factory2.newDocumentBuilder();
				Document doc3 = builder2.parse(in2);
				log.debug(in2 + "  -------");
				TransformerFactory factory7 = TransformerFactory.newInstance();
				Transformer xform = factory7.newTransformer();
				xform.transform(new DOMSource(doc3), new StreamResult(sw));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			post.releaseConnection();
		}

		return result;
	}

	@Override
	public List<Profile> searchProfile(String searchString, String pageNum, String pageSize) {
		return searchInRepository(searchString, pageNum, pageSize, true);
	}

	public List<Profile> searchInRepository(String searchString, String pageNum, String pageSize, boolean plainSearch) {
		List<Profile> profiles = new ArrayList<Profile>();
		String strURL = ProfilerUtil.getInstance().serviceURL() + "findProfile";
		InputStream in4 = null;
		Profile profile = new Profile();
		PostMethod post = new PostMethod(strURL);
		String ticket = authService.readTicket(ProfilerConstants.USERTYPE_USER);
		String organization = ActionContext.getContext().getSession().get(ProfilerConstants.PROPERTY_USER_ORGANIZATION)
				.toString();
		log.debug("Ticket Prepared & ready to Search :" + searchString);
		if (ticket != null && !ticket.isEmpty()) {
			try {
				HttpClient client = new HttpClient();
				PostMethod method = new PostMethod(strURL);
				// Add any parameter if u want to send it with Post req.
				if (plainSearch)
					method.addParameter("stringToSearch", searchString);
				else
					method.addParameter("lucenequery", searchString);
				// method.addParameter("u", "admin");
				// method.addParameter("p", "admin");
				method.addParameter("alf_ticket", ticket);
				method.addParameter("pagenum", pageNum);
				method.addParameter("pagesize", pageSize);
				method.addParameter("organizationid", organization);
				int statusCode = client.executeMethod(method);
				log.debug(statusCode);
				if (statusCode != -1) {
					in4 = method.getResponseBodyAsStream();
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					Document doc = builder.parse(in4);
					// log.debug("Bfr normalize \n"
					// + doc.getTextContent());
					doc.getDocumentElement().normalize();
					// log.debug("Just normalized \n"
					// + doc.getTextContent());
					log.debug("Printing the textual content ");
					TransformerFactory factory7 = TransformerFactory.newInstance();
					Transformer xform = factory7.newTransformer();

					xform.transform(new DOMSource(doc), new StreamResult(sw));

					// log.debug(doc.getTextContent());
					NodeList itemLst = doc.getElementsByTagName("profile");
					NodeList attachmentLst;
					Node attachment;
					for (int i = 0; i < itemLst.getLength(); i++) {
						Node node = itemLst.item(i);
						profile = new Profile();
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							Element element = (Element) itemLst.item(i);
							// log.debug(element.getElementsByTagName("profileId").item(0).getTextContent());
							profile.setId(element.getElementsByTagName("id").item(0).getTextContent());
							profile.setName(element.getElementsByTagName("profileName").item(0).getTextContent());
							profile.setExperience(
									element.getElementsByTagName("profileExperience").item(0).getTextContent());
							profile.setEducation(
									element.getElementsByTagName("profileEducation").item(0).getTextContent());
							profile.setSkills(Arrays.asList(element.getElementsByTagName("profileSkills").item(0)
									.getTextContent().split("\\s*,\\s*")));
							profile.setInterests(
									element.getElementsByTagName("profileInterests").item(0).getTextContent());
							profile.setLocation(
									element.getElementsByTagName("profileLocation").item(0).getTextContent());
							profile.setAddress(element.getElementsByTagName("profileAddress").item(0).getTextContent());
							profile.setResumeSummary(
									element.getElementsByTagName("profileSummary").item(0).getTextContent());
							attachmentLst = doc.getElementsByTagName("attachments");
							for (int j = 0; j < attachmentLst.getLength(); j++) {
								attachment = attachmentLst.item(j);
								if (attachment.getNodeType() == Node.ELEMENT_NODE) {
									Element element2 = (Element) attachmentLst.item(i);

									String id = ProfilerUtil.nodeNotEmpty(element2.getElementsByTagName("id").item(0))
											? element2.getElementsByTagName("id").item(0).getTextContent()
											: "";
									String name = ProfilerUtil
											.nodeNotEmpty(element2.getElementsByTagName("name").item(0))
													? element2.getElementsByTagName("name").item(0).getTextContent()
													: "";
									String title = ProfilerUtil
											.nodeNotEmpty(element2.getElementsByTagName("title").item(0))
													? element2.getElementsByTagName("title").item(0).getTextContent()
													: "";
									String url = ProfilerUtil.getInstance().alfrescoContextURL()
											+ (ProfilerUtil.nodeNotEmpty(element2.getElementsByTagName("url").item(0))
													? element2.getElementsByTagName("url").item(0).getTextContent()
													: "");
									profile.setAttachments(new Attachment(id, name, title, url));
								}
							}

							profiles.add(profile);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				post.releaseConnection();
			}
		}
		log.debug("Search retrieved a total of :" + profiles.size() + " profiles from repository");
		return profiles;
	}

	@Override
	public List<Profile> advSearchProfile(Profile profile, String pageNum, String pageSize) {
		String organization = ActionContext.getContext().getSession().get(ProfilerConstants.PROPERTY_USER_ORGANIZATION)
				.toString();
		String luceneQuery = "PATH:\"/app:company_home/st:sites/cm:" + organization
				+ "/cm:documentLibrary/cm:ProfileR//*\" AND TYPE:\"pf:profile\" " + advQuery(profile);
		return searchInRepository(luceneQuery, pageNum, pageSize, false);
	}

	public String advQuery(Profile profile) {
		String query = "";
		if (profile != null) {
			if (profile.getId() != null && !profile.getId().isEmpty()) {
				query = query + "AND @pf\\:profileId:\"" + profile.id + "\"";
			}
			if (profile.getName() != null && !profile.getName().isEmpty()) {
				query = query + "AND @pf\\:profileName:\"" + profile.name + "\"";
			}
			if (profile.getEducation() != null && !profile.getEducation().isEmpty()) {
				query = query + "AND @pf\\:profileEducation:\"" + profile.education + "\"";
			}
			if (profile.getExperience() != null && !profile.getExperience().isEmpty()) {
				query = query + "AND @pf\\:profileExperience:\"" + profile.experience + "\"";
			}
			if (profile.getInterests() != null && !profile.getInterests().isEmpty()) {
				query = query + "AND @pf\\:profileInterests:\"" + profile.interests + "\"";
			}
			if (profile.getAddress() != null && !profile.getAddress().isEmpty()) {
				query = query + "AND @pf\\:profileAddress:\"" + profile.address + "\"";
			}
			if (profile.getSkills() != null && !profile.getSkills().isEmpty()) {
				query = query + "AND @pf\\:profileSkills:\"" + profile.skills + "\"";
			}
			if (profile.getLocation() != null && !profile.getLocation().isEmpty()) {
				query = query + "AND @pf\\:profileLocation:\"" + profile.location + "\"";
			}
			if (profile.getResumeSummary() != null && !profile.getResumeSummary().isEmpty()) {
				query = query + "AND @pf\\:profileSummary:\"" + profile.resumeSummary + "\"";
			}
		}
		log.debug("Framed Adv Search Query :" + query);
		return query;
	}

	@Override
	public ProfileSearchResults<Profile> obtainAllProfiles(ProfileSearchCriteria psc) {
		ProfileSearchType searchType = psc.getSearchType();
		ProfileSearchResults<Profile> searchResults = new ProfileSearchResults<Profile>();
		String sortOrder = psc.getSortOrder();
		List<Profile> results = null;
		// if(searchType == ProfileSearchType.BY_NAME)
		// {
		// //Use hibernate Criteria API to get and sort results based on USERNAME field
		// in sortOrder
		// results = userDAO.searchUsers(...);
		// }
		// else if(searchType == UserSearchType.BY_ID)
		// {
		// //Use hibernate Criteria API to get and sort results based on USER_ID field
		// in sortOrder
		// results = userDAO.searchUsers(...);
		// }
		String organization = ActionContext.getContext().getSession().get(ProfilerConstants.PROPERTY_USER_ORGANIZATION)
				.toString();
		String luceneQuery = "PATH:\"/app:company_home/st:sites/cm:" + organization
				+ "/cm:documentLibrary/cm:ProfileR//*\" AND TYPE:\"pf:profile\"";
		// log.debug("Query :" +luceneQuery + " with PageNum "+ pageNum + " and PageSize
		// :"+psc.getPageSize());
		// results = searchInRepository(luceneQuery, ""+psc.getPageNum(),
		// ""+psc.getPageSize(), false);

		List<Profile> profiles = new ArrayList<Profile>();
		String strURL = ProfilerUtil.getInstance().serviceURL() + "findProfile";
		InputStream in4 = null;
		Profile profile = new Profile();
		PostMethod post = new PostMethod(strURL);
		String ticket = authService.readTicket(ProfilerConstants.USERTYPE_USER);
		// log.debug("Ticket Prepared & ready to Search :"+searchString);
		if (ticket != null && !ticket.isEmpty()) {
			try {
				HttpClient client = new HttpClient();
				PostMethod method = new PostMethod(strURL);
				// Add any parameter if u want to send it with Post req.

				method.addParameter("lucenequery", luceneQuery);
				// method.addParameter("u", "admin");
				// method.addParameter("p", "admin");
				method.addParameter("alf_ticket", ticket);
				method.addParameter("pagenum", "" + psc.getPageNum());
				method.addParameter("pagesize", "" + psc.getPageSize());
				method.addParameter("organizationid", organization);
				int statusCode = client.executeMethod(method);
				log.debug(statusCode);
				if (statusCode != -1) {
					in4 = method.getResponseBodyAsStream();
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					Document doc = builder.parse(in4);
					// log.debug("Bfr normalize \n"
					// + doc.getTextContent());
					doc.getDocumentElement().normalize();
					// log.debug("Just normalized \n"
					// + doc.getTextContent());
					log.debug("Printing the textual content ");
					TransformerFactory factory7 = TransformerFactory.newInstance();
					Transformer xform = factory7.newTransformer();
					StringWriter sw = new StringWriter();
					xform.transform(new DOMSource(doc), new StreamResult(sw));

					String totalResult = doc.getElementsByTagName("totalSize").item(0).getTextContent();
					if (totalResult != null)
						searchResults.setTotalResults(Integer.parseInt(totalResult));
					// log.debug(doc.getTextContent());
					NodeList itemLst = doc.getElementsByTagName("profile");
					NodeList attachmentLst;
					Node attachment;
					for (int i = 0; i < itemLst.getLength(); i++) {
						Node node = itemLst.item(i);
						profile = new Profile();
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							Element element = (Element) itemLst.item(i);
							// log.debug(element.getElementsByTagName("profileId").item(0).getTextContent());
							profile.setId(element.getElementsByTagName("id").item(0).getTextContent());
							profile.setName(element.getElementsByTagName("profileName").item(0).getTextContent());
							profile.setExperience(
									element.getElementsByTagName("profileExperience").item(0).getTextContent());
							profile.setEmail(element.getElementsByTagName("profileEmail").item(0).getTextContent());
							profile.setContactNo(
									element.getElementsByTagName("profileContactNo").item(0).getTextContent());
							profile.setEducation(
									element.getElementsByTagName("profileEducation").item(0).getTextContent());
							profile.setSkills(Arrays.asList(element.getElementsByTagName("profileSkills").item(0)
									.getTextContent().split("\\s*,\\s*")));
							profile.setInterests(
									element.getElementsByTagName("profileInterests").item(0).getTextContent());
							profile.setLocation(
									element.getElementsByTagName("profileLocation").item(0).getTextContent());
							profile.setAddress(element.getElementsByTagName("profileAddress").item(0).getTextContent());
							profile.setResumeSummary(
									element.getElementsByTagName("profileSummary").item(0).getTextContent());
							attachmentLst = doc.getElementsByTagName("attachments");
							for (int j = 0; j < attachmentLst.getLength(); j++) {
								attachment = attachmentLst.item(j);
								if (attachment.getNodeType() == Node.ELEMENT_NODE) {
									Element element2 = (Element) attachmentLst.item(i);

									String id = ProfilerUtil.nodeNotEmpty(element2.getElementsByTagName("id").item(0))
											? element2.getElementsByTagName("id").item(0).getTextContent()
											: "";
									String name = ProfilerUtil
											.nodeNotEmpty(element2.getElementsByTagName("name").item(0))
													? element2.getElementsByTagName("name").item(0).getTextContent()
													: "";
									String title = ProfilerUtil
											.nodeNotEmpty(element2.getElementsByTagName("title").item(0))
													? element2.getElementsByTagName("title").item(0).getTextContent()
													: "";
									String url = ProfilerUtil.getInstance().alfrescoContextURL()
											+ (ProfilerUtil.nodeNotEmpty(element2.getElementsByTagName("url").item(0))
													? element2.getElementsByTagName("url").item(0).getTextContent()
													: "");
									profile.setAttachments(new Attachment(id, name, title, url));
								}
							}

							profiles.add(profile);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				post.releaseConnection();
			}
		}
		log.debug("Search retrieved a total of :" + profiles.size() + " profiles from repository");
		results = profiles;

		searchResults.setPageSize(psc.getPageSize());
		searchResults.setResults(results);
		return searchResults;
	}
}
