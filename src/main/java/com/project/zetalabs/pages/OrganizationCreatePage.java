package com.project.zetalabs.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationCreatePage {

	WebDriver driver;
	private String secretFilePath = "./src/main/java/config/secrets.properties";
	private Map<String, String> secretsMap;

	@FindBy(xpath = "//input[@name='name']")
	WebElement organizationName;

	@FindBy(xpath = "//input[@id='react-select-3-input']")
	WebElement IndustryType;

	@FindBy(xpath = "//input[@name='email']")
	WebElement email;

	@FindBy(xpath = "//input[@name='phone']")
	WebElement phone;

	@FindBy(xpath = "//input[@name='website']")
	WebElement website;

	@FindBy(xpath = "//div[@role='presentation']")
	WebElement logo;

	@FindBy(xpath = "//input[@name='street']")
	WebElement street;

	@FindBy(xpath = "//input[@name='city']")
	WebElement city;

	@FindBy(xpath = "//input[@name='postal_code']")
	WebElement postalCode;

	@FindBy(xpath = "//input[@name='state']")
	WebElement state;

	@FindBy(xpath = "//input[@id='react-select-5-input']")
	WebElement country;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement submitBtn;

	public OrganizationCreatePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		loadAllSecrets();
	}

	private void loadAllSecrets() {
		secretsMap = new HashMap<>();
		Properties prop = new Properties();
		try (FileInputStream credentials = new FileInputStream(secretFilePath)) {
			prop.load(credentials);
			for (String key : prop.stringPropertyNames()) {
				secretsMap.put(key, prop.getProperty(key));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getSecret(String key) {
		return secretsMap.get(key);
	}

	public void SetOrganizationName() {
	    organizationName.sendKeys(getSecret("organization_new_name"));
	}

	public void SetOrganizationOldName() {
		organizationName.sendKeys(getSecret("organization_old_name"));
	}

	public void SetOrganizationLongName() {
		organizationName.sendKeys(getSecret("organization_long_name"));
	}

	public void SetOrganizationInvalidNameSpecialChars() {
		organizationName.sendKeys(getSecret("organization_invalid_name_special_chars"));
	}

	public void SetOrganizationIndustry() {
		IndustryType.click();
		IndustryType.sendKeys(getSecret("organization_industry"));
		IndustryType.sendKeys(Keys.ENTER);
	}

	public void SetOrganizationInvalidIndustryNumeric() {
		IndustryType.click();
		IndustryType.sendKeys(getSecret("organization_invalid_industry_numeric"));
		IndustryType.sendKeys(Keys.ENTER);
	}

	public void SetOrganizationEmail() {
	    email.sendKeys(getSecret("organization_email"));
	}

	public void SetOrganizationInvalidEmail() {
	    email.sendKeys(getSecret("organization_invalid_email"));
	}

	public void SetOrganizationEmailWithSpaces() {
	    email.sendKeys(getSecret("organization_email_with_spaces"));
	}

	public void SetOrganizationPhone() {
		phone.sendKeys(Keys.BACK_SPACE);
	    phone.sendKeys(getSecret("organization_phone"));
	}

	public void SetOrganizationInvalidPhone() {
		phone.sendKeys(Keys.BACK_SPACE);
	    phone.sendKeys(getSecret("organization_invalid_phone"));
	}

	public void SetOrganizationPhoneWithSpaces() {
		phone.sendKeys(Keys.BACK_SPACE);
	    phone.sendKeys(getSecret("organization_phone_with_spaces"));
	}

	public void SetOrganizationWebsite() {
	    website.sendKeys(getSecret("organization_website"));
	}

	public void SetOrganizationInvalidWebsite() {
	    website.sendKeys(getSecret("organization_invalid_website"));
	}

	public void SetOrganizationWebsiteWithSpaces() {
	    website.sendKeys(getSecret("organization_website_with_spaces"));
	}
	
	public void SetValidLogo() throws AWTException, InterruptedException {
		logo.click();
	    Thread.sleep(2000);
	    String filePath = getSecret("valid_logo_path"); 
	    StringSelection selection = new StringSelection(filePath);
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
	    Robot robot = new Robot();
	    robot.keyPress(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_V);
	    robot.keyRelease(KeyEvent.VK_V);
	    robot.keyRelease(KeyEvent.VK_CONTROL);

	    // Press Enter to confirm upload
	    robot.keyPress(KeyEvent.VK_ENTER);
	    robot.keyRelease(KeyEvent.VK_ENTER);

	    // Add a short wait to ensure file upload completes
	    Thread.sleep(3000);
	}
	
	public void SetInvalidLogo() throws InterruptedException, AWTException {
		logo.click();
	    Thread.sleep(2000);
	    String filePath = getSecret("invalid_logo_path"); 
	    StringSelection selection = new StringSelection(filePath);
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
	    Robot robot = new Robot();
	    robot.keyPress(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_V);
	    robot.keyRelease(KeyEvent.VK_V);
	    robot.keyRelease(KeyEvent.VK_CONTROL);

	    // Press Enter to confirm upload
	    robot.keyPress(KeyEvent.VK_ENTER);
	    robot.keyRelease(KeyEvent.VK_ENTER);

	    // Add a short wait to ensure file upload completes
	    Thread.sleep(3000);
	}

	public void SetOrganizationStreet() {
	    street.sendKeys(getSecret("organization_street"));
	}

	public void SetOrganizationLongStreet() {
	    street.sendKeys(getSecret("organization_long_street"));
	}

	public void SetOrganizationInvalidStreetSpecialChars() {
	    street.sendKeys(getSecret("organization_invalid_street_special_chars"));
	}

	public void SetOrganizationCity() {
	    city.sendKeys(getSecret("organization_city"));
	}

	public void SetOrganizationInvalidCityNumbers() {
	    city.sendKeys(getSecret("organization_invalid_city_numbers"));
	}

	public void SetOrganizationCityWithSpecialChars() {
	    city.sendKeys(getSecret("organization_city_with_special_chars"));
	}

	public void SetOrganizationPostalCode() {
	    postalCode.sendKeys(getSecret("organization_postal_code"));
	}

	public void SetOrganizationInvalidPostalCode() {
	    postalCode.sendKeys(getSecret("organization_invalid_postal_code"));
	}

	public void SetOrganizationPostalCodeWithSpaces() {
	    postalCode.sendKeys(getSecret("organization_postal_code_with_spaces"));
	}

	public void SetOrganizationState() {
	    state.sendKeys(getSecret("organization_state"));
	}

	public void SetOrganizationInvalidStateNumeric() {
	    state.sendKeys(getSecret("organization_invalid_state_numeric"));
	}

	public void SetOrganizationStateWithSpecialChars() {
	    state.sendKeys(getSecret("organization_state_with_special_chars"));
	}

	public void SetOrganizationCountry() {
		country.click();
	    country.sendKeys(getSecret("organization_country"));
	    country.sendKeys(Keys.ENTER);
	}

	public void SetOrganizationInvalidCountryNumeric() {
		country.click();
	    country.sendKeys(getSecret("organization_invalid_country_numeric"));
	    country.sendKeys(Keys.ENTER);
	}

	public void SetOrganizationCountryWithSpecialChars() {
	    country.sendKeys(getSecret("organization_country_with_special_chars"));
	    country.sendKeys(Keys.ENTER);
	}
	
	public void clickSubmitBtn() {
		submitBtn.click();
	}

}
