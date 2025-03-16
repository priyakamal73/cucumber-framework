package StepDefinitions.WebApp;

import Hooks.Hooks;
import WebAppPages.DynamicTablePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class DynamicTableStepDefs {
    DynamicTablePage dynamicTablePage = new DynamicTablePage(Hooks.getDriver());

    @Given("I am on the dynamic table page")
    public void i_am_on_the_dynamic_table_page() {
        dynamicTablePage.clickDynamicTablePageButton();
    }

    @When("I get the CPU load of chrome process from the table")
    public void i_get_the_cpu_load_of_chrome_process_from_the_table() {
        dynamicTablePage.getCPULoadFromTable();

    }

    @Then("compare it with the value in the yellow label, the values must match")
    public void compareItWithTheValueInTheYellowLabelTheValuesMustMatch() {
        String chromeCPU = dynamicTablePage.getChromeCPU();
        String yellowLabel = dynamicTablePage.getYellowLabel();
        Assert.assertEquals(chromeCPU, yellowLabel, "The Chrome CPU values are different");
    }

    @And("when I refresh the page and compare the values again")
    public void whenIRefreshThePageAndCompareTheValuesAgain() {
        dynamicTablePage.refreshPage();
        dynamicTablePage.getCPULoadFromTable();
    }

    @Then("the values must still match")
    public void theValuesMustStillMatch() {
        String chromeCPU = dynamicTablePage.getChromeCPU();
        String yellowLabel = dynamicTablePage.getYellowLabel();
        Assert.assertEquals(chromeCPU, yellowLabel, "The Chrome CPU values are different");
    }
}