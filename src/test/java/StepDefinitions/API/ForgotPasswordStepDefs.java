package StepDefinitions.API;

import API.ForgotPasswordDetails;
import Hooks.Hooks;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.testng.Assert;
import java.util.Properties;
import static io.restassured.RestAssured.given;
public class ForgotPasswordStepDefs {

    private Properties prop;
    private Response response;
    ForgotPasswordDetails forgotPasswordDetails = new ForgotPasswordDetails();

    public ForgotPasswordStepDefs(){
        this.prop = Hooks.getProperties();
    }

    @Given("I make a POST request to forgot password endpoint with existing email")
    public void i_make_a_post_request_to_forgot_password_endpoint_with_existing_email() {

        String email = prop.getProperty("api.email");

        forgotPasswordDetails.setEmail(email);

        response = given()
                .header("accept","application/json")
                .header("Content-Type", "application/json")
                .body(forgotPasswordDetails)
                .post("/users/forgot-password");
    }
    @Then("the status code must be {int}")
    public void the_status_code_must_be(int statusCode) {
        statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "The status code is not 200");
    }
    @Then("the expected success message must be displayed in the response")
    public void the_expected_success_message_must_be_displayed_in_the_response() {
        String message = response.jsonPath().getString("message");
        Assert.assertTrue(message.contains("Password reset link successfully sent"));
    }
}