@API
Feature: Change password

  As a registered user, I want to change my current password

  Scenario: Change the password
    Given I make a POST request to change password endpoint with the current and new password
    Then the status code should be 200
    And the expected success message must be seen in the response
    And the new password must be saved in the prop file