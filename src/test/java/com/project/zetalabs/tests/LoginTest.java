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
import com.project.zetalabs.pages.LoginPage;

public class LoginTest extends BaseClass{

	@Test(priority = 1)
	public void verifyVerifiedCorrectCredentials() throws InterruptedException {
		try {
			LoginPage Login = new LoginPage(driver);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			Thread.sleep(5000);
			Login.SetVerifiedEmail();
			Login.SetVerifiedPassword();
			Login.submitButton();
			wait.until(ExpectedConditions.urlContains("/create-organization"));
			String currentUrl = driver.getCurrentUrl();

			Assert.assertTrue(currentUrl.contains("/create-organization"), 
					"Test Passed: Redirected to /create-organization.");
			Reporter.log("Test Passed: Verified Login was Redirected to /create-organization", true);
		} catch (Exception e) {
			Assert.fail("Test Failed: An exception occurred - " + e.getMessage());
		}
	}
	
	@Test(priority = 2)
	public void verifyUnverifiedCorrectcredentials() {
		try {
			LoginPage Login = new LoginPage(driver);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			Thread.sleep(5000);
			Login.SetUnverifiedEmail();
			Login.SetUnverifiedPassword();
			Login.submitButton();
			WebElement validationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//li[.='Your email is not verified. Please check your inbox for the verification email.']")
					));

