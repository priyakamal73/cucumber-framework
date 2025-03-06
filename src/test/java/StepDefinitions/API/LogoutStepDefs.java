package StepDefinitions.API;

import Hooks.Hooks;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.testng.Assert;
import java.util.Properties;
import static io.restassured.RestAssured.given;

public class LogoutStepDefs {
    private Properties prop;
    private Response response;

    public LogoutStepDefs(){
        this.prop = Hooks.getProperties();
    }
    @Given("I make a DELETE request to logout endpoint")
    public void i_make_a_delete_request_to_logout_endpoint() {
        response = given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .header("x-auth-token",Hooks.getAuthToken())
                .delete("users/logout");
    }
    @Then("I should receive the status code as {int}")
    public void i_should_receive_the_status_code_as(int statusCode) {
        statusCode = response.statusCode();
        Assert.assertEquals(statusCode, 200, "The status code is not 200");
    }
    @Then("I should get the expected success message in its response")
    public void i_should_get_the_expected_success_message_in_its_response() {
        String message = response.jsonPath().getString("message");
        Assert.assertTrue(message.contains("User has been successfully logged out"));
    }
}