package com.project.zetalabs.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	private String secretFilePath = "./src/main/java/configs/secrets.properties";
	Properties prop;
	FileInputStream credentials;
	
	@FindBy(xpath="//input[@class='form-control string email optional']")
	WebElement email;
	
	@FindBy(xpath="//input[@class='form-control password optional password-input']")
	WebElement password;
	
	@FindBy(xpath="//input[@name='commit']")
	WebElement submitBtn;
	
 
	public LoginPage(WebDriver driver) {
		
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

	
	public String correctGetEmail() {
		
		loadSecretInformation();
		String email = prop.getProperty("correct_nivid_email");
		return email;
	}
	
	public void correctSetEmail() {
		
		email.sendKeys(correctGetEmail());
	}
	
	public String incorrectGetEmail() {
		
		loadSecretInformation();
		String email = prop.getProperty("incorrect_nivid_email");
		return email;
	}
	
	public void incorrectSetEmail() {
		
		email.sendKeys(incorrectGetEmail());
	}
		
	
	public String correctGetPassword() {
		
		loadSecretInformation();
		String password = prop.getProperty("correct_nivid_password");
		return password;
	}
	
	public void correctSetPassword() {
	
		password.sendKeys(correctGetPassword());
	}
	
	
	public String incorrectGetPassword() {
		
		loadSecretInformation();
		String password = prop.getProperty("incorrect_nivid_password");
		return password;
	}
	
	public void incorrectSetPassword() {
		
		password.sendKeys(incorrectGetPassword());
	}
	
	
	public void submitButton() {
		
		submitBtn.click();
	
	}
	
	public void verifyNividLogin() throws InterruptedException {
		correctSetEmail();
		correctSetPassword();
		Thread.sleep(500);
		submitButton();
	}

}
