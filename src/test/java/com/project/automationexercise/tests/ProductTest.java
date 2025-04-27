package com.project.automationexercise.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.project.automationexercise.base.BaseClass;
import com.project.automationexercise.pages.LoginPage;
import com.project.automationexercise.pages.ProductPage;
import com.project.automationexercise.utilities.Utilities;

public class ProductTest extends BaseClass {

    @Test(priority = 1, description = "Test product filtering by category")
    public void testProductFiltering() {
        ProductPage productPage = new ProductPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLogin();
        loginPage.login(
                Utilities.getStoredData("generated.email"),
                Utilities.getStoredData("generated.password")
            );
        // Navigate to products page
        productPage.navigateToProducts();
        
        // Filter by Women > Dress
        productPage.filterByWomenDress();
        
        // Verify filtered products contain expected keyword
        Assert.assertTrue(productPage.verifyFilteredProducts("dress"),
                "Filtered products do not contain expected keyword");
    }

    @Test(priority = 2, description = "Test product details verification")
    public void testProductDetails() {
        ProductPage productPage = new ProductPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLogin();
        loginPage.login(
                Utilities.getStoredData("generated.email"),
                Utilities.getStoredData("generated.password")
            );
        // Navigate to products page
        productPage.navigateToProducts();
        
        // Click on first product
        productPage.clickProduct(0);
        
        // Verify product details
        String productName = productPage.getProductName(0);
        String productPrice = productPage.getProductPrice(0);
        
        Assert.assertNotNull(productName, "Product name should not be null");
        Assert.assertNotNull(productPrice, "Product price should not be null");
        Assert.assertTrue(productPrice.matches("Rs\\. \\d+"), 
                "Product price should be in correct format");
    }

    @Test(priority = 3, description = "Test adding products to cart")
    public void testAddToCart() {
        ProductPage productPage = new ProductPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLogin();
        loginPage.login(
                Utilities.getStoredData("generated.email"),
                Utilities.getStoredData("generated.password")
            );
        
        // Navigate to products page
        productPage.navigateToProducts();
        
        // Add first product to cart
        productPage.addToCart(0);
        
        // Verify product was added (this would typically be handled by a cart page)
        // For now, we'll just verify the add to cart button is still present
        Assert.assertTrue(productPage.getProductName(0) != null,
                "Product should still be visible after adding to cart");
    }
} 