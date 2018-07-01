package com.lemon.profiler.unittests;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.struts2.StrutsTestCase;

import com.lemon.profiler.action.AuthenticationAction;
import com.lemon.profiler.action.ProfileAction;
import com.opensymphony.xwork2.ActionProxy;

public class ProfileCRUDTest extends StrutsTestCase {
	//HttpServletRequest request;
	public void testGetNthPrime() throws Exception {
		//ProfileCRUDTest pc = new ProfileCRUDTest();
		//pc.AuthenticationTest();
		//pc.profileCreationTest(request);
        
 }
	public void testAuthenticationTest() throws Exception {
		//request = Mockito.mock(HttpServletRequest.class);
        request.setParameter("userName", "admin");
        request.setParameter("password", "admin");
        
        ActionProxy proxy = getActionProxy("/login");
        AuthenticationAction authAction = (AuthenticationAction) proxy.getAction();
        proxy.execute();
 
        assertEquals(authAction.login(), "success");
        System.out.println("Succssfully logged in");        
        
//        request.setParameter("num", "5");
	}
	public void testprofileCreationTest() throws Exception{
		
		request.setParameter("userName", "admin");
        request.setParameter("password", "admin");
        
        ActionProxy proxy = getActionProxy("/login");
        Map<String, Object> sessionMap = new HashMap<String, Object>();
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        AuthenticationAction authAction = (AuthenticationAction) proxy.getAction();
 //       response.getS
//        System.out.println(sessionMap.get("alf_ticket"));
        //sessionMap.put("alf_ticket", authAction.readSessionTicket());
        proxy.execute();
        System.out.println("^^^^^^^^^^^^");
        System.out.println(sessionMap.get("alf_ticket"));
		//request.setParameter("profile.id", "ID-"+new Random().nextInt(50) + 1);		
		request.setParameter("profile.name", "Name-"+new Random().nextInt(50) + 1);
		request.setParameter("profile.experience", "Experience-"+new Random().nextInt(50) + 1);
		request.setParameter("profile.education", "Education-"+new Random().nextInt(50) + 1);	
		request.setParameter("profile.skills", "Skills-"+new Random().nextInt(50) + 1);
		request.setParameter("profile.interests", "Interests-"+new Random().nextInt(50) + 1);
		request.setParameter("profile.location", "Location"+new Random().nextInt(50) + 1);
		request.setParameter("profile.address", "Address-"+new Random().nextInt(50) + 1);
		request.setParameter("profile.resumeSummary", "Summary -"+new Random().nextInt(50) + 1);
		ActionProxy proxy2 = getActionProxy("/insertOrUpdateProfile");
        ProfileAction profileAction = (ProfileAction) proxy2.getAction();
        proxy2.getInvocation().getInvocationContext().setSession(sessionMap);
        proxy2.execute();
        assertEquals(profileAction.setUpForInsertOrUpdate(), "success");
	}
}
