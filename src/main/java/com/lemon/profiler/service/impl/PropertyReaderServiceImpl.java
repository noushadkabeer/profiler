package com.lemon.profiler.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.PropertyResourceBundle;

import org.apache.log4j.Logger;

import com.lemon.profiler.service.PropertyReaderService;

public class PropertyReaderServiceImpl implements  PropertyReaderService{

	private static final Logger log = Logger.getLogger(PropertyReaderServiceImpl.class);
	public static PropertyReaderServiceImpl propertyServiceImpl;
	private PropertyResourceBundle resouceBundle = null;
	private String content = null;

	public static PropertyReaderServiceImpl getInstance() {
		if (propertyServiceImpl == null) {
			propertyServiceImpl = new PropertyReaderServiceImpl();
		}
		return propertyServiceImpl;
	}

	public PropertyReaderServiceImpl() {
		loadAppConfig();
	}

	public PropertyResourceBundle getResouceBundle() {
		return this.resouceBundle;
	}

	public String getSkandiaConfigContent() {
		return this.content;
	}

	public String getKeyValue(String key) {
		String value = "";
		if (key != null) {
			value = this.resouceBundle.getString(key);
		}
		return value;
	}

	public void loadAppConfig() {
		InputStream input = null;
		try {
			String filename = "app-config.properties";
			input = PropertyReaderService.class.getClassLoader().getResourceAsStream(
					filename);
			if (input == null) {
				log.info("Sorry, unable to find " + filename);
				return;
			} 
	        this.resouceBundle = new PropertyResourceBundle(input); 

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public String getValue() {
		return "";
	}
	
	public static void main(String args[]){
		log.info("Ree");
		new PropertyReaderServiceImpl();
	}
}
