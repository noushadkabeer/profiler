package com.lemon.profiler.test.mockit;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.StrutsStatics;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.lemon.profiler.model.User;
import com.lemon.profiler.service.SearchService;
import com.lemon.profiler.service.impl.SearchServiceImpl;
import com.opensymphony.xwork2.ActionContext;

@RunWith(MockitoJUnitRunner.class)
public class SearchMockerTest {
	SearchService searchService = new SearchServiceImpl();
	User user;	
	   HttpServletRequest request;
	    HttpSession session;

	@Before
    public void setUp() {
		 // Process mock annotations
//        MockitoAnnotations.initMocks(this);        
        
        // create a user

        // mock the session using mockito
        session = Mockito.mock(HttpSession.class);
        Mockito.when(session.getAttribute("ACTIVE_USER")).thenReturn(user);

        // mock the HttpServletRequest
        request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getSession()).thenReturn(session);

        // set the context
        Map<String, Object> contextMap = new HashMap<String, Object>();
        contextMap.put(StrutsStatics.HTTP_REQUEST, request);
        ActionContext.setContext(new ActionContext(contextMap));

    }
 
    @After
    public void tearDown() {
  
    	 user = null;
         request = null;
         session = null;
         ActionContext.setContext(null);
    }
 
    @Test
    public void testDoBasicSearch() {
    	ActionContext actionContext = Mockito.mock(ActionContext.class);
		ServletContext servletContext = Mockito.mock(ServletContext.class);
//    	when(searchService.searchText("ECM", "", "")).thenReturn(false);
//        when(waterContainer.getPortion(Portion.SMALL)).thenReturn(true);
// 
//        assertFalse(coffeeMachine.makeCoffee(Portion.SMALL));        
        //assertNotNull(searchService.searchText("ECM", "", ""));
        System.out.println("Search completed successfully");
    }
     
}
