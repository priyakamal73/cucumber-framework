package StepDefinitions.WebApp;

import Hooks.Hooks;
import WebAppPages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginStepDefs {

    LoginPage loginPage = new LoginPage(Hooks.getDriver());

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
        String message = loginPage.returnMessage();
        Assert.assertEquals(message, "You logged into a secure area!", "Login Failed");
    }

    @Then("I must be redirected to the secure area page")
    public void i_must_be_redirected_to_the_secure_area_page() {
        String currentUrl = loginPage.returnCurrentUrl();
        Assert.assertEquals(currentUrl, "https://practice.expandtesting.com/secure", "Login Failed");
    }

    @Then("when I click on logout button")
    public void when_i_click_on_logout_button() {
        loginPage.clickLogoutButton();
    }

    @Then("I must be back to the login page")
    public void i_must_be_back_to_the_login_page() {
        String currentUrl = loginPage.returnCurrentUrl();
        Assert.assertEquals(currentUrl, "https://practice.expandtesting.com/login", "Login Successful");
    }

    @When("I enter valid{string} and invalid {string}")
    public void iEnterValidAndInvalid(String userName, String passWord) {
        loginPage.scrollPage();
        loginPage.enterUsername(userName);
        loginPage.enterPassword(passWord);
    }

    @Then("I must see an error message")
    public void i_must_see_an_error_message() {
        String message = loginPage.returnMessage();
        Assert.assertEquals(message, "Your password is invalid!", "Login Successful");

    }

    @Then("remain on the login page")
    public void remain_on_the_login_page() {
        String currentUrl = loginPage.returnCurrentUrl();
        Assert.assertEquals(currentUrl, "https://practice.expandtesting.com/login", "Login Successful");
    }
}