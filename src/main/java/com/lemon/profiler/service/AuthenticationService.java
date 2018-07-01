package com.lemon.profiler.service;

public interface AuthenticationService {	
	
	public AuthenticationService getInstance();
	//Authenticate from remote server
	public String authenticate(String username, String password);
	public String validateTicket(String ticket);
	public String readTicket(String userType);	
//	public String getSessionTicket();
}
