package com.lemon.profiler.service;
 
import java.util.PropertyResourceBundle;

public interface PropertyReaderService {

	public PropertyResourceBundle getResouceBundle(); 
	public String getSkandiaConfigContent();
	public String getKeyValue(String key);
	public void loadAppConfig() ;
	
}
