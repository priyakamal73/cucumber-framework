package StepDefinitions.WebApp;

import Hooks.Hooks;
import WebAppPages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDefs {

    LoginPage loginPage = new LoginPage(Hooks.getDriver());

    //Valid users
    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        loginPage.scrollPage();
        loginPage.clickLoginPageLink();
    }

    @When("I enter valid {string} and {string}")
    public void i_enter_valid_and(String userName, String passWord) {
        loginPage.scrollPage();
        loginPage.enterUsername(userName);
        loginPage.enterPassword(passWord);
    }

    @When("click on the login button")
    public void click_on_the_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("I should see a Success message")
    public void i_should_see_a_success_message() {
        loginPage.isSuccessMessageDisplayed();
    }

    @Then("I must be redirected to the secure area page")
    public void i_must_be_redirected_to_the_secure_area_page() {
        loginPage.isSecurePageRedirected();
    }

    @Then("when I click on logout button")
    public void when_i_click_on_logout_button() {
        loginPage.clickLogoutButton();
    }

    @Then("I must be back to the login page")
    public void i_must_be_back_to_the_login_page() {
        loginPage.isLoginPageSeen();
    }

    //Users with invalid password

    @When("I enter valid{string} and invalid {string}")
    public void iEnterValidAndInvalid(String userName, String passWord) {
        loginPage.scrollPage();
        loginPage.enterUsername(userName);
        loginPage.enterPassword(passWord);
    }

    @Then("I must see an error message")
    public void i_must_see_an_error_message() {
        loginPage.isErrorMessageDisplayed();
    }

    @Then("remain on the login page")
    public void remain_on_the_login_page() {
        loginPage.isLoginPageSeen();
    }
}