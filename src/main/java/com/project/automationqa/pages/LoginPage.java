package com.project.automationqa.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//a[contains(@href, '/login')]")
    private WebElement loginLink;

    @FindBy(xpath = "//input[@data-qa='login-email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@data-qa='login-password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@data-qa='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//a[@href='/logout']")
    private WebElement logoutLink;

    @FindBy(xpath = "//p[normalize-space()='Your email or password is incorrect!']")
    private WebElement loginError;

    @FindBy(xpath = "//div[@class='shop-menu pull-right']//b")
    private WebElement loggedInUser;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void navigateToLogin() {
        loginLink.click();
    }

    public void login(String email, String password) {
        wait.until(ExpectedConditions.visibilityOf(emailInput)).sendKeys(email);
        passwordInput.sendKeys(password);
        loginButton.click();
    }

    public void logout() {
        logoutLink.click();
    }

    public String getLoggedInUser() {
        return wait.until(ExpectedConditions.visibilityOf(loggedInUser)).getText();
    }

    public boolean isLoggedIn() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(logoutLink)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoginErrorVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(loginError)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

}
