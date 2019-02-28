package com.lemon.profiler.action;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.lemon.profiler.model.Organization;
import com.lemon.profiler.model.User;
import com.lemon.profiler.service.ApplicationSetupService;
import com.lemon.profiler.service.UserService;
import com.lemon.profiler.service.impl.ApplicationSetupServiceImpl;
import com.lemon.profiler.service.impl.AuthenticationServiceImpl;
import com.lemon.profiler.service.impl.UserServiceImpl;
import com.lemon.profiler.util.ValidationHelper;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserProfileAction extends ActionSupport {

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
	
	public String confirmEmailPassword;
	

	public String getConfirmEmailPassword() {
		return confirmEmailPassword;
	}

	public void setConfirmEmailPassword(String confirmEmailPassword) {
		this.confirmEmailPassword = confirmEmailPassword;
	}

	public String loadUserProfileSettings() {
		System.out.println("--------"+hasActionErrors());
		HttpSession session = ServletActionContext.getRequest().getSession(false);
		String userId = session.getAttribute("username").toString();
		userService = new UserServiceImpl();
		user = userService.getUser(userId);
		log.info("Directing to profileSettings for user : " + user);
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
		// if (!validationSuccessful()) {
		// log.info("Validation failed.. and returning to input");
		// return "input";
		// } else {
		if (ActionContext.getContext().getSession().get("alf_ticket") == null) {			
			if (!validationSuccessful()) {
				log.info("Validation failed.. and returning to input");
				return "input";
			} else {
				log.info(ActionContext.getContext().getSession().get("alf_ticket") + "User registration initiated.. for user :" + user.getUserEmail());
				JSONObject jsonObj = userService.createUser(user);
				if (jsonObj.get("userName") == null) {
					addActionError("Something went wrong! Please try again later..");
					return "error";
				} else {
					String newUserName = jsonObj.get("userName").toString();
					String userAvatar = "";
					if (ActionContext.getContext().getSession().get("username") == null) {
						String newAlfTicket = new AuthenticationServiceImpl().authenticate(newUserName,
								user.getPassword());
						ActionContext.getContext().getSession().put("alf_ticket", newAlfTicket);
						ActionContext.getContext().getSession().put("username", newUserName);
						ActionContext.getContext().getSession().put("password", user.getPassword());
						ActionContext.getContext().getSession().put("logged-in", "true");
						user = userService.getUser(jsonObj.get("userName").toString());
						System.out.println("Details as fetched :"+user.getCompanyaddress2()+user.getCompanyfax());
						org = new Organization();
						org.setDescription(user.getCompanyaddress2()==null? "":user.getCompanyaddress2());
						org.setTitle(user.getCompanyfax()==null? "":user.getCompanyfax());
						if (user != null) {
							// userAvatar =
							// jsonObj.get("avatar").toString().split("api/node/workspace/SpacesStore/")[1].split("/")[0];
							log.info("New User Details " + user.getAvatar() + user.getFirstName() + user.getLastName()
									+ userAvatar);
							// TODO: fix the url
							ActionContext.getContext().getSession().put("userAvatar",
									"../Profiler/images/defaultimg.jpg");
							ActionContext.getContext().getSession().put("user", user);
						}
						log.info("Routing to agencyhome");
						return "agencydashboard";
					}
				}
			}
		} else {
			User tuser = null;
			if (!updateValidationSuccessful()) {
				log.info("Validation failed for profile Update.. and returning to input");
				return "updateValidationFailed";
			} else {
				applicationSetupService = new ApplicationSetupServiceImpl();
				// verify if the user is part of an existing organization
				if (user.getUserOrganization() != null) {
					org.setShortName(user.getUserOrganization());
					org.setType("PRIVATE");
					user.setCompanyfax(org.getTitle());
					user.setCompanyaddress2(org.getDescription());
					String actualShortName = org.getShortName();
					user.setUserOrganization(org.getTitle());
					user.setUserCompanyID(org.getShortName());
					if (applicationSetupService.getOrganizationalSite(org).getShortName().equals("noSite")) {
						tuser = userService.getUser(ActionContext.getContext().getSession().get("username").toString());
						org.setShortName(actualShortName);
						log.info("user >>" + tuser.getUserName());
						user.setUserOrganization(actualShortName);
						applicationSetupService.setupApplication(org, user);
					}
					log.info("Organization Confirmed :" + user.getUserCompanyID());
				}
				// return to the input
				log.info("users organization is " + user.getUserCompanyID());
				log.info("Users company shortdescription is " + user.getUserOrganization());

				userService.updateUser(user);
			}
		}
		// }
		return "default";
	}

	private boolean validationSuccessful() {
		//this.clearActionErrors();

		if (user == null || user.getFirstName() == null ||  user.getFirstName().isEmpty()) {
			log.info("Firstname null");
			addActionError("FirstName is required");
			if (log.isDebugEnabled()) {
				log.debug("Firstname  is required");
			}
		}
		if (user == null || user.getLastName() == null || user.getLastName().isEmpty()) {
			log.info("profile null");
			addActionError("LastName is required");
			if (log.isDebugEnabled()) {
				log.debug("LastName  is required");
			}
		}
		if (user == null || user.getUserEmail() == null || user.getUserEmail().isEmpty()) {
			addActionError("Email is required");
			if (log.isDebugEnabled()) {
				log.debug("Email  is required");
			}
		}		
		
		if(user.getUserEmail()!=null && !user.getUserEmail().isEmpty() ) {
			if(!ValidationHelper.isValidEmailAddress(user.getUserEmail()))
				addActionError("Email entered is not valid");
		}
		
		if (user == null || user.getPassword() == null || user.getPassword().isEmpty()) {
			addActionError("Password is required");
		}
		if (this.hasActionErrors()) {
			ActionContext.getContext().getSession().clear();
			return false;
		} else {
			if(user.getUserEmail().equals(userService.getUser(user.getUserEmail()).getUserEmail())) {
				addActionError("This email is already registered!");
				return false;
			}
			return true;
		}
	}

	private boolean updateValidationSuccessful() {		
		if (user == null || user.getFirstName() == null ||  user.getFirstName().isEmpty()) {
			addActionError("FirstName is required");
			if (log.isDebugEnabled()) {
				log.debug("Firstname  is required");
			}
		}
		if (user == null || user.getLastName() == null || user.getLastName().isEmpty()) {
			addActionError("LastName is required");
			if (log.isDebugEnabled()) {
				log.debug("LastName  is required");
			}
		}
		if (user == null || user.getUserEmail() == null || user.getUserEmail().isEmpty()) {
			addActionError("Email is required");
			if (log.isDebugEnabled()) {
				log.debug("Email  is required");
			}
		}
		if (user == null || user.getInstantmsg() == null || user.getInstantmsg().isEmpty() ||user.getInstantmsg().trim().length()<=0) {
			addActionError("Email password is required");
			if (log.isDebugEnabled()) {
				log.debug("Email  password is required");
			}
		}
		if (confirmEmailPassword == null || confirmEmailPassword.isEmpty() || confirmEmailPassword.trim().length()<=0) {
			addActionError("Confirm password is required");
			if (log.isDebugEnabled()) {
				log.debug("Confirm  is required");
			}
		}
		if(user != null && user.getInstantmsg() !=null && !user.getInstantmsg().isEmpty() && confirmEmailPassword !=null && !confirmEmailPassword.isEmpty() && !user.getInstantmsg().equals(confirmEmailPassword) )
				addActionError("Email Password & Confirm password doesn't match");

		if (user == null || user.getUserCompanyID() == null || user.getUserCompanyID().isEmpty()) {
			addActionError("User's Organization ID is required");
			if (log.isDebugEnabled())
				log.debug("User's Organization ID is required");
		}else user.setUserOrganization(user.getUserCompanyID());
		if (org == null || org.getDescription() == null || org.getDescription().isEmpty()) {
			addActionError("User's Organization Description is required");
			if (log.isDebugEnabled())
				log.debug("User's Organization Description is required");
		}
		if (org == null || org.getTitle() == null || org.getTitle().isEmpty()) {
			addActionError("User's Organization Title is required");
			if (log.isDebugEnabled())
				log.debug("User's Organization Title is required");
		}
		if (hasActionErrors()) {
			System.out.println("Failed validations : "+getActionErrors());
			return false;
		} else {
			if(user.getUserEmail()!=null && !user.getUserEmail().isEmpty() ) {
				if(!ValidationHelper.isValidEmailAddress(user.getUserEmail())) {
					addActionError("Email entered is not valid");
					return false;
				}
			}
			return true;
		}
	}

}
