package com.lemon.profiler.service.impl;

import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

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
import com.lemon.profiler.model.Job;
import com.lemon.profiler.model.Profile;
import com.lemon.profiler.service.AuthenticationService;
import com.lemon.profiler.service.JobService;
import com.lemon.profiler.service.PropertyReaderService;
import com.opensymphony.xwork2.ActionContext;


public class JobServiceImpl implements JobService{	
	
	private static final Logger log = Logger.getLogger(JobServiceImpl.class);
	PropertyReaderService propS = new PropertyReaderServiceImpl();
	AuthenticationService authService = new AuthenticationServiceImpl();
	StringWriter sw = new StringWriter();
	private static ArrayList<Job> jobsList;
	static {
		jobsList = new ArrayList<Job>();
		jobsList.add(new Job("job001", "5.5years","Driller","AL Fathalu","Abu dhabi","Permenant","20000","REF0023","ere@dn.com","Job Description","20"));
		jobsList.add(new Job("job002", "6.5years","Driller","AL Fathalu","Abu dhabi","Permenant","20000","REF0023","ere@dn.com","Job Description","20"));
    }

    Log logger = LogFactory.getLog(this.getClass());
	@Override
	public List<Job> obtainAllJobs() {		
		String organization = ActionContext.getContext().getSession().get(ProfilerConstants.PROPERTY_USER_ORGANIZATION).toString();
		String luceneQuery = "PATH:\"/app:company_home/st:sites/cm:"+organization+"/cm:documentLibrary/cm:ProfileR/cm:Jobs//*\" AND TYPE:\"pf:job\"";
		return searchInRepository(luceneQuery, "", "", false);	
	}
	

	@Override
	public Job pullJobByName(String name) {
		String organization = ActionContext.getContext().getSession().get(ProfilerConstants.PROPERTY_USER_ORGANIZATION).toString();
		String luceneQuery = "PATH:\"/app:company_home/st:sites/cm:"+organization+"/cm:documentLibrary/cm:ProfileR/cm:Jobs//*\" AND TYPE:\"pf:job\" AND @cm:name:"+name+"\"";
		List<Job> jobs = searchInRepository(luceneQuery, "undefined", "undefined", false);
		Job job = jobs.get(0);
		return job;
	}
	
