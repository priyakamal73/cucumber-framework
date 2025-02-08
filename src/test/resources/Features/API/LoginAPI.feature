@API
Feature: Login

  Scenario: Login with valid and existing user credentials
    Given I make a POST request to login endpoint with existing user credentials
    Then I should receive the status code 200
    And I should see the success message in its response
    And the token must be stored successfully