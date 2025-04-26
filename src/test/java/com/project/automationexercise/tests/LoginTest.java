package com.project.automationexercise.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.project.automationexercise.base.BaseClass;
import com.project.automationexercise.pages.LoginPage;
import com.project.automationexercise.utilities.Utilities;

public class LoginTest extends BaseClass {

    @Test(priority = 1, description = "Test successful login")
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        
        // Navigate to login page
        loginPage.navigateToLogin();
        
        // Login with stored credentials
        loginPage.login(
            Utilities.getStoredData("generated.email"),
            Utilities.getStoredData("generated.password")
        );
        
        // Verify login success
        Assert.assertTrue(loginPage.isLoggedIn(),
                "User should be logged in successfully");
    }

    @Test(priority = 2, description = "Test logout")
    public void testLogout() {
        LoginPage loginPage = new LoginPage(driver);
        
        // First login
        loginPage.navigateToLogin();
        loginPage.login(
            Utilities.getStoredData("generated.email"),
            Utilities.getStoredData("generated.password")
        );
        
        // Logout
        loginPage.logout();
        
        // Verify logout success
        Assert.assertFalse(loginPage.isLoggedIn(),
                "User should be logged out successfully");
    }

    @Test(priority = 3, description = "Test login with invalid credentials")
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        
        // Navigate to login page
        loginPage.navigateToLogin();
        
        // Attempt login with invalid credentials
        loginPage.login("invalid@email.com", "invalidpassword");
        
        // Verify login error
        Assert.assertTrue(loginPage.isLoginErrorVisible(),
                "Login error should be displayed for invalid credentials");
    }

    @Test(priority = 4, description = "Test re-login with same credentials")
    public void testReLogin() {
        LoginPage loginPage = new LoginPage(driver);
        
        // First login
        loginPage.navigateToLogin();
        loginPage.login(
            Utilities.getStoredData("generated.email"),
            Utilities.getStoredData("generated.password")
        );
        
        // Logout
        loginPage.logout();
        
        // Re-login
        loginPage.navigateToLogin();
        loginPage.login(
            Utilities.getStoredData("generated.email"),
            Utilities.getStoredData("generated.password")
        );
        
        // Verify re-login success
        Assert.assertTrue(loginPage.isLoggedIn(),
                "User should be able to re-login with same credentials");
    }
} 