@API
Feature: Logout

  Scenario: Logout using existing valid credentials
    Given I make a DELETE request to logout endpoint
    Then I should receive the status code as 200
    And I should get the expected success message in its response
