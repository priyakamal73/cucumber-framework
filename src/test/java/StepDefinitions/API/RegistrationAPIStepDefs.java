package StepDefinitions.API;

import Hooks.Hooks;
import API.RegistrationDetails;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.testng.Assert;
import java.util.Properties;
import static io.restassured.RestAssured.given;

public class RegistrationAPIStepDefs {
    private final Properties prop;
    private Response response;
    RegistrationDetails registrationDetails = new RegistrationDetails();

    public RegistrationAPIStepDefs() {
        this.prop = Hooks.getProperties();
    }

    @Given("I make a POST request to register endpoint with valid user credentials")
    public void iMakeAPOSTRequestToUsersRegisterEndpointWithValidUserCredentials() {

        String email = prop.getProperty("api.email");
        String password = prop.getProperty("api.password");
        String name = prop.getProperty("api.name");

        registrationDetails.setName(name);
        registrationDetails.setEmail(email);
        registrationDetails.setPassword(password);

        response = given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(registrationDetails)
                .when()
                .post("/users/register");
    }

    @Then("I should receive a status code of {int}")
    public void iShouldReceiveAStatusCodeOf(int statusCode) {
        statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201, "The status code is not 201");
    }

    @Then("I should see the success message in the response")
    public void i_should_see_the_success_message_in_the_response() {
        String message = response.jsonPath().getString("message");
        Assert.assertEquals(message, "User account created successfully", "The response message does not match");
    }
}