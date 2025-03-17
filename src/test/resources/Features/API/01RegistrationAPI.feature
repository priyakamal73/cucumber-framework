@API
Feature: Registration

  Scenario: Create a new user account using valid credentials
    Given I make a POST request to register endpoint with valid user credentials
    Then I should receive a status code of 201
    And I should see the success message in the response