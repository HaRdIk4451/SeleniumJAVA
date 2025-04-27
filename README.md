# Automation Exercise Test Project

This project automates the testing of the Automation Exercise website (https://automationexercise.com) using Selenium WebDriver and TestNG. The project follows best practices in test automation and implements a robust Page Object Model design pattern.

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

- **Page Object Model (POM) Implementation**
  - Clean separation of page elements and actions
  - Reusable page components
  - Maintainable and scalable test structure

- **Test Automation Features**
  - Screenshot capture on test failure
  - Custom wait conditions
  - Dynamic element handling
  - Robust error handling
  - Session management
  - Data-driven testing capabilities

- **Configuration Management**
  - Externalized configuration
  - Environment-specific settings
  - Secure credential management

## Test Scenarios

### 1. User Registration & Session Handling
- Register new user with random details
- Verify email uniqueness
- Verify login after registration
- Store and reuse session data
- Handle registration edge cases

### 2. Product Browsing & Filtering
- Navigate to Products page
- Filter by Category (Women > Dress)
- Verify filtered products
- Verify product details
- Test product search functionality
- Validate product sorting options

### 3. Cart Management
- Add multiple items to cart
- Modify quantities
- Remove items
- Verify cart totals
- Test cart persistence
- Validate cart updates

### 4. Checkout Process
- Proceed to checkout
- Enter shipping details
- Enter payment information
- Verify order confirmation
- Test order history
- Validate order details

### 5. Login/Logout
- Login with valid credentials
- Login with invalid credentials
- Logout functionality
- Re-login with same credentials
- Session timeout handling
- Password recovery testing

## Setup Instructions

1. **Prerequisites**
   - Java 11 or higher
   - Maven 3.6.0 or higher
   - Chrome/Firefox/Edge browser
   - Git

2. **Installation**
   ```bash
   # Clone the repository
   git clone [repository-url]
   
   # Navigate to project directory
   cd automation-exercise-test
   
   # Install dependencies
   mvn clean install
   ```

3. **Configuration**
   - Update `config.properties` with your preferences:
     - Browser selection
     - Base URL
     - Timeout settings
   - Update `secrets.properties` with test credentials

## Running Tests

```bash
# Run all tests
mvn clean test

# Run specific test suite
mvn test -Dtest=LoginTest

# Run with specific browser
mvn test -Dbrowser=chrome
```

## Dependencies

- Selenium WebDriver
- TestNG
- WebDriverManager
- Apache Commons
- Log4j

## Best Practices Implemented

- Page Object Model design pattern
- Reusable utility methods
- Custom assertions
- Screenshot capture on failure
- Detailed logging
- Clean test data management
- Robust error handling

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details. 