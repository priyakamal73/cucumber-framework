package StepDefinitions.WebApp;

import Hooks.Hooks;
import WebAppPages.OTPLoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class OTPLoginStepDefs {

    OTPLoginPage otpLoginPage = new OTPLoginPage(Hooks.getDriver());
    String email;
    int otp;

    @Given("I open temp mail site and get the email address")
    public void i_open_temp_mail_site_and_get_the_email_address() {
        otpLoginPage.openTempEmailSiteAndGetEmail();
    }

    @When("I am on the otp login page")
    public void i_am_on_the_otp_login_page() {
        otpLoginPage.clickOTPLoginPageButton();
        otpLoginPage.dismissTheGoogleAd();
    }

    @When("I enter that email address")
    public void i_enter_that_email_address() {
        email = otpLoginPage.enterEmailAddress();
    }

    @When("click on send otp login button")
    public void click_on_send_otp_login_button() {
        otpLoginPage.clickSendOTPCodeButton();
    }

    @Then("I should see the confirmation message with the email address")
    public void i_should_see_the_confirmation_message_with_the_email_address() {
        String otpMessage = otpLoginPage.returnOTPMessage();
        Assert.assertEquals(otpMessage, "We've sent an OTP code to your email: " + email, "The success message is not displayed");
    }

    @When("I enter the correct otp code from the email address")
    public void i_enter_the_correct_otp_code_from_the_email_address() {
        otp = otpLoginPage.getOTPCodeFromEmail();
        otpLoginPage.enterOTP(otp);
    }

    @When("click on the verify otp code button")
    public void click_on_the_verify_otp_code_button() {
        otpLoginPage.clickVerifyOTPCodeButton();
    }

    @Then("I should land on the secure area page")
    public void i_should_land_on_the_secure_area_page() throws InterruptedException {
        Thread.sleep(5000);
        String currentUrl = otpLoginPage.returnCurrentUrl();
        Assert.assertEquals(currentUrl, "https://practice.expandtesting.com/secure", "Login Failed");
        String message = otpLoginPage.returnMessage();
        Assert.assertEquals(message, "You logged into a secure area!", "Login Failed");
    }

    @Then("I logout")
    public void i_logout() {
        otpLoginPage.logout();
    }
}