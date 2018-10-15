package com.lemon.profiler.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.lemon.profiler.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class ReportAction extends ActionSupport implements Preparable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ReportAction.class);

	private String jsonString;
	
	public String getJsonString() {
		return jsonString;
	}


	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}


	public String loadReportDashboard() {
		try{
			
		}
		catch(Exception e){
			return "failure";
		}
		return "success";
	}	
	
	
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public String returnDefaultReport() {
		Gson gson = new Gson();
		jsonString = gson.toJson(getStudentData());

		return "success";
	}
	
	private List<User> getStudentData() {

		  List<User> listOfStudent = new ArrayList<User>();
		  User s1 = new User();
		  s1.setUserName("Noushad");
		  s1.setUserId("75");
		  s1.setCountry("26");
//		  s1.setGeographyMark(91);
//		  s1.setHistoryMark(55);
//		  s1.setLitratureMark(36);
		  listOfStudent.add(s1);

		  return listOfStudent;
		 }
}
