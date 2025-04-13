package com.project.test.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	private String secretFilePath = "./src/main/java/config/secrets.properties";
	private Map<String, String> secretsMap;

	@FindBy(xpath="//input[@placeholder='Username']")
	WebElement username;

	@FindBy(xpath="//input[@placeholder='Password']")
	WebElement password;

	@FindBy(xpath="//button[normalize-space()='Login']")
	WebElement submitBtn;
	
	@FindBy(xpath="//p[@class='oxd-text oxd-text--p orangehrm-login-forgot-header']")
	WebElement clickToForgetPassowrd;
	
	@FindBy(xpath="//input[@name='username']")
	WebElement resetUsername;
	
	@FindBy(xpath="//button[normalize-space()='Cancel']")
	WebElement resetCancelBtn;
	
	@FindBy(xpath="//button[normalize-space()='Reset Password']")
	WebElement resetSubmitBtn;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		loadAllSecrets();
	}
	private void loadAllSecrets() {
		secretsMap = new HashMap<>();
		Properties prop = new Properties();
		try (FileInputStream credentials = new FileInputStream(secretFilePath)) {
			prop.load(credentials);
			for (String key : prop.stringPropertyNames()) {
				secretsMap.put(key, prop.getProperty(key));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getSecret(String key) {
		return secretsMap.get(key);
	}

	public void AdminSetValidUsername() {
		username.sendKeys(getSecret("valid_username_admin"));
	}
	
	public void AdminSetValidPassword() {
		password.sendKeys(getSecret("valid_password_admin"));
	}
	
	public void AdminSetInvalidUsername() {
		username.sendKeys(getSecret("invalid_username_admin"));
	}
	
	public void AdminSetInvalidPassword() {
		password.sendKeys(getSecret("invalid_password_admin"));
	}
	
	public void UserSetValidUsername() {
		username.sendKeys(getSecret("valid_username_user"));
	}
	
	public void UserSetValidPassword() {
		password.sendKeys(getSecret("valid_username_password"));
	}
	
	public void UserSetInvalidUsername() {
		username.sendKeys(getSecret("invalid_username_user"));
	}
	
	public void UserSetInvalidPassword() {
		password.sendKeys(getSecret("invalid_username_password"));
	}
	
	public void LoginSubmitBtnClick() {
		submitBtn.click();
	}
	
	public void ForgetPasswordClick() {
		clickToForgetPassowrd.click();
	}
	
	public void SetValidUsernameReset() {
		resetUsername.sendKeys(getSecret("valid_username_user"));
	}
	
	public void SetInvalidUsernameReset() {
		resetUsername.sendKeys(getSecret("invalid_username_user"));
	}
	
	public void SubmitBtnResetClick() {
		resetSubmitBtn.click();
	}
	
	public void CancelBtnClickReset() {
		resetCancelBtn.click();
	}
	
	

	public void verifyLogin() throws InterruptedException {
		UserSetValidUsername();
		UserSetValidPassword();
		LoginSubmitBtnClick();
	}

}
