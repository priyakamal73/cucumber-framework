@API
Feature: Retrieve the user profile information

  Scenario: Get the user profile information for logged-in current user
    Given I make a GET request to profile endpoint with current logged-in user
    Then I should see the status code 200
    And I should see the success message in response