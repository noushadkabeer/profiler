package com.lemon.profiler.interceptors;

import java.util.Map;

import org.apache.log4j.Logger;

import com.lemon.profiler.constants.ProfilerConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ProfileUpdateInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -601423197373908259L;
	private static final Logger log = Logger.getLogger(ProfileUpdateInterceptor.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		log.debug("ProfileUpdateInterceptor triggered");
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		String loginId = (String) session.get(ProfilerConstants.PROPERTY_ALF_TICKET);
		String organization = (String) session.get(ProfilerConstants.PROPERTY_USER_ORGANIZATION);
		if (loginId == null) {
			log.debug("Invalid session! Redirecting to Login " + invocation.getAction().toString());
			return Action.LOGIN;
		} else if (organization == null || organization.isEmpty()) {
			log.debug("User :" + loginId + " found without organization , redirecting to update the profile");
			addActionError(invocation, "You must update your organization details before proceeding!");
			return "updateProfile";
		} else {
			log.debug("ProfileUpdateInterceptor clearing the path.. as is identified as : " + organization + " for :: "
					+ loginId);
			return invocation.invoke();
		}
	}

	private void addActionError(ActionInvocation invocation, String message) {
		Object action = invocation.getAction();
		if (action instanceof ValidationAware) {
			((ValidationAware) action).addActionError(message);
		}
	}
}