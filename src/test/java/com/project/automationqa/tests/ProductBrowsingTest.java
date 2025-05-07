package com.project.automationqa.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.project.automationqa.base.BaseClass;
import com.project.automationqa.pages.LoginPage;
import com.project.automationqa.pages.ProductsPage;
import com.project.automationqa.pages.CartPage;
import com.project.automationqa.utilities.Utilities;

public class ProductBrowsingTest extends BaseClass {

	@Test(priority = 1, description = "Test product browsing and filtering by Women's Dress category")
	public void testProductBrowsingAndFiltering() {
		Reporter.log("Starting testProductBrowsingAndFiltering test", true);
		ProductsPage productsPage = new ProductsPage(driver);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.navigateToLogin();
		loginPage.login(
				Utilities.getStoredData("generated.email"),
				Utilities.getStoredData("generated.password")
				);

		// Navigate to Products page
		productsPage.navigateToProducts();
		Reporter.log("Navigated to products page", true);

		// Filter products by Category → Women > Dress
		productsPage.filterByWomenDress();
		Reporter.log("Applied Women > Dress filter", true);

		// Verify filtered product list is correct
		boolean isFiltered = productsPage.verifyFilteredProducts();
		Assert.assertTrue(isFiltered, 
				"Filtered products should contain 'dress' keyword");
		Assert.assertEquals(isFiltered, true, "Test should pass if products are filtered correctly");
		Reporter.log("Verified filtered products contain 'dress' keyword", true);
	}

	@Test(priority = 2, description = "Test clicking on first product and verifying product details")
	public void testClickOnFirstProduct() {
		Reporter.log("Starting testClickOnFirstProduct test", true);
		ProductsPage productsPage = new ProductsPage(driver);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.navigateToLogin();
		loginPage.login(
				Utilities.getStoredData("generated.email"),
				Utilities.getStoredData("generated.password")
				);

		// Navigate to Products page
		productsPage.navigateToProducts();
		Reporter.log("Navigated to products page", true);

		// Click on first product and verify details
		productsPage.clickFirstProduct();
		Reporter.log("Clicked on first product", true);

		String productName = productsPage.getProductName();
		String productPrice = productsPage.getProductPrice();
		Reporter.log("Retrieved product details - Name: " + productName + ", Price: " + productPrice, true);

		boolean isNameValid = productName != null;
		boolean isPriceValid = productPrice != null;

		Assert.assertNotNull(productName, "Product name should not be null");
		Assert.assertNotNull(productPrice, "Product price should not be null");
		Assert.assertTrue(isNameValid && isPriceValid, "Test should pass if both name and price are valid");
		Reporter.log("Verified product details are valid", true);
	}

	@Test(priority = 3, description = "Test viewing detailed product information and availability")
	public void testViewProductDetails() {
		Reporter.log("Starting testViewProductDetails test", true);
		ProductsPage productsPage = new ProductsPage(driver);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.navigateToLogin();
		loginPage.login(
				Utilities.getStoredData("generated.email"),
				Utilities.getStoredData("generated.password")
				);

		// Navigate to Products page
		productsPage.navigateToProducts();
		Reporter.log("Navigated to products page", true);

		// Click view product button
		productsPage.viewProductDetails();
		Reporter.log("Clicked view product button", true);

		// Verify detailed product availability
		String detailedAvailability = productsPage.getDetailedProductAvailability();
		boolean isAvailabilityValid = detailedAvailability != null && detailedAvailability.contains("In Stock");

		Assert.assertNotNull(detailedAvailability, "Detailed product availability should not be null");
		Assert.assertTrue(detailedAvailability.contains("In Stock"), 
				"Product should be in stock in detailed view");
		Assert.assertTrue(isAvailabilityValid, "Test should pass if availability is valid");
		Reporter.log("Verified product availability in detailed view: " + detailedAvailability, true);
	}

