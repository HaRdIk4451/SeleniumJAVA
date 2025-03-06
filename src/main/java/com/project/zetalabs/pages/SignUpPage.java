package com.project.zetalabs.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {
    WebDriver driver;
    private String secretFilePath = "./src/main/java/config/secrets.properties";
    private Map<String, String> secretsMap;

    @FindBy(xpath = "//a[@href='/signup']")
    WebElement clickSignUp;

    @FindBy(xpath = "//input[@name='first_name']")
    WebElement Fname;

    @FindBy(xpath = "//input[@name='last_name']")
    WebElement Lname;

    @FindBy(xpath = "//input[@name='phone']")
    WebElement phone;

    @FindBy(xpath = "//input[@name='email']")
    WebElement email;

    @FindBy(xpath = "//input[@name='password']")
    WebElement password;

    @FindBy(xpath = "//button[@type='button']")
    WebElement EyeButton;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitBtn;

    public SignUpPage(WebDriver driver) {
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

    public void signUpHlink() {
        clickSignUp.click();
    }

    public void SetFname() {
        Fname.sendKeys(getSecret("signup_firstName"));
    }
    
    public void SetlongFname() {
        Fname.sendKeys(getSecret("signup_long_firstName"));
    }
    
    public void SetSpecialCharsFname() {
        Fname.sendKeys(getSecret("signup_invalid_firstName_special_chars"));
    }
    
    public void SetInvalidFname() {
        Fname.sendKeys(getSecret("signup_invalid_firstName_numbers"));
    }
    
    public void SetLname() {
    	Lname.sendKeys(getSecret("signup_lastName"));
    }
    
    public void SetLongLname() {
    	Lname.sendKeys(getSecret("signup_long_lastName"));
    }
    
    public void SetSpecialCharsLanme() {
    	Lname.sendKeys(getSecret("signup_invalid_lastName_special_chars"));
    }
    
    public void SetInvalidLname() {
    	Lname.sendKeys(getSecret("signup_invalid_lastName_numbers"));
    }
    
    public void SetNewPhoneNumber() {
        phone.sendKeys(getSecret("signup_new_phone"));
    }
    
    public void SetOldPhoneNumber() {
    	phone.sendKeys(getSecret("signup_old_phone"));
    }
    
    public void SetInvalidPhoneNumber() {
    	phone.sendKeys(getSecret("signup_invalid_phone"));
    }
    
    public void SetShortPhoneNumber() {
    	phone.sendKeys(getSecret("signup_phone_short"));
    }
    
    public void SetLongPhoneNumber() {
    	phone.sendKeys(getSecret("signup_phone_long"));
    }
    
    public void SetNewEmail() {
        email.sendKeys(getSecret("signup_new_email"));
    }
    
    public void SetOldEmail() {
    	email.sendKeys(getSecret("signup_old_email"));
    }
    
    public void SetInvalidEmail() {
    	email.sendKeys(getSecret("signup_invalid_email"));
    }
    
    public void SetEmailWithSpaces() {
    	email.sendKeys(getSecret("signup_email_with_spaces"));
    }
    
    public void SetEmailWithSpecialChars() {
    	email.sendKeys(getSecret("signup_email_special_chars"));
    }
    
    public void SetInvalidEmailDomain() {
    	email.sendKeys(getSecret("signup_invalid_email_domain"));
    }
    
    public void SetValidPasword() {
        password.sendKeys(getSecret("signup_password"));
    }
    
    public void SetInvalidPassword() {
    	password.sendKeys(getSecret("signup_invalid_password"));
    }
    
    public void SetWeakPassword() {
    	password.sendKeys(getSecret("signup_weak_password"));
    }
    
    public void SetShortPassword() {
    	password.sendKeys(getSecret("signup_password_short"));
    }
    
    public void SetLongPassword() {
    	password.sendKeys(getSecret("signup_password_long"));
    }
    
    public void SetPasswordWithNoSpecialChars() {
    	password.sendKeys(getSecret("signup_password_no_special_chars"));
    }

    public void EyeButton() {
        EyeButton.click();
    }

    public void submitButton() {
        submitBtn.click();
    }
}