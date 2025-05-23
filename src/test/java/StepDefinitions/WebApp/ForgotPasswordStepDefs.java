package StepDefinitions.WebApp;

import Hooks.Hooks;
import WebAppPages.ForgotPasswordPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class ForgotPasswordStepDefs {
    ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(Hooks.getDriver());

    @Given("I am on the forgot password screen")
    public void i_am_on_the_forgot_password_screen() {
        forgotPasswordPage.scrollPage();
        forgotPasswordPage.clickForgotPasswordPageButton();
    }

    @When("I enter a valid {string}")
    public void iEnterAValid(String emailAddress) {
        forgotPasswordPage.enterEmailAddress(emailAddress);
    }

    @When("click on the retrieve password button")
    public void click_on_the_retrieve_password_button() {
        forgotPasswordPage.clickRetrievePasswordButton();
    }

    @Then("I should see the success message")
    public void i_should_see_the_success_message() {
        String message = forgotPasswordPage.returnMessage();
        Assert.assertEquals(message, "An e-mail has been sent to you which explains how to reset your password.", "Reset failed");
    }
}