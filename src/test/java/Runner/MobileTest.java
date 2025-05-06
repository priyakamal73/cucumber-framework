package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/Features/MobileApp",
        glue = {"StepDefinitions.MobileApp", "Hooks"},
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "json:target/cucumber-reports/cucumber.json",
                "timeline:test-output-thread/"
        },
        monochrome = true,
        dryRun = false,
        tags = "@MobileApp"
)
public class MobileTest extends AbstractTestNGCucumberTests {
}