package com.lemon.profiler.test.mockit;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.lemon.profiler.service.SearchService;
import com.lemon.profiler.service.impl.SearchServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class SearchMockerTest {
	SearchService searchService = new SearchServiceImpl();
	@Before
    public void setUp() {
    }
 
    @After
    public void tearDown() {
    }
 
    @Test
    public void testDoBasicSearch() {
    	
//    	when(searchService.searchText("ECM", "", "")).thenReturn(false);
//        when(waterContainer.getPortion(Portion.SMALL)).thenReturn(true);
// 
//        assertFalse(coffeeMachine.makeCoffee(Portion.SMALL));        
        assertNotNull(searchService.searchText("ECM", "", ""));
        System.out.println("Search completed successfully");
    }
     
}
