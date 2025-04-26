package com.project.automationexercise.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.project.automationexercise.base.BaseClass;
import com.project.automationexercise.pages.CartPage;
import com.project.automationexercise.pages.CheckoutPage;
import com.project.automationexercise.pages.LoginPage;
import com.project.automationexercise.pages.ProductPage;
import com.project.automationexercise.utilities.Utilities;

public class CheckoutTest extends BaseClass {

    @Test(priority = 1, description = "Test complete checkout flow")
    public void testCompleteCheckout() {
        // Login first
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLogin();
        loginPage.login(
            Utilities.getStoredData("generated.email"),
            Utilities.getStoredData("generated.password")
        );

        // Add product to cart
        ProductPage productPage = new ProductPage(driver);
        productPage.navigateToProducts();
        productPage.addToCart(0);

        // Navigate to cart
        CartPage cartPage = new CartPage(driver);
        cartPage.navigateToCart();

        // Proceed to checkout
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.proceedToCheckout();

        // Add order comment
        checkoutPage.addOrderComment("Test order comment");

        // Place order
        checkoutPage.placeOrder();

        // Enter payment details
        checkoutPage.enterPaymentDetails(
            "Test User",
            "4111111111111111",
            "123",
            "12",
            "2025"
        );

        // Confirm payment
        checkoutPage.confirmPayment();

        // Verify order success
        Assert.assertTrue(checkoutPage.verifyOrderSuccess(),
                "Order should be placed successfully");
    }

    @Test(priority = 2, description = "Test checkout with empty cart")
    public void testCheckoutWithEmptyCart() {
        // Login first
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLogin();
        loginPage.login(
            Utilities.getStoredData("generated.email"),
            Utilities.getStoredData("generated.password")
        );

        // Navigate to cart
        CartPage cartPage = new CartPage(driver);
        cartPage.navigateToCart();

        // Verify cart is empty
        Assert.assertTrue(cartPage.verifyCartEmpty(),
                "Cart should be empty before checkout");
    }
} 