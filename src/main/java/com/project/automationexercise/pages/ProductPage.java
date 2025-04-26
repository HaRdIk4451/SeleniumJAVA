package com.project.automationexercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//a[contains(@href, '/products')]")
    private WebElement productsLink;

    @FindBy(xpath = "(//div[@class='productinfo text-center'])[1]")
    private List<WebElement> productItems;

    @FindBy(xpath = "(//p[contains(text(),'Sleeveless Dress')])[1]")
    private List<WebElement> productNames;

    @FindBy(xpath = "(//h2[contains(text(),'Rs. 1000')])[1]")
    private List<WebElement> productPrices;

    @FindBy(xpath = "//a[@href='#Women']")
    private WebElement womenCategoryLink;

    @FindBy(xpath = "//a[contains(@href,'/category_products/1')]")
    private WebElement womenDressSubcategoryLink;

    @FindBy(xpath = "//a[@data-product-id=3]")
    private List<WebElement> addToCartButtons;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void navigateToProducts() {
        wait.until(ExpectedConditions.elementToBeClickable(productsLink)).click();
    }

    public void filterByWomenDress() {
        wait.until(ExpectedConditions.elementToBeClickable(womenCategoryLink)).click();
        wait.until(ExpectedConditions.elementToBeClickable(womenDressSubcategoryLink)).click();
    }

    public boolean verifyFilteredProducts(String expectedKeyword) {
        wait.until(ExpectedConditions.visibilityOfAllElements(productNames));
        return productNames.stream()
                .anyMatch(element -> element.getText().toLowerCase().contains(expectedKeyword.toLowerCase()));
    }

    public void clickProduct(int index) {
        if (index >= 0 && index < productItems.size()) {
            wait.until(ExpectedConditions.elementToBeClickable(productItems.get(index))).click();
        }
    }

    public String getProductName(int index) {
        if (index >= 0 && index < productNames.size()) {
            return productNames.get(index).getText();
        }
        return null;
    }

    public String getProductPrice(int index) {
        if (index >= 0 && index < productPrices.size()) {
            return productPrices.get(index).getText();
        }
        return null;
    }

    public void addToCart(int index) {
        if (index >= 0 && index < addToCartButtons.size()) {
            wait.until(ExpectedConditions.elementToBeClickable(addToCartButtons.get(index))).click();
        }
    }
} 