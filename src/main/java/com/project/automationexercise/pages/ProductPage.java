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
    private WebElement productsButton;

    @FindBy(xpath = "(//div[@class='choose'])[1]")
    private List<WebElement> items;

    @FindBy(xpath = "(//div[@class='productinfo text-center'])[1]/p")
    private List<WebElement> names;

    @FindBy(xpath = "(//div[@class='productinfo text-center'])[1]/h2")
    private List<WebElement> prices;

    @FindBy(xpath = "//a[@href='#Women']")
    private WebElement womenCategoryButton;

    @FindBy(xpath = "//a[contains(@href,'/category_products/1')]")
    private WebElement womenDressSubcategoryButton;

    @FindBy(xpath = "//a[@data-product-id=3]")
    private List<WebElement> addToCartButtons;

    public ProductPage(WebDriver driver) {
        this.setDriver(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void navigateToProducts() {
        wait.until(ExpectedConditions.elementToBeClickable(productsButton)).click();
    }

    public void filterByWomenDress() {
        wait.until(ExpectedConditions.elementToBeClickable(womenCategoryButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(womenDressSubcategoryButton)).click();
    }

    public boolean verifyFilteredProducts(String expectedKeyword) {
        wait.until(ExpectedConditions.visibilityOfAllElements(names));
        return names.stream()
                .anyMatch(element -> element.getText().toLowerCase().contains(expectedKeyword.toLowerCase()));
    }

    public void clickProduct(int index) {
        if (index >= 0 && index < items.size()) {
            wait.until(ExpectedConditions.elementToBeClickable(items.get(index))).click();
        }
    }

    public String getProductName(int index) {
        if (index >= 0 && index < names.size()) {
            return names.get(index).getText();
        }
        return null;
    }

    public String getProductPrice(int index) {
        if (index >= 0 && index < prices.size()) {
            return prices.get(index).getText();
        }
        return null;
    }

    public void addToCart(int index) {
        if (index >= 0 && index < addToCartButtons.size()) {
            wait.until(ExpectedConditions.elementToBeClickable(addToCartButtons.get(index))).click();
        }
    }

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
} 