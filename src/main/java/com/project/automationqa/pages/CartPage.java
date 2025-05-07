package com.project.automationqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//div[@class='col-sm-8']//a[@href='/view_cart']")
    private WebElement cartLink;

    @FindBy(xpath = "//tbody//tr")
    private List<WebElement> cartItems;

    @FindBy(xpath = "(//a[@class='cart_quantity_delete'])[1]")
    private List<WebElement> removeButtons;

    @FindBy(xpath = "//td[@class='cart_quantity']")
    private List<WebElement> itemTotals;

    @FindBy(xpath = "//td[@class='cart_total']")
    private WebElement cartTotal;

    @FindBy(xpath = "//a[@class='btn btn-default check_out']")
    private WebElement proceedToCheckoutButton;

    public CartPage(WebDriver driver) {
        this.setDriver(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void navigateToCart() {
        cartLink.click();
    }

    public int getCartItemCount() {
        wait.until(ExpectedConditions.visibilityOfAllElements(cartItems));
        return cartItems.size();
    }

    public void removeFirstItem() {
        wait.until(ExpectedConditions.visibilityOfAllElements(removeButtons));
        removeButtons.get(0).click();
    }

    public String getCartTotal() {
        return wait.until(ExpectedConditions.visibilityOf(cartTotal)).getText();
    }

    public List<String> getItemTotals() {
        wait.until(ExpectedConditions.visibilityOfAllElements(itemTotals));
        return itemTotals.stream()
                .map(WebElement::getText)
                .toList();
    }

    public boolean isProceedToCheckoutButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOf(proceedToCheckoutButton)).isDisplayed();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
} 