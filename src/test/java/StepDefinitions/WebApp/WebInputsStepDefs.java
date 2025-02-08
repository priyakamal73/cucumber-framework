package StepDefinitions.WebApp;

import Hooks.Hooks;
import WebAppPages.WebInputsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WebInputsStepDefs {

    WebInputsPage webInputsPage = new WebInputsPage(Hooks.getDriver());

    @Given("I am on the web inputs page")
    public void i_am_on_the_web_inputs_page() {
        webInputsPage.scrollPage();
        webInputsPage.clickInputsPageButton();
        webInputsPage.dismissTheGoogleAd();
    }

    @When("I enter valid {string}, {string}, {string} and {string}")
    public void iEnterValidAnd(String Number, String Text, String Password, String Date) {
        webInputsPage.scrollPage();
        webInputsPage.enterNumber(Number);
        webInputsPage.enterText(Text);
        webInputsPage.enterPassword(Password);
        webInputsPage.enterDate(Date);
    }

    @When("click on display inputs button")
    public void click_on_display_inputs_button() {
        webInputsPage.clickDisplayInputs();
    }

    @Then("I must see the {string}, {string}, {string} and {string} that I entered as output")
    public void iMustSeeTheAndThatIEnteredAsOutput(String Number, String Text, String Password, String Date) {
        webInputsPage.isOutputDisplayedAndAccurate(Number, Text, Password, Date);
        webInputsPage.clearInputs();
    }

}