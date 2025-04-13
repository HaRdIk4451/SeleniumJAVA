package com.project.test.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.project.test.base.BaseClass;
import com.project.test.pages.LoginPage;

public class LoginTest extends BaseClass{

	@Test(priority = 1)
	public void verifyValidLoginWithValidCredentialsAdmin() throws InterruptedException {
			LoginPage Login = new LoginPage(driver);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			Login.AdminSetValidUsername();
			Login.AdminSetValidPassword();
			Login.LoginSubmitBtnClick();
			
			WebElement dashboard = driver.findElement(By.xpath("//div[@class='oxd-topbar-header-title']"));
	        Assert.assertTrue(dashboard.isDisplayed(), "Dashboard should be visible after Valid login");
	}
}
