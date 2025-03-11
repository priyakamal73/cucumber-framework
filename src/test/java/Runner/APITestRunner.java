package Runner;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;

@CucumberOptions(features = "C:\\Users\\SANMUKA PRIYA\\eclipse-workspace\\Practice Testing Site\\src\\test\\resources\\Features\\API",
        glue = {"StepDefinitions.API", "Hooks"},
        plugin = {"pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "timeline:test-output-thread/"
        }, monochrome = true,
        dryRun = false,
        tags = "@API"
)

@Listeners({ExtentITestListenerClassAdapter.class})
public class APITestRunner extends AbstractTestNGCucumberTests {
}