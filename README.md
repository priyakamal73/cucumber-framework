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
- Integerated with  **Jenkins** for Continuos Integration

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
│-- .idea/                         # IDE settings
│-- src/
│   │-- main/
│   │   │-- java/
│   │   │   │-- API/               # API POJO Classes
│   │   │   │-- MobileAppPages/    # Mobile app Page Objects
│   │   │   │-- WebAppPages/       # Web app Page Objects
│   │   │-- resources/
│   │   │   │-- config/            # Configuration file
│   │-- test/
│   │   │-- java/
│   │   │   │-- Hooks/             # Cucumber hooks
│   │   │   │-- Runner/            # Test runner file
│   │   │   │-- StepDefinitions/
│   │   │   │   │-- API/           # API step definitions
│   │   │   │   │-- MobileApp/     # Mobile step definitions
│   │   │   │   │-- WebApp/        # Web step definitions
│   │   │-- resources/
│   │   │   │-- Features/
│   │   │   │   │-- API/           # API feature files
│   │   │   │   │-- MobileApp/     # Mobile feature files
│   │   │   │   │-- WebApp/        # Web feature files
│   │   │   │-- extent.properties  # Extent report properties
│   │   │   │-- extent-config.xml  # Extent report config
│-- target/                        # Compiled output
│-- test-output/                   # Test reports
│-- test-output-thread/            # Threaded test reports
│-- .gitignore                     # Git ignore file
│-- ApiDemos-debug.apk             # Sample apk file for mobile app testing
│-- chromedriver.exe               # Chrome driver executable file
│-- pom.xml                        # pom file with dependencies
│-- README.file                    # README file

```

## Configuration
- **Web App**: Update `config.properties` with browser settings.
- **Mobile App**: Set desired capabilities in `Hooks.java` class.
- **API Testing**: Configure base URLs, authToken and other fields in `config.properties`.
- **Extent Report**: Update `extent.properties` and `extent-config.xml` for extent report configurations.

## Running Tests
### 1. Web Automation
```sh
mvn test -Dcucumber.filter.tags="@WebApp"
```
### 2. Mobile Automation
```sh
mvn test -Dcucumber.filter.tags="@MobileApp"
```
### 3. API Testing
```sh
mvn test -Dcucumber.filter.tags="@API"
```

## Reports
- **Extent Report**: `test-output/SparkReport/ExtentReport.html`
- **Cucumber HTML Report**: `target/cucumber-reports/index.html`
