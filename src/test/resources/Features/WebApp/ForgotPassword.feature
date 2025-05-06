@WebApp @Smoke @Regression @ForgotPassword @Now
Feature: Forgot password

  As a registered user, I want to change my password
  So that I can use the new password to login
  Also login using the new password

  Scenario Outline: Change password for valid email address
    Given I am on the forgot password screen
    When I enter a valid "<email address>"
    And click on the retrieve password button
    Then I should see the success message

    Examples:
      | email address      |
      | priya@gmail.com    |
      | clara@hotmail.com  |
      | luke@yahoo.com     |