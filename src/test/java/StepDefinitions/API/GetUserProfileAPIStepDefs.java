package StepDefinitions.API;

import Hooks.Hooks;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.testng.Assert;
import static io.restassured.RestAssured.given;

public class GetUserProfileAPIStepDefs {
    private Response response;

    @Given("I make a GET request to profile endpoint with current logged-in user")
    public void i_make_a_get_request_to_profile_endpoint_with_current_logged_in_user() {
        response = given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .header("x-auth-token", Hooks.getAuthToken())
                .when()
                .get("/users/profile");
    }

    @Then("I should see the status code {int}")
    public void iShouldSeeTheStatusCode(int statusCode) {
        statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,200,"The status code is not 200");
    }

    @Then("I should see the success message in response")
    public void i_should_see_the_success_message_in_response() {
        String message = response.jsonPath().getString("message");
        Assert.assertEquals(message,"Profile successful","Success messages does not match");
    }
}