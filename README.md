# Selenium Java - Cucumber Framework

## Overview
This is a BDD automation framework using **Selenium - TestNG based Cucumber** for testing **Web, Mobile, and API applications**. It integrates **Appium for mobile automation** and **RestAssured for API automation** to provide a unified testing solution with **Extent Report**. Currently the site used for webapp and API testing is `Expand Testing - Practice Testing Site` and the app for mobile app testing is `ApiDemos-debug.apk`

## Features
- Supports **Web, Mobile, and API** automation
- BDD framework using **TestNG based Cucumber**
- Uses **Selenium WebDriver** for web automation
- Uses **Appium** for mobile automation
- Uses **RestAssured** for API testing
- Generates **Extent Report with Screenshots for failed cases**
- **Page Object Model (POM)** for maintainability
- **Hooks and Tags** for better test management
- **Cross-browser and cross-platform testing**
- Integerated with  **Jenkins** for Continuous Integration

## Tech Stack
- **Language**: Java
- **Web Automation**: Selenium WebDriver
- **Mobile Automation**: Appium
- **API Automation**: RestAssured
- **Test Runner**: TestNG based Cucumber
- **Build Tool**: Maven
- **Reporting**: Extent Reports, Default Cucumber HTML Reports
- **Continuous Integration**: Jenkins

## Project Structure
```
PracticeTestingSite/
│-- .idea/                         # IntelliJ project settings
│-- src/
│   ├── main/                      # Contains application-related code
│   │   ├── java/                  # Main application source code
│   │   │   ├── API/               # POJO classes for API
│   │   │   ├── MobileAppPages/    # Page Objects for Mobile App
│   │   │   ├── WebAppPages/       # Page Objects for Web App
│   │   ├── resources/             # Configuration files
│   │       ├── config/            # Configurations folder
│   │           ├── config.properties  # Contains environment URLs, credentials, etc.
│-- test/                           
│   ├── java/
│   │   ├── Hooks/                  # Cucumber Hooks class (before/after scenarios)
│   │   ├── Runner/                 # TestNG Cucumber Runner classes
│   │   │   ├── APITest.java        # Runs API tests
│   │   │   ├── MobileTest.java     # Runs Mobile App tests
│   │   │   ├── WebTest.java        # Runs Web App tests
│   │   ├── StepDefinitions/        # Step Definitions for Cucumber Scenarios
│   │   │   ├── API/                # Step definitions for API tests
│   │   │   ├── MobileApp/          # Step definitions for Mobile App tests
│   │   │   ├── WebApp/             # Step definitions for Web App tests
│   ├── resources/                  # Test-related resources
│   │   ├── Features/               # Cucumber feature files
│   │   │   ├── API/                # API feature files
│   │   │   ├── MobileApp/          # Mobile App feature files
│   │   │   ├── WebApp/             # Web App feature files
│   │   ├── extent.properties       # Extent Report configuration
│   │   ├── extent-config.xml       # Extent Report XML configuration
│-- test-output-thread/             # Parallel execution reports
│-- test-output/                    # Test output directory (TestNG reports, screenshots, logs)
│   ├── Screenshots/                # Screenshots of test failures
│   ├── SparkReport/                # Spark Extent Reports
│-- .gitignore                      # Git ignore file
│-- ApiDemos-debug.apk              # Sample APK for mobile testing
│-- README.md                       # Project documentation
│-- chromedriver.exe                 # WebDriver binary for Chrome browser
│-- pom.xml                         # Maven dependencies & build configuration

```

## Configuration
- **Web App**: Update `config.properties` with browser settings.
- **Mobile App**: Set desired capabilities in `Hooks.java` class.
- **API Testing**: Configure base URLs, authToken and other fields in `config.properties`.
- **Extent Report**: Update `extent.properties` and `extent-config.xml` for extent report configurations.

## Running Tests
### 1. Web Automation
```sh
mvn test -Dcucumber.tags="@WebApp"
```
### 2. Mobile Automation
```sh
mvn test -Dcucumber.tags="@MobileApp"
```
### 3. API Testing
```sh
mvn test -Dcucumber.tags="@API"
```

## Reports
- **Extent Report**: `test-output/SparkReport/ExtentReport.html`
- **Cucumber HTML Report**: `target/cucumber-reports/index.html`
