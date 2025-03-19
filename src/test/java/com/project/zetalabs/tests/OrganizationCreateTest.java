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
import com.project.zetalabs.pages.OrganizationCreatePage;

public class OrganizationCreateTest extends BaseClass {
	
	@Test(priority = 7)
	public void verifyValidOrganizationCreation() throws InterruptedException {
		try {
			LoginPage Login = new LoginPage(driver);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			Login.verifyLogin();
			
			OrganizationCreatePage org = new OrganizationCreatePage(driver);
			org.SetOrganizationName();
			org.SetOrganizationIndustry();
			org.SetOrganizationEmail();
			org.SetOrganizationPhone();
			org.SetOrganizationWebsite();
			org.SetValidLogo();
			org.SetOrganizationStreet();
			org.SetOrganizationCity();
			org.SetOrganizationPostalCode();
			org.SetOrganizationState();
			org.SetOrganizationCountry();
			Thread.sleep(1000);
			org.clickSubmitBtn();
			wait.until(ExpectedConditions.urlContains("http://192.168.1.72:3000/"));
			String currentUrl = driver.getCurrentUrl();

			Assert.assertTrue(currentUrl.contains("http://192.168.1.72:3000/"), 
					"Test Passed: Redirected to http://192.168.1.72:3000/.");
			Reporter.log("Test Passed: Redirected to http://192.168.1.72:3000/", true);
		} catch (Exception e) {
			Assert.fail("Test Failed: An exception occurred - " + e.getMessage());
		}
		Thread.sleep(4000);
	}
	
	@Test(priority = 1)
	public void verifyBlankOrganizationCreation() throws InterruptedException {
		try {
			LoginPage Login = new LoginPage(driver);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			Login.verifyLogin();
			
			OrganizationCreatePage org = new OrganizationCreatePage(driver);
			Thread.sleep(1000);
			org.clickSubmitBtn();
			WebElement validationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//p[contains(@class,'text-[0.8rem] font-medium text-destructive')]")
					));

