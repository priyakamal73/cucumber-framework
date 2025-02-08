package StepDefinitions.API;

import Hooks.Hooks;
import API.UpdateUserProfileDetails;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.testng.Assert;
import static io.restassured.RestAssured.given;

public class UpdateUserProfileStepDefs {
    private Response response;
    UpdateUserProfileDetails updateUserProfileDetails = new UpdateUserProfileDetails();

    @Given("I make a PATCH request to profile endpoint with updated details for the current logged in user")
    public void iMakeAPATCHRequestToProfileEndpointWithUpdatedDetailsForTheCurrentLoggedInUser() {
        updateUserProfileDetails.setName("Shaun");
        updateUserProfileDetails.setPhone("9940159832");
        updateUserProfileDetails.setCompany("Amazon");

        response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("x-auth-token", Hooks.getAuthToken())
                .body(updateUserProfileDetails)
                .when()
                .patch("/users/profile");
    }

    @Then("I should get the status code as {int}")
    public void i_should_get_the_status_code_as(int statusCode) {
        statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,200,"The status code is not 200");
    }
    @Then("I should see the success message for patch request")
    public void i_should_see_the_success_message_for_patch_request() {
        String message = response.jsonPath().getString("message");
        Assert.assertEquals(message,"Profile updated successful","The expected message is not displayed");
    }
}