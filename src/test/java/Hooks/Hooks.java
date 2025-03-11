package Hooks;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import lombok.Getter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Properties;

public class Hooks {

    protected static WebDriver driver;
    protected static AndroidDriver androidDriver;
    protected static String baseUrl;
    protected static String authToken;
    private static Properties prop;
    private static final String propertiesFilePath = "src/main/resources/config/config.properties";


    public static Properties getProperties() {
        prop = new Properties();
        try (InputStream input = Files.newInputStream(Paths.get(propertiesFilePath))) {
            prop.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;
    }

    @Before
    public void setup(Scenario scenario) {
        Properties prop = getProperties();

        // Determine whether the test is for WebApp  or API or MobileApp by checking tags or properties
        String testType = scenario.getSourceTagNames().stream()
                .filter(tag -> tag.contains("@WebApp") || tag.contains("@API") || tag.contains("@MobileApp"))
                .findFirst().orElse("");

        if (testType.contains("@WebApp")) {
            String browser = prop.getProperty("browser");

            if (browser.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\SANMUKA PRIYA\\eclipse-workspace\\Practice Testing Site\\chromedriver.exe");
                driver = new ChromeDriver();
            } else {
                throw new RuntimeException("Unsupported browser: " + browser);
            }

            baseUrl = prop.getProperty("url");
            driver.get(baseUrl);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        } else if (testType.contains("@API")) {
            RestAssured.baseURI = prop.getProperty("api.url");
            authToken = prop.getProperty("api.authToken");

        } else if (testType.contains("@MobileApp")) {
            try {
                UiAutomator2Options options = new UiAutomator2Options();
                String path = "C:\\Users\\SANMUKA PRIYA\\eclipse-workspace\\Practice Testing Site\\ApiDemos-debug.apk";
                options.setCapability("appium:app", path);
                URL url = new URL("http://0.0.0.0:4723");
                androidDriver = new AndroidDriver(url, options);
                Thread.sleep(3000);
            } catch (Exception e) {
                throw new RuntimeException("Appium driver initialization failed: " + e.getMessage());
            }
        }
    }

    public static WebDriver getDriver(){return driver;}

    public static String getAuthToken() {
        return authToken;
    }

    public static AndroidDriver getAndroidDriver(){
        return androidDriver;
    }

    public static String getPropertiesFilePath(){
        return propertiesFilePath;
    }

    @After
    public void tearDown(Scenario scenario) {

        if (driver != null) {
            if (scenario.isFailed()) {
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
            }
            driver.quit();
        }
        if (scenario.isFailed() && scenario.getSourceTagNames().contains("@API")) {
            System.out.println("API Test failed: " + scenario.getName());
        }
    }
}