			Assert.assertTrue(validationMessage.isDisplayed(), 
					"Test Passed: Validation Message was Displayed");
			Reporter.log("Test Passed: Validation Message was Displayed below all the form Fields", true);
		} catch (Exception e) {

			Assert.fail("Test Failed: Validation Message was not Displayed - " + e.getMessage());
		}
		Thread.sleep(4000);
	}
	
	@Test(priority = 2)
	public void verifyInvalidEmailInOrganizationCreation() throws InterruptedException {
		try {
			LoginPage Login = new LoginPage(driver);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			Login.verifyLogin();
			
			OrganizationCreatePage org = new OrganizationCreatePage(driver);
			org.SetOrganizationName();
			org.SetOrganizationIndustry();
			org.SetOrganizationInvalidEmail();
			org.SetOrganizationPhone();
			org.SetOrganizationWebsite();
			org.SetValidLogo();
			org.SetOrganizationStreet();
			org.SetOrganizationCity();
			org.SetOrganizationPostalCode();
			org.SetOrganizationState();
			org.SetOrganizationCountry();
			Thread.sleep(1000);
			org.clickSubmitBtn();
			WebElement validationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//p[.='Invalid email address']")
					));

			Assert.assertTrue(validationMessage.isDisplayed(), 
					"Test Passed: Validation Message was Displayed");
			Reporter.log("Test Passed: Validation Message was Displayed mentioning Email format Was invalid", true);
		} catch (Exception e) {

			Assert.fail("Test Failed: Validation Message was not Displayed - " + e.getMessage());
		}
		Thread.sleep(4000);
	}
	
	@Test(priority = 3)
	public void verifyInvalidPhoneNumberInOrganizationCreate() throws InterruptedException {
		try {
			LoginPage Login = new LoginPage(driver);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			Login.verifyLogin();
			
			OrganizationCreatePage org = new OrganizationCreatePage(driver);
			org.SetOrganizationName();
			org.SetOrganizationIndustry();
			org.SetOrganizationEmail();
			org.SetOrganizationInvalidPhone();
			org.SetOrganizationWebsite();
			org.SetValidLogo();
			org.SetOrganizationStreet();
			org.SetOrganizationCity();
			org.SetOrganizationPostalCode();
			org.SetOrganizationState();
			org.SetOrganizationCountry();
			Thread.sleep(1000);
			org.clickSubmitBtn();
			WebElement validationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//p[.='Invalid phone number format']")
					));

			Assert.assertTrue(validationMessage.isDisplayed(), 
					"Test Passed: Validation Message was Displayed");
			Reporter.log("Test Passed: Validation Message was Displayed mentioning Phone Number format Was invalid", true);
		} catch (Exception e) {

			Assert.fail("Test Failed: Validation Message was not Displayed - " + e.getMessage());
		}
		Thread.sleep(4000);
	}
	
	@Test(priority = 4)
	public void verifyInvalidWebsiteInOrganization() throws InterruptedException {
		try {
			LoginPage Login = new LoginPage(driver);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			Login.verifyLogin();
			
			OrganizationCreatePage org = new OrganizationCreatePage(driver);
			org.SetOrganizationName();
			org.SetOrganizationIndustry();
			org.SetOrganizationEmail();
			org.SetOrganizationPhone();
			org.SetOrganizationInvalidWebsite();
			org.SetValidLogo();
			org.SetOrganizationStreet();
			org.SetOrganizationCity();
			org.SetOrganizationPostalCode();
			org.SetOrganizationState();
			org.SetOrganizationCountry();
			Thread.sleep(1000);
			org.clickSubmitBtn();
			WebElement validationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//p[contains(@class,'text-[0.8rem] font-medium text-destructive')]")
					));

			Assert.assertTrue(validationMessage.isDisplayed(), 
					"Test Passed: Validation Message was Displayed");
			Reporter.log("Test Passed: Validation Message was Displayed mentioning Email format Was invalid", true);
		} catch (Exception e) {

			Assert.fail("Test Failed: Validation Message was not Displayed - " + e.getMessage());
		}
		Thread.sleep(4000);
	}
	
	@Test(priority = 5)
	public void verifyUploadNonImageFile() throws InterruptedException {
		try {
			LoginPage Login = new LoginPage(driver);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			Login.verifyLogin();
			
			OrganizationCreatePage org = new OrganizationCreatePage(driver);
			org.SetOrganizationName();
			org.SetOrganizationIndustry();
			org.SetOrganizationEmail();
			org.SetOrganizationPhone();
			org.SetOrganizationInvalidWebsite();
			org.SetInvalidLogo();
			org.SetOrganizationStreet();
			org.SetOrganizationCity();
			org.SetOrganizationPostalCode();
			org.SetOrganizationState();
			org.SetOrganizationCountry();
			Thread.sleep(1000);
			org.clickSubmitBtn();
			wait.until(ExpectedConditions.urlContains("http://192.168.1.72:3000/"));
			String currentUrl = driver.getCurrentUrl();

			Assert.assertTrue(currentUrl.contains("http://192.168.1.72:3000/"), 
					"Test Passed: Non Image File was not taken");
			Reporter.log("Test Passed: Non Image File was Not taken", true);
		} catch (Exception e) {
			Assert.fail("Test Failed: An exception occurred - " + e.getMessage());
		}
		Thread.sleep(4000);
	}
	
	@Test(priority = 6)
	public void verifyInvalidPostalCodeinOrganizationCreate() throws InterruptedException {
		try {
			LoginPage Login = new LoginPage(driver);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			Login.verifyLogin();
			
			OrganizationCreatePage org = new OrganizationCreatePage(driver);
			org.SetOrganizationName();
			org.SetOrganizationIndustry();
			org.SetOrganizationEmail();
			org.SetOrganizationPhone();
			org.SetOrganizationInvalidWebsite();
			org.SetValidLogo();
			org.SetOrganizationStreet();
			org.SetOrganizationCity();
			org.SetOrganizationInvalidPostalCode();
			org.SetOrganizationState();
			org.SetOrganizationCountry();
			Thread.sleep(1000);
			org.clickSubmitBtn();
			WebElement validationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//p[contains(@class,'text-[0.8rem] font-medium text-destructive')]")
					));

			Assert.assertTrue(validationMessage.isDisplayed(), 
					"Test Passed: Validation Message was Displayed");
			Reporter.log("Test Passed: Validation Message was Displayed", true);
		} catch (Exception e) {

			Assert.fail("Test Failed: Validation Message was not Displayed - " + e.getMessage());
		}
		Thread.sleep(4000);
	}
}
