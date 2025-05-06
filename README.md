# Selenium Java - Cucumber Framework

## Overview
This is a BDD automation framework using **Selenium - TestNG based Cucumber** for testing **Web, Mobile, and API applications**. It integrates **Appium for Mobile automation** and **RestAssured for API automation** to provide a unified testing solution with **Allure Report**. Currently the site used for webapp and API testing is `Expand Testing - Practice Testing Site` and the app for mobile app testing is `ApiDemos-debug.apk`

## Features
- Supports **Web, Mobile, and API** automation
- BDD framework using **TestNG based Cucumber**
- Uses **Selenium WebDriver** for web automation
- Uses **Appium** for mobile automation
- Uses **RestAssured** for API testing
- Generates **Allure Report with Screenshots for failed cases**
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
- **Reporting**: Allure Report
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
│   │   ├── allure.properties       # Allure Report configuration
│-- test-output-thread/             # Parallel execution reports
│-- test-output/                    # Test output directory (TestNG reports, screenshots, logs)
│   ├── allure-report/              # Allure report files
│   ├── allure-results/             # Allure test results
│-- .gitignore                      # Git ignore file
│-- ApiDemos-debug.apk              # Sample APK for mobile testing
│-- README.md                       # Project documentation
│-- pom.xml                         # Maven dependencies & build configuration

```

## Configuration
- **Web App**: Update `config.properties` with browser settings.
- **Mobile App**: Set desired capabilities in `Hooks.java` class.
- **API Testing**: Configure base URLs, authToken and other fields in `config.properties`.
- **Allure Report**: Update `allure.properties`  for extent report configurations.

## Running Tests
### 1. Web Tests (Runs in headless mode by default)
```sh
mvn test -Dcucumber.tags="@WebApp"
```
### To run Web Tests in headed mode
```sh
mvn test -Dcucumber.tags="@WebApp" -Dheadless=false
```
### 2. Mobile Tests
```sh
mvn test -Dcucumber.tags="@MobileApp"
```
### 3. API Tests
```sh
mvn test -Dcucumber.tags="@API"
```

## Reports
- **Allure Report**: `test-output/allure-results`
