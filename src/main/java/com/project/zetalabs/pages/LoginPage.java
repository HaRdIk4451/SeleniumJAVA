package com.project.zetalabs.pages;

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

	@FindBy(xpath="//input[@id=':r0:-form-item']")
	WebElement email;

	@FindBy(xpath="//input[contains(@name,'password')]")
	WebElement password;

	@FindBy(xpath="//button[contains(@type,'submit')]")
	WebElement submitBtn;
	
	@FindBy(xpath="//a[@href='/recovery-password']")
	WebElement clickToForgetPassowrd;
	
	@FindBy(xpath="//input[@name='email']")
	WebElement resetEmail;
	
	@FindBy(xpath="//button[contains(normalize-space(),'Cancel')]")
	WebElement resetCancelBtn;
	
	@FindBy(xpath="//button[contains(normalize-space(),'Reset Password')]")
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

	public void SetUnverifiedEmail() {
		email.sendKeys(getSecret("unverified_email"));
	}
	
	public void SetUnverifiedPassword() {
		password.sendKeys(getSecret("unverified_password"));
	}
	
	public void SetIncorrectEmail() {
		email.sendKeys(getSecret("incorrect_email"));
	}
	
	public void SetIncorrectPassword() {
		password.sendKeys(getSecret("incorrect_password"));
	}
	
	public void SetVerifiedEmail() {
		email.sendKeys(getSecret("verified_email"));
	}
	
	public void SetVerifiedPassword() {
		password.sendKeys(getSecret("verified_password"));
	}
	
	public void SetInvalidEmailFormat() {
		email.sendKeys(getSecret("invalid_email_format"));
	}
	
	public void SetInvalidPasswordFormat() {
		password.sendKeys(getSecret("invalid_password_format"));
	}
	
	public void clickOnForgetPassword() {
		clickToForgetPassowrd.click();
	}
	
	public void SetVerifiedValidResetEmail() {
		resetEmail.sendKeys(getSecret("verified_email"));
	}
	
	public void SetUnverifiedValidResetEmail() {
		resetEmail.sendKeys(getSecret("unverified_email"));
	}
	
	public void SetInvalidResetEmail() {
		resetEmail.sendKeys(getSecret("invalid_email_format"));
	}
	
	public void clickCancelBtnOnReset() {
		resetCancelBtn.click();
	}
	
	public void clickSubmitBtnOnReset() {
		resetSubmitBtn.click();
	}

	public void submitButton() {
		submitBtn.click();

	}

	public void verifyLogin() throws InterruptedException {
		SetVerifiedEmail();
		SetVerifiedPassword();
		submitButton();
	}

}
