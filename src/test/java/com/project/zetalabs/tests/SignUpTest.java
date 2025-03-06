package com.project.zetalabs.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.project.zetalabs.base.BaseClass;
import com.project.zetalabs.pages.SignUpPage;

public class SignUpTest extends BaseClass {
	
	@Test(priority = 1)
	public void verifyRedirectToSignUp() throws InterruptedException {
		
		SignUpPage SignUp = new SignUpPage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(5000);
		SignUp.signUpHlink();
		Thread.sleep(1000);
		Reporter.log("Test Passed: Redirecting to Sign Up was Succeed", true);
		Reporter.log("Test Failed: Redirecting to Sign Up was not Succeed", false);
	}
	
	@Test(priority = 2)
	public void verifyValidSignUp() {
	    SignUpPage signUpPage = new SignUpPage(driver);
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	    try {
	        signUpPage.signUpHlink();
	        signUpPage.SetFname();
	        signUpPage.SetLname();
	        signUpPage.SetNewPhoneNumber();
	        signUpPage.SetNewEmail();
	        signUpPage.SetValidPasword();

	        signUpPage.submitButton();

	        wait.until(ExpectedConditions.urlContains("/email-verify"));
	        String currentUrl = driver.getCurrentUrl();

	        Assert.assertTrue(currentUrl.contains("/email-verify"), 
	            "Test Passed: Redirected to /email-verify.");
	        Reporter.log("Test Passed: Valid SignUp was Redirected to /email-verify", true);
	    } catch (Exception e) {
	        Assert.fail("Test Failed: An exception occurred - " + e.getMessage());
	    }
	}
	
	@Test(priority = 3)
	public void verifyBlankSignUp() {
	    SignUpPage signUpPage = new SignUpPage(driver);
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    try {
	        signUpPage.signUpHlink();
	        Thread.sleep(2000);
	        signUpPage.submitButton();

	        WebElement validationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//p[@class='text-[0.8rem] font-medium text-destructive']")
	        ));

	        Assert.assertTrue(validationMessage.isDisplayed(), 
	            "Test Passed: Validation Message was Displayed");
	        Reporter.log("Test Passed: Validation Message was Displayed", true);
	    } catch (Exception e) {
	    	
	        Assert.fail("Test Failed: Validation Message was not Displayed - " + e.getMessage());
	    }
	}
	
	@Test(priority = 4)
	public void verifyInvalidEmail() throws InterruptedException {
	    SignUpPage signUpPage = new SignUpPage(driver);
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    
	        signUpPage.signUpHlink();
	        Thread.sleep(200);
	        signUpPage.SetFname();
	        signUpPage.SetLname();
	        signUpPage.SetNewPhoneNumber();
	        signUpPage.SetInvalidEmail();
	        signUpPage.SetValidPasword();

	        signUpPage.submitButton();

	        WebElement element = driver.findElement(By.xpath("//p[@class='text-[0.8rem] font-medium text-destructive']"));
			if (element.isDisplayed()) {
				
				Assert.assertTrue(true, "Test Passed: Validation Message was Displayed");
				Reporter.log("Test Passed: Valid SignUp was Redirected to /email-verify", true);
				} else {
					
					Assert.fail("Test Failed: It was Preceed to Next Page");
				}
	}
	
	@Test(priority = 5)
	public void verifyWeakPassword() throws InterruptedException {
		SignUpPage SignUp = new SignUpPage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(4000);
		SignUp.signUpHlink();
		Thread.sleep(500);
		SignUp.SetFname();
		SignUp.SetLname();
		SignUp.SetNewPhoneNumber();
		SignUp.SetNewEmail();
		SignUp.SetWeakPassword();
		SignUp.submitButton();
		Thread.sleep(5000);
		WebElement element = driver.findElement(By.xpath("//p[@class='text-[0.8rem] font-medium text-destructive']"));
		if (element.isDisplayed()) {
			
			Assert.assertTrue(true, "Test Passed: Validation Message was Displayed");
			} else {
				
				Assert.fail("Test Failed: It was Preceed to Next Page");
	        }
	}
	
	@Test(priority = 6)
	public void verifySignUpWithOldData() {
	    SignUpPage signUpPage = new SignUpPage(driver);
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    try {
	        signUpPage.signUpHlink();
	        Thread.sleep(500);
	        signUpPage.SetFname();
	        signUpPage.SetLname();
	        signUpPage.SetOldPhoneNumber();
	        signUpPage.SetOldEmail();
	        signUpPage.SetValidPasword();
	        signUpPage.submitButton();

	        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//li[contains(@data-state,'open')]")
	        ));

	        Assert.assertTrue(errorMessage.isDisplayed(), "Test Passed: (Error Message) User Already Exists was Displayed");
	        Reporter.log("Test Passed: Error Message was Displayed", true);
	    } catch (Exception e) {
	        Assert.fail("Test Failed: An exception occurred - " + e.getMessage());
	    }
	}
	
	@Test(priority = 7)
	public void verifyPasswordVisibilityToggle() {
		SignUpPage signUpPage = new SignUpPage(driver);
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	    try {
	        signUpPage.signUpHlink();
	        signUpPage.SetFname();
	        signUpPage.SetLname();
	        signUpPage.SetNewPhoneNumber();
	        signUpPage.SetNewEmail();
	        signUpPage.SetValidPasword();
	        signUpPage.EyeButton();
	        
	        Thread.sleep(5000);


	        WebElement passwordVisibility = wait.until(ExpectedConditions.visibilityOfElementLocated(
		            By.xpath("//input[contains(@type,'text')]")
		        ));

		        Assert.assertTrue(passwordVisibility.isDisplayed(), "Test Passed: Password Visibility Button was Working Correctly");
		        Reporter.log("Test Passed: Password Visibility Button Was Functioning Properly", true);
		    } catch (Exception e) {
		        Assert.fail("Test Failed: An exception occurred - " + e.getMessage());
		    }
	}
}
