package StepDefinitions.API;

import API.ChangePasswordDetails;
import Hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.testng.Assert;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import static io.restassured.RestAssured.given;

public class ChangePasswordStepDefs {
    private Properties prop;
    private Response response;
    String currentPassword;
    String newPassword;
    ChangePasswordDetails changePasswordDetails = new ChangePasswordDetails();

    public ChangePasswordStepDefs() {
        this.prop = Hooks.getProperties();
    }

    @Given("I make a POST request to change password endpoint with the current and new password")
    public void i_make_a_post_request_to_change_password_endpoint_with_the_current_and_new_password() {
        currentPassword = prop.getProperty("api.password");
        newPassword = prop.getProperty("api.password") + "123";

        changePasswordDetails.setCurrentPassword(currentPassword);
        changePasswordDetails.setNewPassword(newPassword);

        response = given()
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .header("x-auth-token", Hooks.getAuthToken())
                .body(changePasswordDetails)
                .post("users/change-password");
    }

    @Then("the status code should be {int}")
    public void the_status_code_should_be(int statusCode) {
        statusCode = response.statusCode();
        Assert.assertEquals(statusCode, 200, "The status code is not 200");
    }

    @Then("the expected success message must be seen in the response")
    public void the_expected_success_message_must_be_seen_in_the_response() {
        String message = response.jsonPath().getString("message");
        Assert.assertTrue(message.contains("The password was successfully updated"));
    }

    @And("the new password must be saved in the prop file")
    public void theNewPasswordMustBeSavedInThePropFile() throws IOException {
        prop.setProperty("api.newPassword", newPassword);
        FileOutputStream fos = new FileOutputStream(Hooks.getPropertiesFilePath());
        prop.store(fos, "Updated with new password");
    }
}