package com.project.automationqa.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.project.automationqa.base.BaseClass;
import com.project.automationqa.pages.CartPage;
import com.project.automationqa.pages.LoginPage;
import com.project.automationqa.pages.ProductsPage;
import com.project.automationqa.utilities.Utilities;

public class CartManagementTest extends BaseClass {

	@Test(priority = 1, description = "Test adding items to cart")
	public void testAddItemsToCart() {
		Reporter.log("Starting testAddItemsToCart test", true);
		ProductsPage productsPage = new ProductsPage(driver);
		LoginPage loginPage = new LoginPage(driver);

		// Login first
		loginPage.navigateToLogin();
		loginPage.login(
				Utilities.getStoredData("generated.email"),
				Utilities.getStoredData("generated.password")
				);
		Reporter.log("Logged in successfully", true);

		// Navigate to products and add items
		productsPage.navigateToProducts();
		productsPage.filterByWomenDress();
		productsPage.clickAddToCardViewPage();
		Reporter.log("Added items to cart", true);

		// Verify items were added
		CartPage cartPage = new CartPage(driver);
		cartPage.navigateToCart();
		int itemCount = cartPage.getCartItemCount();

		Assert.assertTrue(itemCount > 0, "Cart should contain items after adding");
		Assert.assertEquals(itemCount > 0, true, "Test should pass if items are added to cart");
		Reporter.log("Verified items were added to cart", true);
	}

	@Test(priority = 2, description = "Test cart item count and total calculation")
	public void testCartItemCountAndTotal() {
		LoginPage loginPage = new LoginPage(driver);

		// Login first
		loginPage.navigateToLogin();
		loginPage.login(
				Utilities.getStoredData("generated.email"),
				Utilities.getStoredData("generated.password")
				);
		Reporter.log("Logged in successfully", true);
		Reporter.log("Starting testCartItemCountAndTotal test", true);
		CartPage cartPage = new CartPage(driver);
		cartPage.navigateToCart();

		// Get initial cart state
		int initialItemCount = cartPage.getCartItemCount();
		String initialTotal = cartPage.getCartTotal();
		Reporter.log("Initial cart state - Items: " + initialItemCount + ", Total: " + initialTotal, true);

		// Verify cart state is valid
		boolean isItemCountValid = initialItemCount > 0;
		boolean isTotalValid = initialTotal != null && !initialTotal.isEmpty();
		boolean isCartStateValid = isItemCountValid && isTotalValid;

		Assert.assertTrue(isItemCountValid, "Cart should have items");
		Assert.assertTrue(isTotalValid, "Cart total should be calculated");
		Assert.assertTrue(isCartStateValid, "Test should pass if cart state is valid");
		Reporter.log("Verified cart state is valid", true);
	}

	@Test(priority = 4, description = "Test removing items from cart")
	public void testRemoveItemsFromCart() {
		Reporter.log("Starting testRemoveItemsFromCart test", true);
		CartPage cartPage = new CartPage(driver);
		LoginPage loginPage = new LoginPage(driver);

		// Login first
		loginPage.navigateToLogin();
		loginPage.login(
				Utilities.getStoredData("generated.email"),
				Utilities.getStoredData("generated.password")
				);
		Reporter.log("Logged in successfully", true);
		
		cartPage.navigateToCart();

		// Get initial cart state
		int initialItemCount = cartPage.getCartItemCount();
		String initialTotal = cartPage.getCartTotal();
		Reporter.log("Initial cart state - Items: " + initialItemCount + ", Total: " + initialTotal, true);

		// Remove one product
		cartPage.removeFirstItem();
		Reporter.log("Removed first item from cart", true);

		// Verify cart updates
		int finalItemCount = cartPage.getCartItemCount();
		String finalTotal = cartPage.getCartTotal();
		Reporter.log("Final cart state - Items: " + finalItemCount + ", Total: " + finalTotal, true);

		boolean isItemCountValid = finalItemCount == initialItemCount - 1;
		boolean isTotalChanged = !finalTotal.equals(initialTotal);
		boolean isTestPassed = isItemCountValid && isTotalChanged;

		Assert.assertEquals(finalItemCount, initialItemCount - 1, 
				"Cart item count should decrease by 1 after removal");
		Assert.assertNotEquals(finalTotal, initialTotal, 
				"Cart total should change after item removal");
		Assert.assertTrue(isTestPassed, "Test should pass if both item count and total are updated correctly");
		Reporter.log("Verified cart updates after item removal", true);
		
		// No Decreasing Option from cart was there
	}

	@Test(priority = 3, description = "Test cart persistence after logout")
	public void testCartPersistence() {
		Reporter.log("Starting testCartPersistence test", true);
		CartPage cartPage = new CartPage(driver);
		LoginPage loginPage = new LoginPage(driver);

		// Login first
		loginPage.navigateToLogin();
		loginPage.login(
				Utilities.getStoredData("generated.email"),
				Utilities.getStoredData("generated.password")
				);
		Reporter.log("Logged in successfully", true);
		
		cartPage.navigateToCart();

		// Get cart state before logout
		int itemCountBeforeLogout = cartPage.getCartItemCount();
		String totalBeforeLogout = cartPage.getCartTotal();
		Reporter.log("Cart state before logout - Items: " + itemCountBeforeLogout + ", Total: " + totalBeforeLogout, true);

		// Logout
		loginPage.logout();
		Reporter.log("Logged out", true);

		// Login again
		loginPage.navigateToLogin();
		loginPage.login(
				Utilities.getStoredData("generated.email"),
				Utilities.getStoredData("generated.password")
				);
		Reporter.log("Logged in again", true);

		// Get cart state after login
		cartPage.navigateToCart();
		int itemCountAfterLogin = cartPage.getCartItemCount();
		String totalAfterLogin = cartPage.getCartTotal();
		Reporter.log("Cart state after login - Items: " + itemCountAfterLogin + ", Total: " + totalAfterLogin, true);

		// Verify cart state is preserved
		boolean isItemCountPreserved = itemCountAfterLogin == itemCountBeforeLogout;
		boolean isTotalPreserved = totalAfterLogin.equals(totalBeforeLogout);
		boolean isCartPreserved = isItemCountPreserved && isTotalPreserved;

		Assert.assertEquals(itemCountAfterLogin, itemCountBeforeLogout, 
				"Cart item count should be preserved after logout and login");
		Assert.assertEquals(totalAfterLogin, totalBeforeLogout, 
				"Cart total should be preserved after logout and login");
		Assert.assertTrue(isCartPreserved, "Test should pass if cart state is preserved");
		Reporter.log("Verified cart state is preserved after logout and login", true);
	}
} 