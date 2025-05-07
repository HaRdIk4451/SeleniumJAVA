package com.project.test.automationqa;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.project.test.automationqa.base.BaseClass;
import com.project.test.automationqa.pages.NividLivePage;

public class NividLiveTest extends BaseClass{
	
	@Test
	public void verifyLoginTest() throws InterruptedException {
		
		NividLivePage nividLogin = new NividLivePage(driver);
		nividLogin.setEmail();
		nividLogin.setPassword();
		nividLogin.submitButton(); 
	}
	
	@Test
	public void verifyMainDashboard() {
		
		
	}

}
