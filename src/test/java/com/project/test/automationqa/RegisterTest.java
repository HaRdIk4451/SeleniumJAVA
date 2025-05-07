package com.project.test.automationqa;

import org.testng.annotations.Test;

import com.project.test.automationqa.base.BaseClass;
import com.project.test.automationqa.pages.RegisterPage;

public class RegisterTest extends BaseClass{

	@Test
	public void verifyRegisterTest() throws InterruptedException {
		
		RegisterPage register = new RegisterPage(driver);
		register.setFirstName();
		Thread.sleep(2000);
		register.setLastName();
		register.chooseMaleOption();
		Thread.sleep(2000);
		register.setEmail();
		Thread.sleep(2000);
		register.setVerification();
		Thread.sleep(2000);
		register.submitButton();
	}
}

