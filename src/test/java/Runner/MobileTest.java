package Runner;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@CucumberOptions(features = "src/test/resources/Features/MobileApp",
        glue = {"StepDefinitions.MobileApp", "Hooks"},
        plugin = {"pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
                "timeline:test-output-thread/"
        }, monochrome = true,
        dryRun = false,
        tags = "@MobileApp"
)

@Listeners({ExtentITestListenerClassAdapter.class})
public class MobileTest extends AbstractTestNGCucumberTests {
}