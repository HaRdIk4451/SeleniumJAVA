package com.project.zetalabs.tests;

import java.time.Duration;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.project.zetalabs.base.BaseClass;
import com.project.zetalabs.pages.LoginPage;

public class LoginTest extends BaseClass{
	
	@Test(priority = 1)
	public void verifyCorrectCredentials() throws InterruptedException {
		
		LoginPage Login = new LoginPage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(5000);
		Login.correctSetEmail();
		Login.correctSetPassword();
		Thread.sleep(500);
		Login.submitButton();
		Reporter.log("TestCase for Correct Both Credentials", true);
	}
	
	@Test(priority = 2)
	public void verifyBlankCredentials() throws InterruptedException {
		
		LoginPage Login = new LoginPage(driver);
		Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Login.submitButton();
		Reporter.log("TestCase for Both Blank Credentials", true);
	}
	
	@Test(priority = 3)
	public void verifyIncorrectEmail() throws InterruptedException {
		
		LoginPage Login = new LoginPage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(5000);
		Login.incorrectSetEmail();
		Login.correctSetPassword();
		Thread.sleep(500);
		Login.submitButton();
		Reporter.log("TestCase for Incorrect Email and Correct Password", true);
	}
	
	@Test(priority = 4)
	public void verifyIncorrectPassword() throws InterruptedException {
		
		LoginPage Login = new LoginPage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(5000);
		Login.correctSetEmail();
		Login.incorrectSetPassword();
		Thread.sleep(500);
		Login.submitButton();
		Reporter.log("TestCase for Correct Email and Incorrect Password", true);
	}
	
	@Test(priority = 5)
	public void verifyIncorrectCredentials() throws InterruptedException {
		
		LoginPage Login = new LoginPage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(5000);
		Login.incorrectSetEmail();
		Login.incorrectSetPassword();
		Thread.sleep(500);
		Login.submitButton();
		Reporter.log("TestCase for Both Incorrect Credentials", true);
	}

}
