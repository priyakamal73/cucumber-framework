@API
Feature: Update user profile

  Scenario: Update the user profile information
    Given I make a PATCH request to profile endpoint with updated details for the current logged in user
    Then I should get the status code as 200
    And I should see the success message for patch request