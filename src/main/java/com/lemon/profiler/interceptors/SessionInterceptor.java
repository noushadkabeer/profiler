package com.lemon.profiler.interceptors;

import java.util.Map;

import org.apache.log4j.Logger;

import com.lemon.profiler.constants.ProfilerConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class SessionInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -601423197373908259L;
	private static final Logger log = Logger
			.getLogger(SessionInterceptor.class);
		
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = invocation.getInvocationContext()
				.getSession();
		String loginId = (String) session.get(ProfilerConstants.PROPERTY_ALF_TICKET);
		if (loginId == null) {
			log.info("Invalid session! Redirecting to Login");
			return Action.LOGIN;
		} else {
			return invocation.invoke();			
		}
	}
}