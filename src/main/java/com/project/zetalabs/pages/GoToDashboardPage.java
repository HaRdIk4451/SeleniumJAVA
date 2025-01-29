package com.project.zetalabs.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoToDashboardPage{
	
	WebDriver driver;
	private String secretFilePath = "./src/main/java/config/secrets.properties";
	Properties prop;
	FileInputStream credentials;
	
	@FindBy(xpath="//li[contains(.,'Admins')]")
	WebElement ClickAdminSidebar;
	
 
	public GoToDashboardPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	
	public void loadSecretInformation() {
		
		prop = new Properties();
		try {
			credentials = new FileInputStream(secretFilePath);
			prop.load(credentials);
		} catch (FileNotFoundException e) {
						e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	public void ClickAdminSidebar() {
		
		ClickAdminSidebar.click();
	
	}
}
