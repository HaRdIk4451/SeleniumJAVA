package com.project.test.automationqa.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NividLivePage {
	
	WebDriver driver;
	public String secretFilePath = "./src/main/java/configs/secrets.properties";
	Properties prop;
	FileInputStream credentials;
	
	@FindBy(xpath="//input[@class='form-control string email optional']")
	WebElement email;
	
	@FindBy(xpath="//input[@class='form-control password optional password-input']")
	WebElement password;
	
	@FindBy(xpath="//input[@class='btn btn login-btn btn-primary']")
	public
	WebElement submitBtn;
	
 
	public NividLivePage(WebDriver driver) {
		
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
	
	
	public void setEmail() {
		
		email.sendKeys(getEmail());
	}
	
	public String getEmail() {
		
		loadSecretInformation();
		String email = prop.getProperty("nivid_email");
		return email;
	}
	
	public void setPassword() {
		
		password.sendKeys(getPassword());
	}
	
	public String getPassword() {
		
		loadSecretInformation();
		String password = prop.getProperty("nivid_password");
		return password;
	}
	
	
	public void submitButton() {
		
		submitBtn.click();
	
	}

}
