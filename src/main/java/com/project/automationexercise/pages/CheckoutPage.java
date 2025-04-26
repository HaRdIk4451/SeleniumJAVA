package com.project.automationexercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//a[contains(@href, '/checkout')]")
    private WebElement checkoutButton;

    @FindBy(xpath = "//textarea[@name='message']")
    private WebElement orderCommentInput;

    @FindBy(xpath = "//a[contains(@href, '/payment')]")
    private WebElement placeOrderButton;

    @FindBy(xpath = "//input[@name='name_on_card']")
    private WebElement nameOnCardInput;

    @FindBy(xpath = "//input[@name='card_number']")
    private WebElement cardNumberInput;

    @FindBy(xpath = "//input[@name='cvc']")
    private WebElement cvcInput;

    @FindBy(xpath = "//input[@name='expiry_month']")
    private WebElement expiryMonthInput;

    @FindBy(xpath = "//input[@name='expiry_year']")
    private WebElement expiryYearInput;

    @FindBy(xpath = "//button[@id='submit']")
    private WebElement payAndConfirmButton;

    @FindBy(xpath = "//div[contains(@class, 'success-message')]")
    private WebElement orderSuccessMessage;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void proceedToCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }

    public void addOrderComment(String comment) {
        wait.until(ExpectedConditions.visibilityOf(orderCommentInput)).sendKeys(comment);
    }

    public void placeOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton)).click();
    }

    public void enterPaymentDetails(String nameOnCard, String cardNumber, String cvc, String expiryMonth, String expiryYear) {
        wait.until(ExpectedConditions.visibilityOf(nameOnCardInput)).sendKeys(nameOnCard);
        cardNumberInput.sendKeys(cardNumber);
        cvcInput.sendKeys(cvc);
        expiryMonthInput.sendKeys(expiryMonth);
        expiryYearInput.sendKeys(expiryYear);
    }

    public void confirmPayment() {
        wait.until(ExpectedConditions.elementToBeClickable(payAndConfirmButton)).click();
    }

    public boolean verifyOrderSuccess() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(orderSuccessMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
} 