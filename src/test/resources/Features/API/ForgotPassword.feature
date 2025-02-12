@API
Feature: Forgot password

  As a registered user, I want to request a new password

  Scenario: Send password reset link to user's email
    Given I make a POST request to forgot password endpoint with existing email
    Then the status code must be 200
    And the expected success message must be displayed in the response