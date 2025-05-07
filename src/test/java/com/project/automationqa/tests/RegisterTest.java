package com.project.automationqa.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.project.automationqa.base.BaseClass;
import com.project.automationqa.pages.RegisterPage;
import com.project.automationqa.utilities.Utilities;

public class RegisterTest extends BaseClass{
	@Test(priority = 1, description = "Complete new user registration with data storage")
	public void testCompleteRegistrationWithDataStorage() {
		Reporter.log("Starting testCompleteRegistrationWithDataStorage test", true);
		RegisterPage registerPage = new RegisterPage(driver);

		// Execute complete registration flow (stores all data automatically)
		registerPage.completeRegistrationWithStoredData();
		Reporter.log("Completed registration flow", true);

		// Verify registration success
		boolean isAccountCreated = registerPage.isAccountCreated();
		Assert.assertTrue(isAccountCreated, 
				"Account creation message not displayed");
		Assert.assertEquals(isAccountCreated, true, "Test should pass if account is created successfully");
		Reporter.log("Verified account creation", true);

		System.out.println("===== REGISTRATION SUCCESSFUL - DATA STORED IN SECRETS.PROPERTIES =====");
	}

	@Test(priority = 2, description = "Verify stored registration data", 
			dependsOnMethods = "testCompleteRegistrationWithDataStorage")
	public void verifyStoredRegistrationData() {
		Reporter.log("Starting verifyStoredRegistrationData test", true);

		// Retrieve all stored test data
		String storedEmail = Utilities.getStoredData("generated.email");
		String storedPassword = Utilities.getStoredData("generated.password");
		String storedFullName = Utilities.getStoredData("generated.fullname");
		Reporter.log("Retrieved stored data - Email: " + storedEmail + ", FullName: " + storedFullName, true);

		// Verify data was stored properly
		boolean isEmailStored = storedEmail != null;
		boolean isPasswordStored = storedPassword != null;
		boolean isFullNameStored = storedFullName != null;
		boolean isAllDataStored = isEmailStored && isPasswordStored && isFullNameStored;

		Assert.assertNotNull(storedEmail, "Email not found in secrets.properties");
		Assert.assertNotNull(storedPassword, "Password not found in secrets.properties");
		Assert.assertNotNull(storedFullName, "Full name not found in secrets.properties");
		Assert.assertTrue(isAllDataStored, "Test should pass if all data is stored properly");
		Reporter.log("Verified all data is stored properly", true);

		System.out.println("Stored Data Verification:");
		System.out.println("- Email: " + storedEmail);
		System.out.println("- Password: " + storedPassword);
		System.out.println("- Full Name: " + storedFullName);

		System.out.println("===== STORED DATA VERIFICATION COMPLETE =====");
	}

	@Test(priority = 3, description = "Test duplicate registration with stored email",
			dependsOnMethods = "testCompleteRegistrationWithDataStorage")
	public void testDuplicateRegistrationWithStoredEmail() {
		Reporter.log("Starting testDuplicateRegistrationWithStoredEmail test", true);
		RegisterPage registerPage = new RegisterPage(driver);
		String storedEmail = Utilities.getStoredData("generated.email");

		Reporter.log("Using stored email: " + storedEmail, true);

		registerPage.navigateToRegistration();
		registerPage.fillBasicInformation(
				registerPage.generateAndStoreFullName(), // New name
				storedEmail                             // Existing email
				);
		Reporter.log("Attempted registration with existing email", true);

		// Verify duplicate email error
		boolean isErrorVisible = registerPage.isEmailExistsErrorVisible();
		Assert.assertTrue(isErrorVisible,
				"Expected error message for duplicate email not displayed");
		Assert.assertEquals(isErrorVisible, true, "Test should pass if duplicate email error is displayed");
		Reporter.log("Verified duplicate email error message", true);
	}

	@Test(priority = 4, description = "Test registration with minimum required fields")
	public void testMinimalRegistration() {
		Reporter.log("Starting testMinimalRegistration test", true);
		RegisterPage registerPage = new RegisterPage(driver);

		// Generate and store only required fields
		String minimalName = registerPage.generateAndStoreFullName();
		String minimalEmail = registerPage.generateAndStoreEmail();
		String minimalPassword = registerPage.generateAndStorePassword();
		String firstName = registerPage.generateAndStoreFirstName();
		String lastName = registerPage.generateAndStoreLastName();
		String address1 = registerPage.generateAndStoreAddress1();
		Reporter.log("Generated minimal required fields", true);

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
		Reporter.log("Filled minimal registration form", true);
		
		registerPage.submitRegistration();

		// Verify success
		boolean isMinimalRegistrationSuccess = registerPage.isAccountCreated();
		Assert.assertTrue(isMinimalRegistrationSuccess,
				"Minimal registration should succeed");
		Assert.assertEquals(isMinimalRegistrationSuccess, true, "Test should pass if minimal registration succeeds");
		Reporter.log("Verified minimal registration success", true);
	}

}
