package StepDefinitions.MobileApp;

import Hooks.Hooks;
import MobileAppPages.NotificationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class NotificationToggleStepDefs {
    boolean flag;
    NotificationPage notificationPage = new NotificationPage(Hooks.getAndroidDriver());

    @Given("I open my notifications")
    public void i_open_my_notifications() {
        notificationPage.openNotificationContainer();
    }

    @When("I click on the airplane toggle")
    public void i_click_on_the_airplane_toggle() {
        flag = notificationPage.toggleAirplaneMode();
    }

    @Then("the airplane must be turned on")
    public void the_airplane_must_be_turned_on() {
        Assert.assertTrue(flag);
    }
}