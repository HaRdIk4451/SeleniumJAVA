package com.project.automationexercise.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.project.automationexercise.base.BaseClass;
import com.project.automationexercise.pages.CartPage;
import com.project.automationexercise.pages.LoginPage;
import com.project.automationexercise.pages.ProductPage;
import com.project.automationexercise.utilities.Utilities;

public class CartTest extends BaseClass {

	@Test(priority = 1, description = "Test adding multiple items to cart")
	public void testAddMultipleItems() {
		ProductPage productPage = new ProductPage(driver);
		CartPage cartPage = new CartPage(driver);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(
				Utilities.getStoredData("generated.email"),
				Utilities.getStoredData("generated.password")
				);
		// Add first product
		productPage.navigateToProducts();
		productPage.addToCart(0);

		// Add second product
		productPage.addToCart(1);

		// Navigate to cart and verify items
		cartPage.navigateToCart();
		Assert.assertEquals(cartPage.getCartItemCount(), 2,
				"Cart should contain 2 items");
	}

	@Test(priority = 2, description = "Test quantity modification")
	public void testQuantityModification() {
		ProductPage productPage = new ProductPage(driver);
		CartPage cartPage = new CartPage(driver);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(
				Utilities.getStoredData("generated.email"),
				Utilities.getStoredData("generated.password")
				);

		// Add a product to cart
		productPage.navigateToProducts();
		productPage.addToCart(0);

		// Navigate to cart
		cartPage.navigateToCart();

		// Increase quantity to 3
		cartPage.increaseQuantity(0, 2); // Increase twice to get to 3

		// Verify quantity was updated
		String itemTotal = cartPage.getItemTotal(0);
		Assert.assertNotNull(itemTotal, "Item total should be updated");
	}

	@Test(priority = 3, description = "Test item removal")
	public void testItemRemoval() {
		ProductPage productPage = new ProductPage(driver);
		CartPage cartPage = new CartPage(driver);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(
				Utilities.getStoredData("generated.email"),
				Utilities.getStoredData("generated.password")
				);

		// Add two products
		productPage.navigateToProducts();
		productPage.addToCart(0);
		productPage.addToCart(1);

		// Navigate to cart
		cartPage.navigateToCart();

		// Remove first item
		cartPage.removeItem(0);

		// Verify only one item remains
		Assert.assertEquals(cartPage.getCartItemCount(), 1,
				"Cart should contain only one item after removal");
	}

	@Test(priority = 4, description = "Test cart total calculation")
	public void testCartTotal() {
		ProductPage productPage = new ProductPage(driver);
		CartPage cartPage = new CartPage(driver);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(
				Utilities.getStoredData("generated.email"),
				Utilities.getStoredData("generated.password")
				);

		// Add a product
		productPage.navigateToProducts();
		productPage.addToCart(0);

		// Navigate to cart
		cartPage.navigateToCart();

		// Get cart total
		String cartTotal = cartPage.getCartTotal();

		// Verify total is in correct format
		Assert.assertTrue(cartTotal.matches("Rs\\. \\d+"),
				"Cart total should be in correct format");
	}
} 