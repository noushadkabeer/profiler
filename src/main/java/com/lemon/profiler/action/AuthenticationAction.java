package com.lemon.profiler.action;

import org.apache.log4j.Logger;

import com.lemon.profiler.service.AuthenticationService;
import com.lemon.profiler.service.impl.AuthenticationServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class AuthenticationAction extends ActionSupport implements Preparable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;	
	AuthenticationService authService = new AuthenticationServiceImpl();;
	private static final Logger log = Logger.getLogger(AuthenticationAction.class);
	
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
	
	public String login(){ 
		String result = "input";
		if (!validationSuccessful()) {
			log.info("Validation failed..");
			return "input";
		} else {
	//	authService = new AuthenticationServiceImpl();
		log.info(userName+password);
		result = authService.authenticate(userName, password);
		log.info("Authenticated :"+result);
		if(result==null || result.length()!=47){
			addActionMessage("Something went wrong. Please try again..");
			result = "failure";
		}else{
			ActionContext.getContext().getSession().put("alf_ticket", result);
			ActionContext.getContext().getSession().put("username", userName);
			ActionContext.getContext().getSession().put("password", password);
			ActionContext.getContext().getSession().put("logged-in", "true");
			return "success";
		}
		}
		log.info("----------->"+ActionContext.getContext().getSession().get("alf_ticket"));
		return result;
	}
	
	 private boolean validationSuccessful() {
		 if (userName == null || userName.isEmpty()) {
				log.info("Username is required");
				
			}
			if (password == null || password.isEmpty()) {
				 addActionMessage("Password is required");
				 if (log.isDebugEnabled()) {
					log.debug("Authenticating username - : " + userName);
				}
				return false;
			}
			
			if(this.hasActionMessages()){
				 return false;
			} else {
				return true;
			}
	}
	 
	public String logout() throws Exception
	  {
	    ActionContext.getContext().getSession().remove("logged-in");
	    ActionContext.getContext().getSession().remove("logged-in");
	    ActionContext.getContext().getSession().remove("username");
	    ActionContext.getContext().getSession().remove("password");
	    return "success";
	  }
	
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
//	//Action mainly used for Test authentication 
//	public String readSessionTicket() {	
//		log.info("----------->"+ActionContext.getContext().getSession().get("alf_ticket"));
//		log.info("Returing session ticket from getSessionTicket");		
//		//authService = new AuthenticationServiceImpl();
//		return authService.getSessionTicket();		
//	}
}
