package com.project.zetalabs.tests;

import java.time.Duration;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.project.zetalabs.base.BaseClass;
import com.project.zetalabs.pages.GoToDashboardPage;
import com.project.zetalabs.pages.LoginPage;

public class GoToDashboardTest extends BaseClass{
	
	@Test(priority = 1)
	public void verifyCorrectCredentials() throws InterruptedException {
		
		LoginPage login = new LoginPage(driver);
		login.verifyLogin();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		GoToDashboardPage dashboard = new GoToDashboardPage(driver);
		dashboard.ClickAdminSidebar();
		Thread.sleep(5000);
		
		Reporter.log("Test Case for Clicking Admin Sidebar", true);
	}

}
