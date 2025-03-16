package StepDefinitions.WebApp;

import Hooks.Hooks;
import WebAppPages.BasicAuthenticationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class BasicAuthenticationStepDefs {

    BasicAuthenticationPage basicAuthenticationPage = new BasicAuthenticationPage(Hooks.getDriver());

    @Given("I am on the basic auth page")
    public void i_am_on_the_basic_auth_page() {
        basicAuthenticationPage.clickBasicAuthLink();
    }

    @When("I enter the valid {string} and {string}")
    public void iEnterTheValidAnd(String username, String password) {
        basicAuthenticationPage.enterCredentials(username, password);
    }

    @Then("I must see the success message")
    public void iMustSeeTheSuccessMessage() throws InterruptedException {
        String message = basicAuthenticationPage.returnMessage();
        Thread.sleep(3000);
        Assert.assertEquals(message, "Congratulations! You must have the proper credentials.", "Authentication failed");
    }
}