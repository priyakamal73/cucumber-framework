package Runner;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;

@CucumberOptions(features = "C:\\Users\\SANMUKA PRIYA\\eclipse-workspace\\Practice Testing Site\\src\\test\\resources\\Features\\WebApp",
        glue = {"StepDefinitions.WebApp", "Hooks"},
        plugin = {"pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "timeline:test-output-thread/"
        }, monochrome = true,
        dryRun = false,
        tags = "@WebApp"
)

@Listeners({ExtentITestListenerClassAdapter.class})
public class WebTestRunner extends AbstractTestNGCucumberTests {
}