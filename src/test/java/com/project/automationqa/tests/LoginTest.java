package com.project.automationqa.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.project.automationqa.base.BaseClass;
import com.project.automationqa.pages.LoginPage;
import com.project.automationqa.utilities.Utilities;

public class LoginTest extends BaseClass{
	@Test(priority = 1, description = "Test successful login")
    public void testSuccessfulLogin() {
        Reporter.log("Starting testSuccessfulLogin test", true);
        LoginPage loginPage = new LoginPage(driver);
        
        // Navigate to login page
        loginPage.navigateToLogin();
        Reporter.log("Navigated to login page", true);
        
        // Login with stored credentials
        loginPage.login(
            Utilities.getStoredData("generated.email"),
            Utilities.getStoredData("generated.password")
        );
        Reporter.log("Attempted login with stored credentials", true);
        
        // Verify login success
        boolean isLoggedIn = loginPage.isLoggedIn();
        Assert.assertTrue(isLoggedIn,
                "User should be logged in successfully");
        Assert.assertEquals(isLoggedIn, true, "Test should pass if login is successful");
        Reporter.log("Verified successful login", true);
    }

    @Test(priority = 2, description = "Test logout")
    public void testLogout() {
        Reporter.log("Starting testLogout test", true);
        LoginPage loginPage = new LoginPage(driver);
        
        // First login
        loginPage.navigateToLogin();
        loginPage.login(
            Utilities.getStoredData("generated.email"),
            Utilities.getStoredData("generated.password")
        );
        Reporter.log("Logged in with stored credentials", true);
        
        // Logout
        loginPage.logout();
        Reporter.log("Attempted logout", true);
        
        // Verify logout success
        boolean isLoggedOut = !loginPage.isLoggedIn();
        Assert.assertFalse(loginPage.isLoggedIn(),
                "User should be logged out successfully");
        Assert.assertEquals(isLoggedOut, true, "Test should pass if logout is successful");
        Reporter.log("Verified successful logout", true);
    }

    @Test(priority = 3, description = "Test login with invalid credentials")
    public void testInvalidLogin() {
        Reporter.log("Starting testInvalidLogin test", true);
        LoginPage loginPage = new LoginPage(driver);
        
        // Navigate to login page
        loginPage.navigateToLogin();
        Reporter.log("Navigated to login page", true);
        
        // Attempt login with invalid credentials
        loginPage.login("invalid@email.com", "invalidpassword");
        Reporter.log("Attempted login with invalid credentials", true);
        
        // Verify login error
        boolean isErrorVisible = loginPage.isLoginErrorVisible();
        Assert.assertTrue(isErrorVisible,
                "Login error should be displayed for invalid credentials");
        Assert.assertEquals(isErrorVisible, true, "Test should pass if error is displayed for invalid login");
        Reporter.log("Verified error message for invalid login", true);
    }

    @Test(priority = 4, description = "Test re-login with same credentials")
    public void testReLogin() {
        Reporter.log("Starting testReLogin test", true);
        LoginPage loginPage = new LoginPage(driver);
        
        // First login
        loginPage.navigateToLogin();
        loginPage.login(
            Utilities.getStoredData("generated.email"),
            Utilities.getStoredData("generated.password")
        );
        Reporter.log("Completed first login", true);
        
        // Logout
        loginPage.logout();
        Reporter.log("Completed logout", true);
        
        // Re-login
        loginPage.navigateToLogin();
        loginPage.login(
            Utilities.getStoredData("generated.email"),
            Utilities.getStoredData("generated.password")
        );
        Reporter.log("Attempted re-login", true);
        
        // Verify re-login success
        boolean isReLoggedIn = loginPage.isLoggedIn();
        Assert.assertTrue(isReLoggedIn,
                "User should be able to re-login with same credentials");
        Assert.assertEquals(isReLoggedIn, true, "Test should pass if re-login is successful");
        Reporter.log("Verified successful re-login", true);
    }

    @Test(priority = 5, description = "Test Logout And Re-Login")
    public void testLogoutAndRelogin() {
        Reporter.log("Starting testLogoutAndRelogin test", true);
        LoginPage loginPage = new LoginPage(driver);
        
        // Initial login
        loginPage.navigateToLogin();
        loginPage.login(
                Utilities.getStoredData("generated.email"),
                Utilities.getStoredData("generated.password")
            );
        Reporter.log("Completed initial login", true);
        
        // Store logged in user info
        String initialUser = loginPage.getLoggedInUser();
        Reporter.log("Stored initial user info: " + initialUser, true);
        
        // Logout
        loginPage.logout();
        Reporter.log("Completed logout", true);
        
        // Re-login with same credentials
        loginPage.navigateToLogin();
        loginPage.login(
                Utilities.getStoredData("generated.email"),
                Utilities.getStoredData("generated.password")
            );
        Reporter.log("Completed re-login", true);
        
        // Verify user state is preserved
        String finalUser = loginPage.getLoggedInUser();
        boolean isUserStatePreserved = finalUser.equals(initialUser);
        Assert.assertEquals(finalUser, initialUser, 
            "User state should be preserved after logout and re-login");
        Assert.assertTrue(isUserStatePreserved, "Test should pass if user state is preserved");
        Reporter.log("Verified user state preservation - Final user: " + finalUser, true);
    }
}