	@Override
	public Job pullJob(String jobId) {
		log.debug("Fetching the Job :"+jobId);
		Job job = null;
		List<Job> jobs = new ArrayList<Job>(); 
		String strURL = ProfilerUtil.getInstance().serviceURL()+ "findJobById";
		InputStream in4 = null;
		PostMethod post = new PostMethod(strURL);
		String organization = ActionContext.getContext().getSession().get(ProfilerConstants.PROPERTY_USER_ORGANIZATION).toString();
		String ticket = authService.readTicket(ProfilerConstants.USERTYPE_USER);
		log.debug("Ticket Prepared & ready to Search :"+jobId);
		if (ticket != null && !ticket.isEmpty()) {
			try {
				HttpClient client = new HttpClient();
				PostMethod method = new PostMethod(strURL);
				// Add any parameter if u want to send it with Post req. 
				method.addParameter("stringToSearch", jobId); 
				// method.addParameter("u", "admin");
				// method.addParameter("p", "admin");
				method.addParameter("organizationid" , organization);
				method.addParameter("alf_ticket", ticket); 
				int statusCode = client.executeMethod(method);
				log.debug(statusCode);
				if (statusCode != -1) {
					in4 = method.getResponseBodyAsStream();
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					Document doc = builder.parse(in4);
					log.debug("Bfr normalize \n"
							+ doc.getTextContent());
					doc.getDocumentElement().normalize();
					log.debug("Just normalized \n"
							+ doc.getTextContent());
					TransformerFactory factory7 = TransformerFactory
							.newInstance();
					Transformer xform = factory7.newTransformer();
					xform.transform(new DOMSource(doc), new StreamResult(sw));
					log.debug(doc.getTextContent());
					NodeList itemLst = doc.getElementsByTagName("job"); 
					for (int i = 0; i < itemLst.getLength(); i++) {
						Node node = itemLst.item(i);
						job = new Job();
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							Element element = (Element) itemLst.item(i);
							log.debug(element
									.getElementsByTagName("jobId").item(0)
									.getTextContent());
							job.setJobId(element
									.getElementsByTagName("id").item(0)
									.getTextContent());
							job.setJobExperience(element.getElementsByTagName("jobExperience").item(0).getTextContent());								
							job.setJobTitle(element
									.getElementsByTagName("jobTitle")
									.item(0).getTextContent());
							job.setCompany(element
									.getElementsByTagName("jobCompany")
									.item(0).getTextContent());
							job.setAboutJob(element
									.getElementsByTagName("jobAboutJob")
									.item(0).getTextContent());
							job.setLocation(element
									.getElementsByTagName("jobLocation")
									.item(0).getTextContent());
							job.setJobType(element
									.getElementsByTagName("jobType").item(0)
									.getTextContent());
							job.setSalary(element
									.getElementsByTagName("jobSalary")
									.item(0).getTextContent());
							job.setReferenceCode(element.getElementsByTagName("jobReferenceCode").item(0).getTextContent());
							job.setNoOfVaccancies(element
									.getElementsByTagName("jobNoOfVaccancies")
									.item(0).getTextContent());
							job.setContactInfo(element
									.getElementsByTagName("jobContactInfo")
									.item(0).getTextContent());
							jobs.add(job);
						}
					} 
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				post.releaseConnection();
			}
		}
		log.debug("Total Profiles on Search by ID Returned :"+jobs.size());
			
		job = jobs.get(0);
		return job;
	}
	@Override
	public void update(Job job) {
		log.debug("will update this..");        
        //From here
        String ticket = authService.readTicket(ProfilerConstants.USERTYPE_USER);
        String strURL =  ProfilerUtil.getInstance().serviceURL()+ "updateJob?alf_ticket="+ticket;  
		PostMethod post = new PostMethod(strURL); 		
		String organization = ActionContext.getContext().getSession().get(ProfilerConstants.PROPERTY_USER_ORGANIZATION).toString();
		post.addParameter("alf_ticket", ticket);
		try { 
			InputStream in2 = null;
			String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?><storeid>"+job.getJobId()+"</storeid>"; 
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
			Part[] parts = { new StringPart("jobId", job.getJobId()),
					new StringPart("organizationid" , organization),
					new StringPart("jobExperience", job.getJobExperience()),
					new StringPart("jobTitle", job.getJobTitle()),
					new StringPart("jobCompany", job.getCompany()),
					new StringPart("jobLocation", job.getLocation()),
					new StringPart("jobType", job.getJobType()),
					new StringPart("jobSalary", job.getSalary()),
					new StringPart("jobReferenceCode", job.getReferenceCode()),
					new StringPart("jobContactInfo", job.getContactInfo()),
					new StringPart("jobAboutJob", job.getAboutJob()),
					new StringPart("jobNoOfVaccancies", job.getNoOfVaccancies()),
			};

			log.debug(parts.length + parts.toString());
			HttpClient client = new HttpClient();
			// PostMethod method = new PostMethod(strURL);
//			Credentials loginCreds = new UsernamePasswordCredentials("admin","admin");
//			client.getState().setCredentials(AuthScope.ANY, loginCreds);
			
			
			post.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(3, false));
			post.setRequestEntity(new MultipartRequestEntity(parts, post
					.getParams()));
			log.debug(post);
			int statusCode = client.executeMethod(post);
			log.debug(statusCode);
			if (statusCode != -1) {
				in2 = post.getResponseBodyAsStream();

				DocumentBuilderFactory factory2 = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder builder2 = factory2.newDocumentBuilder();
				Document doc3 = builder2.parse(in2);
				log.debug(in2 + "  -------"); 
				TransformerFactory factory7 = TransformerFactory.newInstance();
				Transformer xform = factory7.newTransformer(); 
				xform.transform(new DOMSource(doc3), new StreamResult(
						sw));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			post.releaseConnection();
		}        
        
        
        log.debug("Record Updated Successfully..");
		
	}
	@Override
	public void insert(Job job) {
		log.debug("Already existing :"+jobsList.size());		 
//		jobsList.add(new Job((0+ new Random().nextInt(Integer.MAX_VALUE)+1)+"", job.getJobExperience(),job.getJobTitle(),
//				job.getCompany(),job.getLocation(),job.getJobType(), job.getSalary(),job.getReferenceCode(),
//				job.getContactInfo(),job.getAboutJob(),job.getNoOfVaccancies()));
		
		String ticket = authService.readTicket(ProfilerConstants.USERTYPE_USER);
        String strURL =  ProfilerUtil.getInstance().serviceURL()+ "createJob?alf_ticket="+ticket;  
		PostMethod post = new PostMethod(strURL); 		
		post.addParameter("alf_ticket", ticket);
		String organization = ActionContext.getContext().getSession().get(ProfilerConstants.PROPERTY_USER_ORGANIZATION).toString();
		try { 
			InputStream in2 = null;
			String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?><storeid>"+job.getJobId()+"</storeid>"; 
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
			Part[] parts = { new StringPart("jobId", job.getJobId()),
					new StringPart("organizationid" , organization),
					new StringPart("jobExperience", job.getJobExperience()),
					new StringPart("jobTitle", job.getJobTitle()),
					new StringPart("jobCompany", job.getCompany()),
					new StringPart("jobLocation", job.getLocation()),
					new StringPart("jobType", job.getJobType()),
					new StringPart("jobSalary", job.getSalary()),
					new StringPart("jobReferenceCode", job.getReferenceCode()),
					new StringPart("jobContactInfo", job.getContactInfo()),
					new StringPart("jobAboutJob", job.getAboutJob()),
					new StringPart("jobNoOfVaccancies", job.getNoOfVaccancies()),	
			};

			log.debug(parts.length + parts.toString());
			HttpClient client = new HttpClient();
			// PostMethod method = new PostMethod(strURL);
//			Credentials loginCreds = new UsernamePasswordCredentials("admin","admin");
//			client.getState().setCredentials(AuthScope.ANY, loginCreds);
			
			
			post.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(3, false));
			post.setRequestEntity(new MultipartRequestEntity(parts, post
					.getParams()));
			log.debug(post);
			int statusCode = client.executeMethod(post);
			log.debug(statusCode);
			if (statusCode != -1) {
				in2 = post.getResponseBodyAsStream();

				DocumentBuilderFactory factory2 = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder builder2 = factory2.newDocumentBuilder();
				Document doc3 = builder2.parse(in2);
				log.debug(in2 + "  -------"); 
				TransformerFactory factory7 = TransformerFactory.newInstance();
				Transformer xform = factory7.newTransformer(); 
				xform.transform(new DOMSource(doc3), new StreamResult(
						sw));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			post.releaseConnection();
		}        
        
		
		log.debug("Added one and now size is :"+jobsList.size());		
	}
	@Override
	public String delete(String id) {
		String result = "";
		Job job = null;
		log.debug("Deleting the Job :"+id);         
		//From here
        String ticket = authService.readTicket(ProfilerConstants.USERTYPE_USER);
        String strURL =  ProfilerUtil.getInstance().serviceURL()+ "deleteJob?alf_ticket="+ticket;  
		PostMethod post = new PostMethod(strURL); 		
		post.addParameter("alf_ticket", ticket);
		try { 
			InputStream in2 = null;
			String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?><storeid>"+id+"</storeid>"; 
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
			Part[] parts = { new StringPart("id", id),
					new StringPart("id", id)				
			}; 
			HttpClient client = new HttpClient(); 			
			post.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(3, false));
			post.setRequestEntity(new MultipartRequestEntity(parts, post
					.getParams()));
			log.debug(post);
			int statusCode = client.executeMethod(post);
			log.debug("Delete Status :"+statusCode);
			if (statusCode != -1) {
				in2 = post.getResponseBodyAsStream();
				DocumentBuilderFactory factory2 = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder builder2 = factory2.newDocumentBuilder();
				Document doc3 = builder2.parse(in2);
				log.debug(in2 + "  -------"); 
				TransformerFactory factory7 = TransformerFactory.newInstance();
				Transformer xform = factory7.newTransformer(); 
				xform.transform(new DOMSource(doc3), new StreamResult(
						sw));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			post.releaseConnection();
		}     
		

		return result;
	}
	
