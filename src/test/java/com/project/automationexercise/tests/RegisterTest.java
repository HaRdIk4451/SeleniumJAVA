package com.project.automationexercise.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Company;
import com.project.automationexercise.base.BaseClass;
import com.project.automationexercise.pages.RegisterPage;
import com.project.automationexercise.utilities.Utilities;

public class RegisterTest extends BaseClass {

	@Test(priority = 1, description = "Complete new user registration with data storage")
	public void testCompleteRegistrationWithDataStorage() {
		RegisterPage registerPage = new RegisterPage(driver);

		System.out.println("===== STARTING REGISTRATION TEST WITH DATA STORAGE =====");

		// Execute complete registration flow (stores all data automatically)
		registerPage.completeRegistrationWithStoredData();

		// Verify registration success
		Assert.assertTrue(registerPage.isAccountCreated(), 
				"Account creation message not displayed");

		System.out.println("===== REGISTRATION SUCCESSFUL - DATA STORED IN SECRETS.PROPERTIES =====");
	}

	@Test(priority = 2, description = "Verify stored registration data", 
			dependsOnMethods = "testCompleteRegistrationWithDataStorage")
	public void verifyStoredRegistrationData() {
		System.out.println("===== VERIFYING STORED TEST DATA =====");

		// Retrieve all stored test data
		String storedEmail = Utilities.getStoredData("generated.email");
		String storedPassword = Utilities.getStoredData("generated.password");
		String storedFullName = Utilities.getStoredData("generated.fullname");

		// Verify data was stored properly
		Assert.assertNotNull(storedEmail, "Email not found in secrets.properties");
		Assert.assertNotNull(storedPassword, "Password not found in secrets.properties");
		Assert.assertNotNull(storedFullName, "Full name not found in secrets.properties");

		System.out.println("Stored Data Verification:");
		System.out.println("- Email: " + storedEmail);
		System.out.println("- Password: " + storedPassword);
		System.out.println("- Full Name: " + storedFullName);

		System.out.println("===== STORED DATA VERIFICATION COMPLETE =====");
	}

	@Test(priority = 3, description = "Test duplicate registration with stored email",
			dependsOnMethods = "testCompleteRegistrationWithDataStorage")
	public void testDuplicateRegistrationWithStoredEmail() {
		RegisterPage registerPage = new RegisterPage(driver);
		String storedEmail = Utilities.getStoredData("generated.email");

		System.out.println("===== TESTING DUPLICATE REGISTRATION =====");
		System.out.println("Using stored email: " + storedEmail);

		registerPage.navigateToRegistration();
		registerPage.fillBasicInformation(
				registerPage.generateAndStoreFullName(), // New name
				storedEmail                             // Existing email
				);

		// Verify duplicate email error
		Assert.assertTrue(registerPage.isEmailExistsErrorVisible(),
				"Expected error message for duplicate email not displayed");

		System.out.println("===== DUPLICATE EMAIL TEST PASSED =====");
	}

	@Test(priority = 4, description = "Test registration with minimum required fields")
	public void testMinimalRegistration() {
		RegisterPage registerPage = new RegisterPage(driver);

		System.out.println("===== TESTING MINIMAL REGISTRATION =====");

		// Generate and store only required fields
		String minimalName = registerPage.generateAndStoreFullName();
		String minimalEmail = registerPage.generateAndStoreEmail();
		String minimalPassword = registerPage.generateAndStorePassword();
		String firstName = registerPage.generateAndStoreFirstName();
		String lastName = registerPage.generateAndStoreLastName();
		String address1 = registerPage.generateAndStoreAddress1();

		// Execute minimal registration
		registerPage.navigateToRegistration();
		registerPage.fillBasicInformation(minimalName, minimalEmail);
		registerPage.selectGender("male");
		registerPage.fillPassword(minimalPassword);
		registerPage.fillDateOfBirth(1, 1, "1990"); // Default values
		
		// Fill required address information
		registerPage.fillAddressInformation(firstName, lastName, "", address1, "");
		
		// Fill required location information
		registerPage.fillLocationInfo(
			"United States",  // Default country
			"California",     // Default state
			"Los Angeles",    // Default city
			"90001",         // Default zipcode
			"1234567890"     // Default mobile
		);
		
		registerPage.submitRegistration();

		// Verify success
		Assert.assertTrue(registerPage.isAccountCreated(),
				"Minimal registration should succeed");

		System.out.println("===== MINIMAL REGISTRATION TEST PASSED =====");
	}
}