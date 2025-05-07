# Automation QA Project

## Overview
This project is a Selenium-based automation framework using Java and TestNG for testing an e-commerce web application. It follows the Page Object Model (POM) design pattern for maintainability and scalability.

## Prerequisites
- **Java JDK 11 or higher**
- **Eclipse IDE for Java Developers**
- **Maven** (recommended for dependency management)
- **Google Chrome, Firefox, or Edge browser**

## Setup Instructions

### 1. Install Java
- Download and install the [Java JDK](https://adoptopenjdk.net/) (version 11 or higher).
- Set the `JAVA_HOME` environment variable and add `JAVA_HOME/bin` to your `PATH`.
- Verify installation:
  ```sh
  java -version
  ```

### 2. Install Eclipse
- Download and install [Eclipse IDE for Java Developers](https://www.eclipse.org/downloads/).
- Launch Eclipse and select a workspace directory.

### 3. Clone the Project
- Clone this repository or download the source code.
- In Eclipse, go to `File > Import > Existing Maven Projects` and select the project directory.

### 4. Install Maven (if not already installed)
- Download and install [Maven](https://maven.apache.org/download.cgi).
- Add Maven to your `PATH`.
- Verify installation:
  ```sh
  mvn -version
  ```

### 5. Project Dependencies
- All dependencies (Selenium, TestNG, WebDriverManager, Java Faker, etc.) are managed in `pom.xml`.
- Eclipse will automatically download dependencies if the project is imported as a Maven project.
- If not, right-click the project and select `Maven > Update Project`.

### 6. Install TestNG Plugin in Eclipse
- Go to `Help > Eclipse Marketplace`.
- Search for **TestNG** and install the **TestNG for Eclipse** plugin.
- Restart Eclipse if prompted.

### 7. Configure Browsers
- The framework supports Chrome, Firefox, and Edge.
- WebDriverManager is used to automatically manage browser drivers.
- Make sure the desired browser is installed on your system.

### 8. Running the Tests
- Open `testng.xml` in Eclipse.
- Right-click and select `Run As > TestNG Suite`.
- Or, right-click any test class and select `Run As > TestNG Test`.
- Test results will be shown in the TestNG tab and reports will be generated in the `test-output` directory.

### 9. Project Structure
```
com.project.automationqa
│
├── base/         # Base test setup (WebDriver, config)
├── pages/        # Page Object Model classes
├── tests/        # TestNG test classes
├── utilities/    # Utility/helper classes
├── resources/    # Config and test data files
└── pom.xml       # Maven dependencies
```

### 10. Customization
- Update `src/main/resources/config.properties` for environment-specific settings.
- Test data can be managed in `secrets.properties` or via utility methods.

### 11. Troubleshooting
- **ElementClickInterceptedException**: If a modal or popup blocks an element, ensure your test closes it before proceeding.
- **Browser/Driver Issues**: Make sure your browser is up to date. WebDriverManager should handle driver versions automatically.
- **Dependency Issues**: Run `Maven > Update Project` or `mvn clean install` to resolve.

### 12. Useful Links
- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [TestNG Documentation](https://testng.org/doc/)
- [WebDriverManager](https://github.com/bonigarcia/webdrivermanager)
- [Eclipse IDE](https://www.eclipse.org/)
- [Maven](https://maven.apache.org/)

## File System Structure

The project directory is organized as follows:

```
com.project.automationqa/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── project/
│   │               └── automationqa/
│   │                   ├── base/         # Base test setup (WebDriver, config)
│   │                   ├── pages/        # Page Object Model classes
│   │                   ├── utilities/    # Utility/helper classes
│   ├── test/
│   │   └── java/
│   │       └── com/
│   │           └── project/
│   │               └── automationqa/
│   │                   └── tests/        # TestNG test classes
│   └── resources/        # Config and test data files
├── pom.xml               # Maven dependencies
├── testng.xml            # TestNG suite configuration
└── README.md             # Project documentation
```

## How to Run the Project

### Running from Eclipse
1. **Import the project** as a Maven project (see setup instructions above).
2. **Build the project**: Right-click the project > `Run As > Maven install` (optional, for a clean build).
3. **Run all tests**:
   - Open `testng.xml` in Eclipse.
   - Right-click and select `Run As > TestNG Suite`.
   - Or, right-click any test class and select `Run As > TestNG Test` to run individual tests.
4. **View results**: Test results will appear in the TestNG tab and reports will be generated in the `test-output` directory.

### Running from the Command Line (Maven)
1. Open a terminal/command prompt and navigate to the project root directory (where `pom.xml` is located).
2. Run all tests using Maven:
   ```sh
   mvn clean test
   ```
3. To run a specific test class:
   ```sh
   mvn -Dtest=FullyQualifiedTestClassName test
   # Example:
   mvn -Dtest=com.project.automationqa.tests.CheckoutTest test
   ```
4. **View results**: TestNG HTML reports will be generated in the `test-output` directory.

---

## Author
Automation QA Project Team 