package StepDefinitions.WebApp;

import Hooks.Hooks;
import WebAppPages.RegistrationPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class RegistrationStepDefs {
    RegistrationPage registrationPage = new RegistrationPage(Hooks.getDriver());

    @Given("I am on the registration page")
    public void i_am_on_the_registration_page() {
        registrationPage.scrollPage();
        registrationPage.clickRegisterPageLink();
    }

    @When("I enter a valid {string}, {string}, and {string}")
    public void i_enter_a_valid_and(String userName, String passWord, String confirmpassword) {
        registrationPage.scrollPage();
        registrationPage.enterUsername(userName);
        registrationPage.enterPassword(passWord);
        registrationPage.enterConfirmPassword(confirmpassword);
    }

    @When("I click on the register button")
    public void i_click_on_the_register_button() {
        registrationPage.clickRegisterButton();
    }

    @Then("I should see a success message")
    public void i_should_see_a_success_message() {
        registrationPage.isSuccessMessageDisplayed();
    }

    @Then("I should be redirected to the login page")
    public void i_should_be_redirected_to_the_login_page() {
        registrationPage.isLoginPageRedirected();
    }

    @When("I enter existing users {string}, {string}, and {string}")
    public void iEnterExistingUsersAnd(String userName, String passWord, String confirmpassword) {
        registrationPage.scrollPage();
        registrationPage.enterUsername(userName);
        registrationPage.enterPassword(passWord);
        registrationPage.enterConfirmPassword(confirmpassword);
    }

    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage() {
        registrationPage.isErrorMessageDisplayed();
    }

    @And("I should remain in the register page")
    public void iShouldRemainInTheRegisterPage() {
        registrationPage.isRegisterpageRetained();
    }
}