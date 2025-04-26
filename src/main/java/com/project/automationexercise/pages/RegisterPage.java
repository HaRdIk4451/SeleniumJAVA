package com.project.automationexercise.pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.project.automationexercise.utilities.Utilities;
import java.time.Duration;

public class RegisterPage {

	private WebDriver driver;
	private WebDriverWait wait;
	private Faker faker;

	// Web Elements
	@FindBy(xpath = "//a[contains(@href, '/login')]")
	private WebElement signupLink;

	@FindBy(xpath = "//input[@data-qa='signup-name']")
	private WebElement nameInput;

	@FindBy(xpath = "//input[@data-qa='signup-email']")
	private WebElement emailInput;

	@FindBy(xpath = "//button[@data-qa='signup-button']")
	private WebElement signupButton;

	@FindBy(id = "id_gender1")
	private WebElement genderMaleRadio;

	@FindBy(id = "id_gender2")
	private WebElement genderFemaleRadio;

	@FindBy(id = "password")
	private WebElement passwordInput;

	@FindBy(id = "days")
	private WebElement dayDropdown;

	@FindBy(id = "months")
	private WebElement monthDropdown;

	@FindBy(id = "years")
	private WebElement yearDropdown;

	@FindBy(id = "newsletter")
	private WebElement newsletterCheckbox;

	@FindBy(id = "optin")
	private WebElement specialOffersCheckbox;

	@FindBy(id = "first_name")
	private WebElement firstNameInput;

	@FindBy(id = "last_name")
	private WebElement lastNameInput;

	@FindBy(id = "company")
	private WebElement companyInput;

	@FindBy(id = "address1")
	private WebElement address1Input;

	@FindBy(id = "address2")
	private WebElement address2Input;

	@FindBy(id = "country")
	private WebElement countryDropdown;

	@FindBy(id = "state")
	private WebElement stateInput;

	@FindBy(id = "city")
	private WebElement cityInput;

	@FindBy(id = "zipcode")
	private WebElement zipcodeInput;

	@FindBy(id = "mobile_number")
	private WebElement mobileNumberInput;

	@FindBy(xpath = "//button[@data-qa='create-account']")
	private WebElement createAccountButton;

	@FindBy(xpath = "//h2[@data-qa='account-created']")
	private WebElement accountCreatedMessage;

