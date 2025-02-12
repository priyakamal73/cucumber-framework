package StepDefinitions.MobileApp;

import Hooks.Hooks;
import MobileAppPages.AlertPage;
import io.cucumber.java.en.*;

public class AlertsStepDefs {

    AlertPage alertPage = new AlertPage(Hooks.getAndroidDriver());

    @Given("I am on the alerts page")
    public void i_am_on_the_alerts_page() {
        alertPage.navigateToAlertPage();
    }
    @When("I choose a specific alert type")
    public void i_choose_a_specific_alert_type() {
      alertPage.clickTraditionalThemeAlert();
    }
    @When("perform alert operations")
    public void perform_alert_operations() {
        alertPage.acceptAlert();
    }
    @Then("the operations must be executed successfully")
    public void the_operations_must_be_executed_successfully() {
        alertPage.isAlertListingPageDisplayed();
    }
}