package com.lemon.profiler.action;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.lemon.profiler.common.ProfilerUtil;
import com.lemon.profiler.model.Organization;
import com.lemon.profiler.model.User;
import com.lemon.profiler.service.ApplicationSetupService;
import com.lemon.profiler.service.UserService;
import com.lemon.profiler.service.impl.ApplicationSetupServiceImpl;
import com.lemon.profiler.service.impl.AuthenticationServiceImpl;
import com.lemon.profiler.service.impl.UserServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserProfileAction extends ActionSupport{

	/**
	 * This class contains logic to manage User profile of current logged in user
	 */
	private static final long serialVersionUID = -6802268559049704714L;
	private static final Logger log = Logger.getLogger(UserProfileAction.class);
	UserService userService = null;
	ApplicationSetupService applicationSetupService = null;
	public Organization org = null;
	
	public Organization getOrg() {
		return org;
	}
	public void setOrg(Organization org) {
		this.org = org;
	}

	public User user = null;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String loadUserProfileSettings() {
		System.out.println("--------");
		HttpSession session=ServletActionContext.getRequest().getSession(false);  
		String userId = session.getAttribute("username").toString();
		userService = new UserServiceImpl();
		user = userService.getUser(userId);
		log.info("Directing to profileSettings for user : "+user);
		return "success";
	}
	
	public String registerUser() {
		 ActionContext.getContext().getSession().remove("logged-in");
		 ActionContext.getContext().getSession().remove("logged-in");
		 ActionContext.getContext().getSession().remove("username");
		 ActionContext.getContext().getSession().remove("password");
		return "registerForm";
	}
	
	public String insertOrUpdateUser() {
		userService = new UserServiceImpl();
		log.info("insert or update user..");
		if (!validationSuccessful()) {
			log.info("Validation failed..");
			return "input";
		} else {
			if (ActionContext.getContext().getSession().get("alf_ticket")==null ) {
				log.info("User registration initiated.. for user :"+ user.getUserEmail()); 				
				JSONObject jsonObj = userService.createUser(user);
				if(jsonObj.get("userName") ==null) {
					addActionMessage("Something went wrong! Please try again later..");
					return "error";
				}
				else {
				String newUserName = jsonObj.get("userName").toString();
				String userAvatar = "";
				if(ActionContext.getContext().getSession().get("username") ==null){
					String newAlfTicket = new AuthenticationServiceImpl().authenticate(newUserName, user.getPassword());
					ActionContext.getContext().getSession().put("alf_ticket", newAlfTicket);
					ActionContext.getContext().getSession().put("username", newUserName);
					ActionContext.getContext().getSession().put("password", user.getPassword());
					ActionContext.getContext().getSession().put("logged-in", "true");
					user = userService.getUser(jsonObj.get("userName").toString());
					if(user!=null) {
//						userAvatar = jsonObj.get("avatar").toString().split("api/node/workspace/SpacesStore/")[1].split("/")[0];
						log.info("New User Details "+user.getAvatar()+user.getFirstName()+ user.getLastName() + userAvatar);
						//TODO: fix the url
						ActionContext.getContext().getSession().put("userAvatar", "../Prfoler/images/defaultimg.jpg");
						ActionContext.getContext().getSession().put("user", user);
					}
					log.info("Routing to agencyhome");
				 return "agencydashboard";	
				}
				}
			} else {
				User tuser = null;
				applicationSetupService = new ApplicationSetupServiceImpl();
				//verify if the user is part of an existing organization
				if(user.getUserOrganization()!=null) {
					org.setShortName(user.getUserOrganization());
					org.setType("PRIVATE");
					String actualShortName = org.getShortName();
					user.setUserOrganization(org.getTitle());
					user.setUserCompanyID(org.getShortName());
					if(applicationSetupService.getOrganizationalSite(org).getShortName().equals("noSite")) {						
						tuser = userService.getUser(ActionContext.getContext().getSession().get("username").toString());
						org.setShortName(actualShortName);
						log.info("user >>"+tuser.getUserName());
						user.setUserOrganization(actualShortName);
						applicationSetupService.setupApplication(org, user);
						}
					log.info("Organization Confirmed :"+user.getUserCompanyID());
				}
					//return to the input
					log.info("users organization is "+user.getUserCompanyID());
					log.info("Users company shortdescription is "+user.getUserOrganization());
				
				
				userService.updateUser(user);
			}
		}
		return "default";
	}
	
	private boolean validationSuccessful() { 
		
		if (user==null || user.getUserEmail() == null) {
			log.info("profile null");
			if (log.isDebugEnabled()) {
				log.debug("Email  " + " is required");
			}
		}
//		if (user.getPassword() == null || user.getPassword().trim().length() < 1) {
//			 addActionMessage("Password is required");
//			return false;
//		}
		
		if(this.hasActionMessages()){
			 return false;
		} else {
			return true;
		}
	}

}