			Assert.assertTrue(validationMessage.isDisplayed(), 
					"Test Passed: Validation Message was Displayed");
			Reporter.log("Test Passed: Validation Message was Displayed mentioning Email was not Verified", true);
		} catch (Exception e) {

			Assert.fail("Test Failed: Validation Message was not Displayed - " + e.getMessage());
		}
	}

	@Test(priority = 3)
	public void verifyIncorrectPassword() throws InterruptedException {

		try {
			LoginPage Login = new LoginPage(driver);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			Thread.sleep(5000);
			Login.SetIncorrectEmail();
			Login.SetIncorrectPassword();
			Login.submitButton();
			Thread.sleep(2000);
			WebElement validationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//li[.='This email is not registered.']")
					));

			Assert.assertTrue(validationMessage.isDisplayed(), 
					"Test Passed: Validation Message was Displayed");
			Reporter.log("Test Passed: Validation Message was Displayed mentioning Email was not Registered", true);
		} catch (Exception e) {

			Assert.fail("Test Failed: Validation Message was not Displayed - " + e.getMessage());
		}
	}

	@Test(priority = 4)
	public void verifyIncorrectEmail() throws InterruptedException {

		try {
			LoginPage Login = new LoginPage(driver);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			Thread.sleep(5000);
			Login.SetVerifiedEmail();
			Login.SetIncorrectPassword();
			Login.submitButton();
			WebElement validationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//li[.='Invalid email or password.']")
					));

			Assert.assertTrue(validationMessage.isDisplayed(), 
					"Test Passed: Validation Message was Displayed");
			Reporter.log("Test Passed: Validation Message was Displayed mentioning Invalid Email was not Registered", true);
		} catch (Exception e) {

			Assert.fail("Test Failed: Validation Message was not Displayed - " + e.getMessage());
		}
	}

	@Test(priority = 5)
	public void verifyIncorrectCredentials() throws InterruptedException {

		try {
			LoginPage Login = new LoginPage(driver);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			Thread.sleep(5000);
			Login.SetIncorrectEmail();
			Login.SetIncorrectPassword();
			Login.submitButton();
			WebElement validationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//li[.='This email is not registered.']")
					));

			Assert.assertTrue(validationMessage.isDisplayed(), 
					"Test Passed: Validation Message was Displayed");
			Reporter.log("Test Passed: Validation Message was Displayed mentioning Email was not Registered", true);
		} catch (Exception e) {

			Assert.fail("Test Failed: Validation Message was not Displayed - " + e.getMessage());
		}
	}

	@Test(priority = 6)
	public void verifyBlankCredentials() throws InterruptedException {

		try {
			LoginPage Login = new LoginPage(driver);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			Thread.sleep(5000);
			Login.submitButton();
			WebElement validationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//p[contains(@class,'text-[0.8rem] font-medium text-destructive')]")
					));

			Assert.assertTrue(validationMessage.isDisplayed(), 
					"Test Passed: Validation Message was Displayed");
			Reporter.log("Test Passed: Validation Message was Displayed below the form", true);
		} catch (Exception e) {

			Assert.fail("Test Failed: Validation Message was not Displayed - " + e.getMessage());
		}
	}
	
	@Test(priority = 7)
	public void verifyInvalidEmailFormat() {
		try {
			LoginPage Login = new LoginPage(driver);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			Thread.sleep(5000);
			Login.SetInvalidEmailFormat();
			Login.SetVerifiedPassword();
			Login.submitButton();
			WebElement validationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//p[contains(@class,'text-[0.8rem] font-medium text-destructive')]")
					));

			Assert.assertTrue(validationMessage.isDisplayed(),
					"Test Passed: Validation Message was Displayed");
			Reporter.log("Test Passed: Validation Message was Displayed below the Email Form", true);
		} catch (Exception e) {

			Assert.fail("Test Failed: Validation Message was not Displayed - " + e.getMessage());
		}
	}
	
	@Test(priority = 8)
	public void verifyInvalidPasswordFormat() {
		try {
			LoginPage Login = new LoginPage(driver);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			Thread.sleep(5000);
			Login.SetVerifiedEmail();
			Login.SetInvalidPasswordFormat();
			Login.submitButton();
			WebElement validationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//li[.='Invalid email or password.']")
					));

			Assert.assertTrue(validationMessage.isDisplayed(), 
					"Test Passed: Validation Message was Displayed");
			Reporter.log("Test Passed: Validation Message was Displayed mentioning Invalid Email was not Registered", true);
		} catch (Exception e) {

			Assert.fail("Test Failed: Validation Message was not Displayed - " + e.getMessage());
		}
	}
	
	//Reset Password / Forget Password
	
	@Test(priority = 9)
	public void verifyVerifiedValidResetEmail() {
		try {
			LoginPage Login = new LoginPage(driver);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			Thread.sleep(5000);
			Login.clickOnForgetPassword();
			Login.SetVerifiedValidResetEmail();
			Login.clickSubmitBtnOnReset();
			WebElement validationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//li[.='Password reset link sent to your email.']")
					));

			Assert.assertTrue(validationMessage.isDisplayed(), 
					"Test Passed: Validation Message was Displayed");
			Reporter.log("Test Passed: Validation Message was Displayed mentioning Password Reset Link Sent to mail", true);
		} catch (Exception e) {

			Assert.fail("Test Failed: Validation Message was not Displayed - " + e.getMessage());
		}
	}
	
	@Test(priority = 10)
	public void verifyUnverifiedValidResetEmail() {
		try {
			LoginPage Login = new LoginPage(driver);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			Thread.sleep(5000);
			Login.clickOnForgetPassword();
			Login.SetUnverifiedValidResetEmail();
			Login.clickSubmitBtnOnReset();
			WebElement validationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//li[.='Password reset link sent to your email.']")
					));

			Assert.assertTrue(validationMessage.isDisplayed(), 
					"Test Passed: Validation Message was Displayed");
			Reporter.log("Test Passed: Validation Message was Displayed mentioning Password Reset Link Sent to mail", true);
		} catch (Exception e) {

			Assert.fail("Test Failed: Validation Message was not Displayed - " + e.getMessage());
		}
	}
	
	@Test(priority = 11)
	public void verifyInvalidResetEmailFormat() {
		try {
			LoginPage Login = new LoginPage(driver);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			Login.clickOnForgetPassword();
			Thread.sleep(5000);
			Login.SetInvalidResetEmail();
			Login.clickSubmitBtnOnReset();
			WebElement validationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//p[contains(@class,'text-[0.8rem] font-medium text-destructive')]")
					));

			Assert.assertTrue(validationMessage.isDisplayed(), 
					"Test Passed: Validation Message was Displayed");
			Reporter.log("Test Passed: Validation Message was Displayed mentioning Password Invalid Email", true);
		} catch (Exception e) {

			Assert.fail("Test Failed: Validation Message was not Displayed - " + e.getMessage());
		}
	}
	
	@Test(priority = 12)
	public void verifyBlankResetPassword() {
		try {
			LoginPage Login = new LoginPage(driver);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			Thread.sleep(5000);
			Login.clickOnForgetPassword();
			Login.clickSubmitBtnOnReset();
			WebElement validationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//p[contains(@class,'text-[0.8rem] font-medium text-destructive')]")
					));

			Assert.assertTrue(validationMessage.isDisplayed(), 
					"Test Passed: Validation Message was Displayed");
			Reporter.log("Test Passed: Validation Message was Displayed below mentioning Email is Required", true);
		} catch (Exception e) {

			Assert.fail("Test Failed: Validation Message was not Displayed - " + e.getMessage());
		}
	}
	
	@Test(priority = 13)
	public void verifyCancelBtnInResetPassword() {
		try {
			LoginPage Login = new LoginPage(driver);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			Thread.sleep(5000);
			Login.clickOnForgetPassword();
			Login.clickCancelBtnOnReset();
			wait.until(ExpectedConditions.urlContains("/login"));
			String currentUrl = driver.getCurrentUrl();

			Assert.assertTrue(currentUrl.contains("/login"), 
					"Test Passed: Redirected to /login.");
			Reporter.log("Test Passed: Verified Login was Redirected to /login", true);
		} catch (Exception e) {
			Assert.fail("Test Failed: An exception occurred - " + e.getMessage());
		}
	}

}
