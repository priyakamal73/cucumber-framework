@WebApp
Feature: OTP Login

  As a registered user, I want to login using OTP

  Scenario: Successful login using OTP
    Given I open temp mail site and get the email address
    When I am on the otp login page
    And I enter that email address
    And click on send otp login button
    Then I should see the confirmation message with the email address
    When I enter the correct otp code from the email address
    And click on the verify otp code button
    Then I should land on the secure area page
    And I logout