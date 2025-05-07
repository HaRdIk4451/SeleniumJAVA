package com.project.test.automationqa.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	private String secretFilePath = "./src/main/java/configs/secrets.properties";
	Properties prop;
	FileInputStream credentials;
	
	@FindBy(xpath="//input[@name='vfb-5']")
	WebElement firstNameElement;
	
	@FindBy(xpath="//input[@name='vfb-7']")
	WebElement lastNameElement;
	
	@FindBy(xpath="//input[@value='Male']")
	WebElement radioBtnMale;
	
	@FindBy(xpath="//input[@type='email']")
	WebElement email;
	
	@FindBy(xpath="//input[@name='vfb-3']")
	WebElement twoDigitVerification;
	
	@FindBy(xpath="//input[@id='vfb-4']")
	WebElement submitBtn;
	
 
	public RegisterPage(WebDriver driver) {
		
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
	
	public void setFirstName() {
		
		firstNameElement.sendKeys(getFirstName());
	}
	
	public void setLastName() {
		
		lastNameElement.sendKeys(getLastName());
	}
	
	public void setEmail() {
		
		email.sendKeys(getEmail());
	}
	
	public String getEmail() {
		
		loadSecretInformation();
		String email = prop.getProperty("email");
		return email;
	}
	
	
	public String getFirstName() {
		
		loadSecretInformation();
		String firstName = prop.getProperty("firstname");
		return firstName;
	}
	
	
	
	public String getLastName() {
		
		loadSecretInformation();
		String lastName = prop.getProperty("lastname");
		return lastName;
	}
	
	public void chooseMaleOption() {
		
		radioBtnMale.click();
	}
	
	public String getVerification() {
		
		loadSecretInformation();
		String twoDigit = prop.getProperty("verification");
		return twoDigit;
	}
	
	public void setVerification() {
		
		twoDigitVerification.sendKeys(getVerification());
	}
	
	public void submitButton() {
		
		submitBtn.click();
	
	}

}
