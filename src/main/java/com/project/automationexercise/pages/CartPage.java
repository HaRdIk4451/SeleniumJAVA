package com.project.automationexercise.pages;

import org.openqa.selenium.Keys;
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

    @FindBy(xpath = "//a[contains(text(),'Cart')]")
    private WebElement cartLink;

    @FindBy(xpath = "//a[@href='/product_details/3']")
    private List<WebElement> cartItems;

    @FindBy(xpath = "//input[@id='quantity']")
    private List<WebElement> quantityIncreaseButtons;

    @FindBy(xpath = "//input[@id='quantity']")
    private List<WebElement> quantityDecreaseButtons;

    @FindBy(xpath = "//a[@class='cart_quantity_delete']")
    private List<WebElement> deleteButtons;

    @FindBy(xpath = "//input[@id='quantity']")
    private List<WebElement> itemTotals;

    @FindBy(xpath = "//span[contains(@class, 'cart_total_price')]")
    private WebElement cartTotal;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void navigateToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
    }

    public int getCartItemCount() {
        wait.until(ExpectedConditions.visibilityOfAllElements(cartItems));
        return cartItems.size();
    }

    public void increaseQuantity(int itemIndex, int times) {
        if (itemIndex >= 0 && itemIndex < quantityIncreaseButtons.size()) {
            for (int i = 0; i < times; i++) {
                wait.until(ExpectedConditions.elementToBeClickable(quantityIncreaseButtons.get(itemIndex))).sendKeys(Keys.ARROW_UP);
            }
        }
    }

    public void decreaseQuantity(int itemIndex, int times) {
        if (itemIndex >= 0 && itemIndex < quantityDecreaseButtons.size()) {
            for (int i = 0; i < times; i++) {
                wait.until(ExpectedConditions.elementToBeClickable(quantityDecreaseButtons.get(itemIndex))).sendKeys(Keys.ARROW_DOWN);
            }
        }
    }

    public void removeItem(int itemIndex) {
        if (itemIndex >= 0 && itemIndex < deleteButtons.size()) {
            wait.until(ExpectedConditions.elementToBeClickable(deleteButtons.get(itemIndex))).click();
        }
    }

    public String getItemTotal(int itemIndex) {
        if (itemIndex >= 0 && itemIndex < itemTotals.size()) {
            return itemTotals.get(itemIndex).getText();
        }
        return null;
    }

    public String getCartTotal() {
        return wait.until(ExpectedConditions.visibilityOf(cartTotal)).getText();
    }

    public boolean verifyCartEmpty() {
        return cartItems.isEmpty();
    }
} 