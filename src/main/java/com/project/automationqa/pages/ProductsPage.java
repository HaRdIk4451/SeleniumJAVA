package com.project.automationqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//a[@href='/products']")
    private WebElement productsLink;

    @FindBy(xpath = "//h2[normalize-space()='Category']")
    private WebElement categoryTitle;

    @FindBy(xpath = "//a[@href='#Women']")
    private WebElement womenCategory;

    @FindBy(xpath = "//a[@href='/category_products/1']")
    private WebElement dressSubcategory;

    @FindBy(xpath = "//div[@class='features_items']//div[@class='col-sm-4']")
    private List<WebElement> productItems;
    
    @FindBy(xpath = "//div[@class='features_items']//div[@class='col-sm-4']//a")
    private WebElement initialAddToCart;

    @FindBy(xpath = "//div[@class='productinfo text-center']//p")
    private WebElement productName;

    @FindBy(xpath = "//div[@class='productinfo text-center']//h2")
    private WebElement productPrice;

    @FindBy(xpath = "//div[@class='col-sm-4'][1]//div[@class='choose']")
    private WebElement viewProductButton;
    
    @FindBy(xpath = "//div[@class='col-sm-4'][2]//div[@class='choose']")
    private WebElement viewProductButton2;

    @FindBy(xpath = "//p[contains(.,'Availability: In Stock')]")
    private WebElement detailedProductAvailability;

    @FindBy(xpath = "//input[@id='quantity']")
    private WebElement quantityInput;

    @FindBy(xpath = "//button[@class='btn btn-default cart']")
    private WebElement addToCartButton;

    public ProductsPage(WebDriver driver) {
        this.setDriver(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void navigateToProducts() {
        productsLink.click();
    }

    public void filterByWomenDress() {
        womenCategory.click();
        wait.until(ExpectedConditions.elementToBeClickable(dressSubcategory)).click();
    }

    public boolean verifyFilteredProducts() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productItems));
        return productItems.stream()
                .anyMatch(item -> item.getText().toLowerCase().contains("dress"));
    }

    public void clickFirstProduct() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productItems));
        productItems.get(0).click();
    }
    
    public void clickAddToCardViewPage() {
    		wait.until(ExpectedConditions.visibilityOf(initialAddToCart)).click();
    }

    public String getProductName() {
        return wait.until(ExpectedConditions.visibilityOf(productName)).getText();
    }

    public String getProductPrice() {
        return wait.until(ExpectedConditions.visibilityOf(productPrice)).getText();
    }

    public void viewProductDetails() {
        wait.until(ExpectedConditions.elementToBeClickable(viewProductButton)).click();
    }
    
    public void viewProductDetailsSecond() {
        wait.until(ExpectedConditions.elementToBeClickable(viewProductButton2)).click();
    }

    public String getDetailedProductAvailability() {
        return wait.until(ExpectedConditions.visibilityOf(detailedProductAvailability)).getText();
    }

    public void increaseQuantity(int quantity) {
        wait.until(ExpectedConditions.visibilityOf(quantityInput)).clear();
        quantityInput.sendKeys(String.valueOf(quantity));
    }

    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
} 