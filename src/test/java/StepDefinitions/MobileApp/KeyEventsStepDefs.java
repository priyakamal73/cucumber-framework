package StepDefinitions.MobileApp;

import Hooks.Hooks;
import MobileAppPages.KeyEventsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class KeyEventsStepDefs {

    KeyEventsPage keyEventsPage = new KeyEventsPage(Hooks.getAndroidDriver());

    @Given("I am on the text fields page")
    public void i_am_on_the_text_fields_page() {
        keyEventsPage.clickViewsPage();
        keyEventsPage.clicktextFieldsPage();
    }

    @When("I click on the first line of the editable text area")
    public void i_click_on_the_first_line_of_the_editable_text_area() {
        keyEventsPage.clickFirstLineOfEditableArea();
    }

    @When("I use the key events to press the keys and type the text")
    public void i_use_the_key_events_to_press_the_keys_and_type_the_text() {
        keyEventsPage.typeText();
    }

    @Then("the text must be seen successfully")
    public void the_text_must_be_seen_successfully() {
        String text = keyEventsPage.returnText();
        Assert.assertTrue(text.contains("hi"));
    }
}