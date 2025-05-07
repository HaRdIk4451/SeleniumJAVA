package com.project.automationqa.pages;

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

    @FindBy(xpath = "//a[@class='btn btn-default check_out']")
    private WebElement checkoutButton;

    @FindBy(xpath = "//input[@data-qa='name-on-card']")
    private WebElement nameOnCard;

    @FindBy(xpath = "//input[@data-qa='card-number']")
    private WebElement cardNumber;

    @FindBy(xpath = "//input[@data-qa='cvc']")
    private WebElement cvc;

    @FindBy(xpath = "//input[@data-qa='expiry-month']")
    private WebElement expiryMonth;

    @FindBy(xpath = "//input[@data-qa='expiry-year']")
    private WebElement expiryYear;

    @FindBy(xpath = "//button[@data-qa='pay-button']")
    private WebElement payButton;

    @FindBy(xpath = "//p[contains(text(),'Congratulations! Your order has been confirmed!')]")
    private WebElement orderConfirmation;

    @FindBy(xpath = "(//div[@class='col-xs-12 col-sm-6'])[2]//ul")
    private WebElement billingAddress;

    @FindBy(xpath = "(//div[@class='col-xs-12 col-sm-6'])[1]//ul")
    private WebElement deliveryAddress;

    @FindBy(xpath = "(//div[@id='cart_info'])[1]//tbody//tr")
    private WebElement orderDetails;

    @FindBy(xpath = "//tr[last()]//p")
    private WebElement orderTotal;

    @FindBy(xpath = "//a[@href='/payment']")
    private WebElement placeOrderButton;

    public CheckoutPage(WebDriver driver) {
        this.setDriver(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void proceedToCheckout() {
        checkoutButton.click();
    }

    public void clickPlaceOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton)).click();
    }

    public void enterPaymentDetails(String name, String cardNum, String cvcNum, String month, String year) {
        wait.until(ExpectedConditions.visibilityOf(nameOnCard)).sendKeys(name);
        cardNumber.sendKeys(cardNum);
        cvc.sendKeys(cvcNum);
        expiryMonth.sendKeys(month);
        expiryYear.sendKeys(year);
    }

    public void submitPayment() {
        wait.until(ExpectedConditions.elementToBeClickable(payButton)).click();
    }

    public String getOrderConfirmation() {
        return wait.until(ExpectedConditions.visibilityOf(orderConfirmation)).getText();
    }

    public boolean isBillingFormVisible() {
        return wait.until(ExpectedConditions.visibilityOf(billingAddress)).isDisplayed();
    }

    public boolean isPaymentFormVisible() {
        return wait.until(ExpectedConditions.visibilityOf(deliveryAddress)).isDisplayed();
    }

    public boolean isOrderSummaryVisible() {
        return wait.until(ExpectedConditions.visibilityOf(orderDetails)).isDisplayed();
    }

    public boolean isCardNumberEntered() {
        return !cardNumber.getDomProperty("value").isEmpty();
    }

    public boolean isExpiryDateEntered() {
        return !expiryMonth.getDomProperty("value").isEmpty() && !expiryYear.getDomProperty("value").isEmpty();
    }

    public boolean isCVVEntered() {
        return !cvc.getDomProperty("value").isEmpty();
    }

    public String getOrderTotal() {
        return wait.until(ExpectedConditions.visibilityOf(orderTotal)).getText();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
} 