	@Test(priority = 4, description = "Test adding product to cart with increased quantity")
	public void testAddToCartWithIncreasedQuantity() {
		Reporter.log("Starting testAddToCartWithIncreasedQuantity test", true);
		ProductsPage productsPage = new ProductsPage(driver);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.navigateToLogin();
		loginPage.login(
				Utilities.getStoredData("generated.email"),
				Utilities.getStoredData("generated.password")
				);

		// Navigate to Products page
		productsPage.navigateToProducts();
		Reporter.log("Navigated to products page", true);

		// Click on first product
		productsPage.viewProductDetails();
		Reporter.log("Clicked on first product", true);

		// Increase quantity
		productsPage.increaseQuantity(2);
		Reporter.log("Increased product quantity to 2", true);

		// Add to cart
		productsPage.addToCart();
		Reporter.log("Added product to cart", true);

		// Navigate to Products page
		productsPage.navigateToProducts();
		Reporter.log("Navigated to products page", true);

		// Click on first product
		productsPage.viewProductDetailsSecond();
		Reporter.log("Clicked on first product", true);

		// Increase quantity
		productsPage.increaseQuantity(2);
		Reporter.log("Increased product quantity to 2", true);

		// Add to cart
		productsPage.addToCart();
		Reporter.log("Added product to cart", true);

		// Verify cart has items
		CartPage cartPage = new CartPage(driver);
		cartPage.navigateToCart();
		int itemCount = cartPage.getCartItemCount();
		Assert.assertTrue(itemCount > 0, "Cart should contain items after adding");
		Reporter.log("Verified items in cart", true);
	}

	@Test(priority = 5, description = "Test adding product to cart with maximum quantity")
	public void testAddToCartWithMaximumQuantity() {
		Reporter.log("Starting testAddToCartWithMaximumQuantity test", true);
		ProductsPage productsPage = new ProductsPage(driver);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.navigateToLogin();
		loginPage.login(
				Utilities.getStoredData("generated.email"),
				Utilities.getStoredData("generated.password")
				);

		// Navigate to Products page
		productsPage.navigateToProducts();
		Reporter.log("Navigated to products page", true);

		// Click on first product
		productsPage.viewProductDetails();
		Reporter.log("Clicked on first product", true);

		// Set maximum quantity
		productsPage.increaseQuantity(10);
		Reporter.log("Set product quantity to maximum (10)", true);

		// Add to cart
		productsPage.addToCart();
		Reporter.log("Added product to cart", true);

		// Verify cart has items
		CartPage cartPage = new CartPage(driver);
		cartPage.navigateToCart();
		int itemCount = cartPage.getCartItemCount();
		Assert.assertTrue(itemCount > 0, "Cart should contain items after adding");
		Reporter.log("Verified items in cart", true);
	}

	@Test(priority = 6, description = "Test adding product to cart with minimum quantity")
	public void testAddToCartWithMinimumQuantity() {
		Reporter.log("Starting testAddToCartWithMinimumQuantity test", true);
		ProductsPage productsPage = new ProductsPage(driver);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.navigateToLogin();
		loginPage.login(
				Utilities.getStoredData("generated.email"),
				Utilities.getStoredData("generated.password")
				);

		// Navigate to Products page
		productsPage.navigateToProducts();
		Reporter.log("Navigated to products page", true);

		// Click on first product
		productsPage.viewProductDetails();
		Reporter.log("Clicked on first product", true);

		// Set minimum quantity
		productsPage.increaseQuantity(1);
		Reporter.log("Set product quantity to minimum (1)", true);

		// Add to cart
		productsPage.addToCart();
		Reporter.log("Added product to cart", true);

		// Verify cart has items
		CartPage cartPage = new CartPage(driver);
		cartPage.navigateToCart();
		int itemCount = cartPage.getCartItemCount();
		Assert.assertTrue(itemCount > 0, "Cart should contain items after adding");
		Reporter.log("Verified items in cart", true);
	}
}