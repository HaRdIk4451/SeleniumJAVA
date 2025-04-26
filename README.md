# Automation Exercise Test Project

This project automates the testing of the Automation Exercise website (https://automationexercise.com) using Selenium WebDriver and TestNG.

## Project Structure

```
src/
├── main/
│   └── java/
│       └── com/
│           └── project/
│               └── automationexercise/
│                   ├── base/
│                   │   └── BaseClass.java
│                   ├── pages/
│                   │   ├── RegisterPage.java
│                   │   ├── LoginPage.java
│                   │   ├── ProductPage.java
│                   │   ├── CartPage.java
│                   │   └── CheckoutPage.java
│                   └── utilities/
│                       └── Utilities.java
└── test/
    └── java/
        └── com/
            └── project/
                └── automationexercise/
                    └── tests/
                        ├── RegisterTest.java
                        ├── LoginTest.java
                        ├── ProductTest.java
                        ├── CartTest.java
                        └── CheckoutTest.java
```

## Features

- Page Object Model (POM) implementation
- Reusable custom commands
- Screenshot comparison
- API response handling
- Data-driven testing
- Negative test scenarios
- Session management
- Error handling

## Test Scenarios

1. User Registration & Session Handling
   - Register new user with random details
   - Verify email uniqueness
   - Verify login after registration
   - Store and reuse session data

2. Product Browsing & Filtering
   - Navigate to Products page
   - Filter by Category (Women > Dress)
   - Verify filtered products
   - Verify product details

3. Cart Management
   - Add multiple items
   - Modify quantities
   - Remove items
   - Verify cart totals

4. Checkout Process
   - Proceed to checkout
   - Enter shipping details
   - Enter payment information
   - Verify order confirmation

5. Login/Logout
   - Login with valid credentials
   - Login with invalid credentials
   - Logout
   - Re-login with same credentials

## Setup

1. Clone the repository
2. Install Java 11 or higher
3. Install Maven
4. Install required browsers (Chrome, Firefox, Edge)
5. Update `config.properties` with your preferences

## Running Tests

```bash
mvn clean test
```

## Configuration

- Browser selection: `config.properties`
- Base URL: `config.properties`
- Test data: `secrets.properties`

## Dependencies

- Selenium WebDriver
- TestNG
- JavaFaker
- JSON

## Reports

Test reports are generated in the `test-output` directory.

## Screenshots

Screenshots are stored in `test-output/screenshots/` for visual comparison. 