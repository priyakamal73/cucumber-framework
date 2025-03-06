package StepDefinitions.API;

import Hooks.Hooks;
import API.LoginDetails;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.proxy;

public class LoginAPIStepDefs {
    private Properties prop;
    private Response response;
    LoginDetails loginDetails = new LoginDetails();

    public LoginAPIStepDefs() {
        this.prop = Hooks.getProperties();
    }


    @Given("I make a POST request to login endpoint with existing user credentials")
    public void i_make_a_post_request_to_login_endpoint_with_existing_user_credentials() {

        String email = prop.getProperty("api.email");
        String password;

        if (prop.getProperty("api.newPassword") == null || prop.getProperty("api.newPassword").isEmpty()){
            password = prop.getProperty("api.password");
        }
        else{
            password = prop.getProperty("api.newPassword");
        }


        loginDetails.setEmail(email);
        loginDetails.setPassword(password);

        response = given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(loginDetails)
                .when()
                .post("/users/login");
    }

    @Then("I should receive the status code {int}")
    public void i_should_receive_the_status_code(int statusCode) {
        statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "The status code is not 200");
    }

    @Then("I should see the success message in its response")
    public void i_should_see_the_success_message_in_its_response() {
        String message = response.jsonPath().getString("message");
        Assert.assertEquals(message, "Login successful", "The success message is not as expected");
    }

    @And("the token must be stored successfully")
    public void theTokenMustBeStoredSuccessfully() throws IOException {
        String authToken = response.jsonPath().getString("data.token");
        prop.setProperty("api.authToken",authToken);
        FileOutputStream fos = new FileOutputStream(String.valueOf(Hooks.getPropertiesFilePath()));
        prop.store(fos, "Updated with auth token");
    }
}