package com.lemon.profiler.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.lemon.profiler.constants.ProfilerConstants;
import com.lemon.profiler.model.Task;
import com.lemon.profiler.service.AuthenticationService;
import com.lemon.profiler.service.PropertyReaderService;
import com.lemon.profiler.service.TaskService;


public class TaskServiceImpl implements TaskService{
	PropertyReaderService propS = new PropertyReaderServiceImpl();
	AuthenticationService authService = new AuthenticationServiceImpl();
	private static final Logger log = Logger.getLogger(TaskServiceImpl.class);
	
	private static ArrayList<Task> taskList;

	static {
		taskList = new ArrayList<Task>();
		taskList.add(new Task("task001", "Approve Task ","Verify the resume and approve ","Work In Progress", "A", "B","C","D", "E", "F", "G", "H"));
		taskList.add(new Task("task002", "Approve Task ","Verify the resume and approve ","Work In Progress","A", "B","C","D", "E", "F", "G", "H"));		
		    }

    Log logger = LogFactory.getLog(this.getClass());

	
	public ArrayList<Task> getAllTasks() { 
		return taskList;
	}	
	
	@Override
	public List<Task> obtainTaskList() {
		String ticket = authService.readTicket(ProfilerConstants.USERTYPE_USER);
		List<Task> workList = new ArrayList<Task>();
		Task workNode;
		String strURL = propS.getKeyValue("contentServerURL")
				+ propS.getKeyValue("serviceURL")+"workList";
		InputStream in3 = null;
		PostMethod post = new PostMethod(strURL);
		if (ticket != null && ticket.isEmpty()) {

			try {
				HttpClient client = new HttpClient();
				PostMethod method = new PostMethod(strURL);
				// Add any parameter if u want to send it with Post req.
				method.addParameter("alf_ticket", ticket);

				int statusCode = client.executeMethod(method);
				log.info(statusCode);
				if (statusCode != -1) {
					in3 = method.getResponseBodyAsStream();

					DocumentBuilderFactory factory = DocumentBuilderFactory
							.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					Document doc = builder.parse(in3);
					doc.getDocumentElement().normalize();

					TransformerFactory factory7 = TransformerFactory
							.newInstance();
					Transformer xform = factory7.newTransformer();

					xform.transform(new DOMSource(doc), new StreamResult(
							System.out));

					log.info("----------------------------");
					NodeList itemLst = doc.getElementsByTagName("tasks");

					try {
						for (int i = 0; i < itemLst.getLength(); i++) {
							Node item = itemLst.item(i);

							if (item.getNodeType() == Node.ELEMENT_NODE) {
								Element ielem = (Element) item;
								NodeList guidanceText = ielem
										.getElementsByTagName("taskItem");

								for (int gti = 0; gti < guidanceText
										.getLength(); gti++) {
									workNode = new Task();
									Node taskItem = guidanceText.item(gti);
									if (taskItem.getNodeType() == Node.ELEMENT_NODE) {
										Element taskelem = (Element) taskItem;
										workNode.setDescription(taskelem
												.getElementsByTagName(
														"description").item(0)
												.getTextContent());
										workNode.setType(taskelem
												.getElementsByTagName("type")
												.item(0).getTextContent());
										workNode.setTaskid(taskelem
												.getElementsByTagName("taskid")
												.item(0).getTextContent());
										workNode.setStartdate(taskelem
												.getElementsByTagName(
														"startdate").item(0)
												.getTextContent());
										workNode.setPriority(taskelem
												.getElementsByTagName(
														"priority").item(0)
												.getTextContent());
										workNode.setStatus(taskelem
												.getElementsByTagName("status")
												.item(0).getTextContent());
										workNode.setPercentcomplete(taskelem
												.getElementsByTagName(
														"percentcomplete")
												.item(0).getTextContent());
										workNode.setDuetoday(taskelem
												.getElementsByTagName(
														"duetoday").item(0)
												.getTextContent());
										workNode.setOverdue(taskelem
												.getElementsByTagName("overdue")
												.item(0).getTextContent());
										workNode.setDuedate(taskelem
												.getElementsByTagName("duedate")
												.item(0).getTextContent());
									}
									workList.add(workNode);
								}
							}

						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				log.info("total worklist size :" + workList.size());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				post.releaseConnection();
			}
		}
		return workList;
	}
}