	@FindBy(xpath = "//p[.='Email Address already exist!']")
	private WebElement emailExistsError;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		this.faker = new Faker();
		PageFactory.initElements(driver, this);
	}

	// Data Generation and Storage Methods
	public String generateAndStoreFullName() {
		String fullName = faker.name().fullName();
		Utilities.storeTestData("generated.fullname", fullName);
		System.out.println("Generated and stored full name: " + fullName);
		return fullName;
	}

	public String generateAndStoreEmail() {
		String email = faker.internet().emailAddress();
		Utilities.storeTestData("generated.email", email);
		System.out.println("Generated and stored email: " + email);
		return email;
	}

	public String generateAndStorePassword() {
		String password = faker.internet().password(8, 16, true, true);
		Utilities.storeTestData("generated.password", password);
		System.out.println("Generated and stored password: " + password.substring(0, 2) + "*****");
		return password;
	}

	public String generateAndStoreFirstName() {
		String firstName = faker.name().firstName();
		Utilities.storeTestData("generated.firstname", firstName);
		return firstName;
	}

	public String generateAndStoreLastName() {
		String lastName = faker.name().lastName();
		Utilities.storeTestData("generated.lastname", lastName);
		return lastName;
	}

	public String generateAndStoreCompany() {
		String company = faker.company().name();
		Utilities.storeTestData("generated.company", company);
		return company;
	}

	public String generateAndStoreAddress1() {
		String address = faker.address().streetAddress();
		Utilities.storeTestData("generated.address1", address);
		return address;
	}

	public String generateAndStoreAddress2() {
		String address = faker.address().secondaryAddress();
		Utilities.storeTestData("generated.address2", address);
		return address;
	}

	// Registration Flow Methods
	public void navigateToRegistration() {
		wait.until(ExpectedConditions.elementToBeClickable(signupLink)).click();
		System.out.println("Navigated to registration page");
	}

	public void fillBasicInformation(String name, String email) {
		wait.until(ExpectedConditions.visibilityOf(nameInput)).sendKeys(name);
		emailInput.sendKeys(email);
		signupButton.click();
		System.out.println("Filled basic information");
	}

	public void selectGender(String gender) {
		if (gender.equalsIgnoreCase("male")) {
			genderMaleRadio.click();
		} else {
			genderFemaleRadio.click();
		}
		System.out.println("Selected gender: " + gender);
	}

	public void fillPassword(String password) {
		passwordInput.sendKeys(password);
		System.out.println("Filled password");
	}

	public void fillDateOfBirth(int day, int month, String year) {
		new Select(dayDropdown).selectByValue(String.valueOf(day));
		new Select(monthDropdown).selectByValue(String.valueOf(month));
		new Select(yearDropdown).selectByValue(year);
		System.out.println("Selected DOB: " + day + "/" + month + "/" + year);
	}

	public void optForNewsletters(boolean newsletter, boolean specialOffers) {
		if (newsletter && !newsletterCheckbox.isSelected()) {
			newsletterCheckbox.click();
		}
		if (specialOffers && !specialOffersCheckbox.isSelected()) {
			specialOffersCheckbox.click();
		}
		System.out.println("Newsletter: " + newsletter + ", Special Offers: " + specialOffers);
	}

	public void fillAddressInformation(String firstName, String lastName, String company, 
			String address1, String address2) {
		firstNameInput.sendKeys(firstName);
		lastNameInput.sendKeys(lastName);
		companyInput.sendKeys(company);
		address1Input.sendKeys(address1);
		address2Input.sendKeys(address2);
		System.out.println("Filled address information");
	}

	public void fillLocationInfo(String country, String state, String city, 
			String zipcode, String mobile) {
		new Select(countryDropdown).selectByVisibleText(country);
		stateInput.sendKeys(state);
		cityInput.sendKeys(city);
		zipcodeInput.sendKeys(zipcode);
		mobileNumberInput.sendKeys(mobile);
		System.out.println("Filled location information");
	}

	public void submitRegistration() {
		createAccountButton.click();
		System.out.println("Submitted registration form");
	}

	// Verification Methods
	public boolean isAccountCreated() {
		try {
			return wait.until(ExpectedConditions.visibilityOf(accountCreatedMessage)).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isEmailExistsErrorVisible() {
		try {
			return wait.until(ExpectedConditions.visibilityOf(emailExistsError)).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	// Complete Registration Flow
	public void completeRegistrationWithStoredData() {
		// Generate and store all test data
		String fullName = generateAndStoreFullName();
		String email = generateAndStoreEmail();
		String password = generateAndStorePassword();

		// Execute registration flow
		navigateToRegistration();
		fillBasicInformation(fullName, email);

		if (isEmailExistsErrorVisible()) {
			email = generateAndStoreEmail();
			fillBasicInformation(fullName, email);
		}

		selectGender("male");
		fillPassword(password);
		fillDateOfBirth(faker.number().numberBetween(1, 28), 
				faker.number().numberBetween(1, 12), 
				String.valueOf(faker.number().numberBetween(1950, 2000)));

		optForNewsletters(true, true);

		fillAddressInformation(
				generateAndStoreFirstName(),
				generateAndStoreLastName(),
				generateAndStoreCompany(),
				generateAndStoreAddress1(),
				generateAndStoreAddress2()
				);

		fillLocationInfo(
				"United States",
				faker.address().state(),
				faker.address().city(),
				faker.address().zipCode(),
				faker.phoneNumber().cellPhone()
				);

		submitRegistration();
	}
}