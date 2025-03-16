package StepDefinitions.WebApp;

import Hooks.Hooks;
import WebAppPages.DigestAuthenticationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

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
    public void i_must_see_the_success_message_on_the_page() throws InterruptedException {
        String message = digestAuthenticationPage.returnMessage();
        Thread.sleep(5000);
        Assert.assertEquals(message, "Congratulations! You must have the proper credentials.", "Authentication failed");
    }
}