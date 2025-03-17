package StepDefinitions.WebApp;

import Hooks.Hooks;
import WebAppPages.WebInputsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class WebInputsStepDefs {

    WebInputsPage webInputsPage = new WebInputsPage(Hooks.getDriver());

    @Given("I am on the web inputs page")
    public void i_am_on_the_web_inputs_page() {
        webInputsPage.scrollPage();
        webInputsPage.clickInputsPageButton();
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

        String number = webInputsPage.returnNumber();
        String text = webInputsPage.returnText();
        String password = webInputsPage.returnPassword();
        String date = webInputsPage.returnDate();

        DateTimeFormatter featureFileFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
        DateTimeFormatter webOutputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate parsedFeatureDate = LocalDate.parse(Date, featureFileFormat);
            LocalDate parsedWebDate = LocalDate.parse(date, webOutputFormat);

            String expectedDate = parsedFeatureDate.format(webOutputFormat);
            String actualDate = parsedWebDate.format(webOutputFormat);

            System.out.println("The obtained date is " + actualDate);
            System.out.println("The date from the feature file is " + expectedDate);

            Assert.assertEquals(number, Number, "The number entered is not the same");
            Assert.assertEquals(text, Text, "The text entered is not the same");
            Assert.assertEquals(password, Password, "The password entered is not the same");
            Assert.assertEquals(actualDate, expectedDate, "The date entered is not the same");
        } catch (Exception e) {
            e.printStackTrace();
        }
        webInputsPage.clearInputs();
    }
}