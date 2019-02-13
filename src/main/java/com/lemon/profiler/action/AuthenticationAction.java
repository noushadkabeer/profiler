package com.lemon.profiler.action;

import org.apache.log4j.Logger;

import com.lemon.profiler.common.ProfilerUtil;
import com.lemon.profiler.constants.ProfilerConstants;
import com.lemon.profiler.model.User;
import com.lemon.profiler.service.AuthenticationService;
import com.lemon.profiler.service.UserService;
import com.lemon.profiler.service.impl.AuthenticationServiceImpl;
import com.lemon.profiler.service.impl.UserServiceImpl;
import com.lemon.profiler.util.ValidationHelper;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class AuthenticationAction extends ActionSupport implements Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	AuthenticationService authService = new AuthenticationServiceImpl();;
	private static final Logger log = Logger.getLogger(AuthenticationAction.class);
	UserService userService = new UserServiceImpl();
	User user = null;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {
		String result = "input";
		String userAvatar = "";
		if (!validationSuccessful()) {
			log.debug("Validation failed..");
			return "input";
		} else {
			// authService = new AuthenticationServiceImpl();
			log.debug(userName + password);
			result = authService.authenticate(userName, password);
			log.debug("Authenticated :" + result);
			if (result == null || result.length() != 47) {
				addActionMessage("Something went wrong. Please try again..");
				result = "failure";
			} else {
				ActionContext.getContext().getSession().put(ProfilerConstants.PROPERTY_ALF_TICKET, result);
				ActionContext.getContext().getSession().put("username", userName);
				ActionContext.getContext().getSession().put("password", password);
				ActionContext.getContext().getSession().put("logged-in", "true");
				user = userService.getUser(userName);
				log.debug("Avatar ::" + user.getAvatar());
				if (user != null) {
					if (user.getAvatar() != null && !user.getAvatar().isEmpty()) {
						userAvatar = user.getAvatar().split("api/node/workspace/SpacesStore/")[1].split("/")[0];
						ActionContext.getContext().getSession().put("userAvatar",
								ProfilerUtil.getInstance().cmisServiceURL() + "content?id=" + userAvatar
										+ "&alf_ticket=" + result);
					} else {
						userAvatar = "../Prfoler/images/defaultimg.jpg";
						ActionContext.getContext().getSession().put("userAvatar", userAvatar);
					}
					ActionContext.getContext().getSession().put(ProfilerConstants.PROPERTY_USER_ORGANIZATION,
							user.getUserOrganization());
					ActionContext.getContext().getSession().put(ProfilerConstants.USER_EMAIL_PASSWORD, user.getInstantmsg());
					ActionContext.getContext().getSession().put("user", user);
				}
				return "success";
			}
		}
		log.debug("Alf_ticket ----------->" + ActionContext.getContext().getSession().get("alf_ticket"));
		return result;
	}

	private boolean validationSuccessful() {
		if (userName == null || userName.isEmpty()) {
			addActionError("Username is required");
		}
		if (password == null || password.isEmpty()) {
			addActionError("Password is required");
			if (log.isDebugEnabled()) {
				log.debug("Authenticating username - : " + userName);
			}
			return false;
		}
		if(userName!=null && !userName.isEmpty() ) {
			if(!ValidationHelper.isValidEmailAddress(userName))
				addActionError("Username must be a valid email");
		}
		if (this.hasActionErrors()) {
			return false;
		} else {
			return true;
		}
	}

	public String logout() throws Exception {
		ActionContext.getContext().getSession().remove("logged-in");
		ActionContext.getContext().getSession().remove("logged-in");
		ActionContext.getContext().getSession().remove("username");
		ActionContext.getContext().getSession().remove("password");
		ActionContext.getContext().getSession().remove("alf_ticket");
		ActionContext.getContext().getSession().clear();
		return "success";
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub

	}

}
