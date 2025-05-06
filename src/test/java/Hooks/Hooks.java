package Hooks;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Properties;
import io.qameta.allure.Allure;

public class Hooks {

    protected static WebDriver driver;
    protected static AndroidDriver androidDriver;
    protected static String baseUrl;
    protected static String authToken;
    private static Properties prop;
    private static final String propertiesFilePath = "src/main/resources/config/config.properties";

    public static Properties getProperties() {
        if (prop == null) {
            prop = new Properties();
            try (InputStream input = Files.newInputStream(Paths.get(propertiesFilePath))) {
                prop.load(input);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load properties file", e);
            }
        }
        return prop;
    }

    public static String getPropertiesFilePath() {
        return propertiesFilePath;
    }

    @Before
    public void setup(Scenario scenario) {
        Properties prop = getProperties();

        // Extract tag like @WebApp, @API, etc.
        String testType = scenario.getSourceTagNames().stream()
                .filter(tag -> tag.contains("@WebApp") || tag.contains("@API") || tag.contains("@MobileApp"))
                .findFirst().orElse("Other");

        // Initialize based on tag
        if (testType.contains("@WebApp")) {
            setupWebDriver(prop);
        } else if (testType.contains("@API")) {
            setupApi(prop);
        } else if (testType.contains("@MobileApp")) {
            setupMobileDriver();
        }
    }


    private void setupWebDriver(Properties prop) {
        String browser = prop.getProperty("browser");
        if (!browser.equalsIgnoreCase("chrome")) {
            throw new RuntimeException("Unsupported browser: " + browser);
        }

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--remote-allow-origins=*");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);

        baseUrl = prop.getProperty("url");
        driver.get(baseUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    private void setupApi(Properties prop) {
        RestAssured.baseURI = prop.getProperty("api.url");
        authToken = prop.getProperty("api.authToken");
    }

    private void setupMobileDriver() {
        try {
            UiAutomator2Options options = new UiAutomator2Options();
            String path = "C:\\Users\\SANMUKA PRIYA\\eclipse-workspace\\Practice Testing Site\\ApiDemos-debug.apk";
            options.setCapability("appium:app", path);
            androidDriver = new AndroidDriver(new URI("http://0.0.0.0:4723").toURL(), options);
            Thread.sleep(2000);
        } catch (Exception e) {
            throw new RuntimeException("Appium driver initialization failed", e);
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        if (driver != null && scenario.isFailed()) {
            takeScreenshot(scenario.getName());
        }

        attachScenarioStatus(scenario);

        if (driver != null) {
            driver.quit();
        }
        if (androidDriver != null) {
            androidDriver.quit();
        }
    }

    private void takeScreenshot(String name) {
        try {
            if (driver != null) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Screenshot on Failure - " + name, "image/png",
                        new ByteArrayInputStream(screenshot), ".png");
            }
        } catch (Exception e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }
    }

    private void attachScenarioStatus(Scenario scenario) {
        scenario.isFailed();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static String getAuthToken() {
        return authToken;
    }

    public static AndroidDriver getAndroidDriver() {
        return androidDriver;
    }
}