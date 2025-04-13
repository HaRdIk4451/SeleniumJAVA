package com.project.test.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Utilities {
	
	public static Properties prop;
	public static FileInputStream input;
	public static String globalFilePath = "./src/main/java/config/config.properties";
	public static final int IMPLICIT_WAIT_TIME = 10;
	
	
	public static void initGlobalConfiguration() {
		
		prop = new Properties();
		try {
			input = new FileInputStream(globalFilePath);
			prop.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static String getBrowserName() {
		
		String browserName = prop.getProperty("browser");
		return browserName;
	}
	
	
	public static String getBaseUrl() {
		
		String baseUrl = prop.getProperty("baseurl");
		return baseUrl;
	}

}
