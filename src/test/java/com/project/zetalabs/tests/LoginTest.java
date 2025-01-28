package com.project.zetalabs.tests;

import java.time.Duration;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.project.zetalabs.base.BaseClass;
import com.project.zetalabs.pages.LoginPage;

public class LoginTest extends BaseClass{
	
	@Test
	public void verifyCorrectCredentials() throws InterruptedException {
		
		LoginPage nividLogin = new LoginPage(driver);
		nividLogin.correctSetEmail();
		nividLogin.correctSetPassword();
		Thread.sleep(500);
		nividLogin.submitButton();
		Reporter.log("TestCase for Correct Both Credentials", true);
	}
	
	@Test
	public void verifyBlankCredentials() throws InterruptedException {
		
		LoginPage nividLogin = new LoginPage(driver);
		Thread.sleep(500);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		nividLogin.submitButton();
		Reporter.log("TestCase for Both Blank Credentials", true);
	}
	
	@Test
	public void verifyIncorrectEmail() throws InterruptedException {
		
		LoginPage nividLogin = new LoginPage(driver);
		nividLogin.incorrectSetEmail();
		nividLogin.correctSetPassword();
		Thread.sleep(500);
		nividLogin.submitButton();
		Reporter.log("TestCase for Incorrect Email and Correct Password", true);
	}
	
	@Test
	public void verifyIncorrectPassword() throws InterruptedException {
		
		LoginPage nividLogin = new LoginPage(driver);
		nividLogin.correctSetEmail();
		nividLogin.incorrectSetPassword();
		Thread.sleep(500);
		nividLogin.submitButton();
		Reporter.log("TestCase for Correct Email and Incorrect Password", true);
	}
	
	@Test
	public void verifyIncorrectCredentials() throws InterruptedException {
		
		LoginPage nividLogin = new LoginPage(driver);
		nividLogin.incorrectSetEmail();
		nividLogin.incorrectSetPassword();
		Thread.sleep(500);
		nividLogin.submitButton();
		Reporter.log("TestCase for Both Incorrect Credentials", true);
	}

}
