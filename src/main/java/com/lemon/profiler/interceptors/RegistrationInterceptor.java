package com.lemon.profiler.interceptors;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class RegistrationInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -601423197373908259L;
	private static final Logger log = Logger
			.getLogger(RegistrationInterceptor.class);
		
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("Redirect to register");
		return "registerForm";
	}
}