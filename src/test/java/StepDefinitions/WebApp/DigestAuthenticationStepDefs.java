package StepDefinitions.WebApp;

import Hooks.Hooks;
import WebAppPages.DigestAuthenticationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DigestAuthenticationStepDefs {

    DigestAuthenticationPage digestAuthenticationPage = new DigestAuthenticationPage(Hooks.getDriver());

    @Given("I am on the digest auth page")
    public void i_am_on_the_digest_auth_page() {
        digestAuthenticationPage.clickDigestAuthLink();
    }

    @When("I enter the valid {string} and {string} credentials")
    public void i_enter_the_valid_and_credentials(String username, String password) {
        digestAuthenticationPage.enterCredentials(username, password);
    }

    @Then("I must see the success message on the page")
    public void i_must_see_the_success_message_on_the_page() {
        digestAuthenticationPage.isSuccessAlertDisplayed();

    }
}
