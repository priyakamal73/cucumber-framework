package StepDefinitions.MobileApp;

import Hooks.Hooks;
import MobileAppPages.ActionBarPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class ActionBarStepDefs {
    boolean flag;
    ActionBarPage actionBarPage = new ActionBarPage(Hooks.getAndroidDriver());

    @Given("I am on the Display Options page")
    public void i_am_on_the_display_options_page() {
        actionBarPage.navigateToDisplayOptionsPage();
    }

    @When("I click on the DISPLAY_HOME_AS_UP button")
    public void i_click_on_the_display_home_as_up_button() {
        actionBarPage.clickDisplayHomeAsUpButton();
    }

    @Then("I should see the back button next to the page title")
    public void i_should_see_the_back_button_next_to_the_page_title() {
        actionBarPage.isBackButtonDisplayed();
    }

    @Then("if I click on the DISPLAY_HOME_AS_UP button again")
    public void if_i_click_on_the_display_home_as_up_button_again() {
        actionBarPage.clickDisplayHomeAsUpButton();
    }

    @Then("the back button must be hidden")
    public void the_back_button_must_be_hidden() {
        flag = actionBarPage.isBackButtonHidden();
        Assert.assertFalse(flag);
    }
}