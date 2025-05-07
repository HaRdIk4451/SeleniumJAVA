package com.project.automationqa.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.project.automationqa.base.BaseClass;
import com.project.automationqa.pages.CartPage;
import com.project.automationqa.pages.CheckoutPage;
import com.project.automationqa.pages.LoginPage;
import com.project.automationqa.pages.ProductsPage;
import com.project.automationqa.utilities.Utilities;

public class CheckoutTest extends BaseClass {

	@Test(priority = 1, description = "Test adding items and proceeding to checkout")
	public void testProceedToCheckout() {
		Reporter.log("Starting testProceedToCheckout test", true);
		ProductsPage productsPage = new ProductsPage(driver);
		CartPage cartPage = new CartPage(driver);
		LoginPage loginPage = new LoginPage(driver);

		// Login first
		loginPage.navigateToLogin();
		loginPage.login(
				Utilities.getStoredData("generated.email"),
				Utilities.getStoredData("generated.password")
				);
		Reporter.log("Logged in successfully", true);

		// Add items to cart
		productsPage.navigateToProducts();
		productsPage.filterByWomenDress();
		productsPage.clickAddToCardViewPage();
		Reporter.log("Added items to cart", true);

		// Proceed to checkout
		cartPage.navigateToCart();
		boolean isCheckoutButtonVisible = cartPage.isProceedToCheckoutButtonVisible();

		Assert.assertTrue(isCheckoutButtonVisible, "Proceed to checkout button should be visible");
		Assert.assertEquals(isCheckoutButtonVisible, true, "Test should pass if checkout button is visible");
		Reporter.log("Verified proceed to checkout button is visible", true);
	}

	@Test(priority = 2, description = "Test checkout page navigation and form display")
	public void testCheckoutPageNavigation() {
		Reporter.log("Starting testCheckoutPageNavigation test", true);
		CartPage cartPage = new CartPage(driver);
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		LoginPage loginPage = new LoginPage(driver);

		// Login first
		loginPage.navigateToLogin();
		loginPage.login(
				Utilities.getStoredData("generated.email"),
				Utilities.getStoredData("generated.password")
				);
		Reporter.log("Logged in successfully", true);

		// Navigate to checkout
		cartPage.navigateToCart();
		checkoutPage.proceedToCheckout();
		Reporter.log("Navigated to checkout page", true);

		// Verify checkout form elements
		boolean isBillingFormVisible = checkoutPage.isBillingFormVisible();
		boolean isOrderSummaryVisible = checkoutPage.isOrderSummaryVisible();
		Assert.assertTrue(isBillingFormVisible, "Billing form should be visible");
		Assert.assertTrue(isOrderSummaryVisible, "Order summary should be visible");
		Reporter.log("Verified all checkout form elements are visible", true);

		// Place the order to go to payment page
		checkoutPage.clickPlaceOrder();
		Reporter.log("Clicked Place Order button", true);
	}

	@Test(priority = 3, description = "Test payment details entry and order confirmation")
	public void testPaymentDetailsEntryAndOrderConfirmation() {
		Reporter.log("Starting testPaymentDetailsEntryAndOrderConfirmation test", true);
		CartPage cartPage = new CartPage(driver);
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		LoginPage loginPage = new LoginPage(driver);

		// Login first
		loginPage.navigateToLogin();
		loginPage.login(
				Utilities.getStoredData("generated.email"),
				Utilities.getStoredData("generated.password")
				);
		Reporter.log("Logged in successfully", true);
		
		cartPage.navigateToCart();
		checkoutPage.proceedToCheckout();
		checkoutPage.clickPlaceOrder();

		// Enter payment details
		checkoutPage.enterPaymentDetails(
				"Test User",
				"4111111111111111",
				"123",
				"12",
				"2025"
				);
		Reporter.log("Entered payment details", true);

		// Submit payment
		checkoutPage.submitPayment();
		Reporter.log("Submitted payment", true);

		// Verify order confirmation
		String confirmationMessage = checkoutPage.getOrderConfirmation();
		boolean isOrderConfirmed = confirmationMessage != null && confirmationMessage.toLowerCase().contains("congratulations");
		Assert.assertNotNull(confirmationMessage, "Order confirmation message should be displayed");
		Assert.assertTrue(isOrderConfirmed, "Order should be confirmed successfully");
		Reporter.log("Verified order confirmation: " + confirmationMessage, true);
	}
} 