	@Override
	public List<Job> searchJob(String searchString, String pageNum, String pageSize) {
		return searchInRepository(searchString, pageNum, pageSize, true);
	}
	
	public List<Job> searchInRepository(String searchString, String pageNum, String pageSize, boolean plainSearch){
		List<Job> jobs = new ArrayList<Job>(); 
		String strURL = ProfilerUtil.getInstance().serviceURL()+ "findJob";
		InputStream in4 = null;
		Job job = new Job();  
		PostMethod post = new PostMethod(strURL);
		String ticket = authService.readTicket(ProfilerConstants.USERTYPE_USER);
		String organization = ActionContext.getContext().getSession().get(ProfilerConstants.PROPERTY_USER_ORGANIZATION).toString();
		log.debug("Ticket Prepared & ready to Search :"+searchString);
		if (ticket != null && !ticket.isEmpty()) {
			try {
				HttpClient client = new HttpClient();
				PostMethod method = new PostMethod(strURL);
				// Add any parameter if u want to send it with Post req.
				if(plainSearch)
					method.addParameter("stringToSearch", searchString);
				else
					method.addParameter("lucenequery", searchString);
				// method.addParameter("u", "admin");
				// method.addParameter("p", "admin");
				method.addParameter("organizationid" , organization);
				method.addParameter("alf_ticket", ticket);
				method.addParameter("pagenum", pageNum);
				method.addParameter("pagesize", pageSize);
				int statusCode = client.executeMethod(method);
				log.debug(statusCode);
				if (statusCode != -1) {
					in4 = method.getResponseBodyAsStream();
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					Document doc = builder.parse(in4);
					log.debug("Bfr normalize \n"
							+ doc.getTextContent());
					doc.getDocumentElement().normalize();
					log.debug("Just normalized \n"
							+ doc.getTextContent());
					TransformerFactory factory7 = TransformerFactory
							.newInstance();
					Transformer xform = factory7.newTransformer();
					xform.transform(new DOMSource(doc), new StreamResult(sw));
					log.debug(doc.getTextContent());
					NodeList itemLst = doc.getElementsByTagName("job"); 
					for (int i = 0; i < itemLst.getLength(); i++) {
						Node node = itemLst.item(i);
						job = new Job();
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							Element element = (Element) itemLst.item(i);
							log.debug("ID "+element
									.getElementsByTagName("id").item(0)
									.getTextContent());
							job.setJobId(element
									.getElementsByTagName("id").item(0)
									.getTextContent());
							job.setJobExperience(element.getElementsByTagName("jobExperience").item(0).getTextContent());								
							job.setJobTitle(element
									.getElementsByTagName("jobTitle")
									.item(0).getTextContent());
							job.setCompany(element
									.getElementsByTagName("jobCompany")
									.item(0).getTextContent());
							job.setAboutJob(element
									.getElementsByTagName("jobAboutJob")
									.item(0).getTextContent());
							job.setLocation(element
									.getElementsByTagName("jobLocation")
									.item(0).getTextContent());
							job.setJobType(element
									.getElementsByTagName("jobType").item(0)
									.getTextContent());
							job.setSalary(element
									.getElementsByTagName("jobSalary")
									.item(0).getTextContent());
							job.setReferenceCode(element.getElementsByTagName("jobReferenceCode").item(0).getTextContent());
							job.setNoOfVaccancies(element
									.getElementsByTagName("jobNoOfVaccancies")
									.item(0).getTextContent());
							job.setContactInfo(element
									.getElementsByTagName("jobContactInfo")
									.item(0).getTextContent());
							log.debug("job added :" + job.jobId);
							jobs.add(job);
						}
					} 
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
			} finally {
				post.releaseConnection();
			}
		}
		log.debug("Before :"+jobs.size());
		return jobs;
	}
	

}
