@WebApp @Smoke @Regression
Feature: User Registration

  As a new user
  I want to register an account
  So that I can access the site
  Also test that already existing users cannot register again

  Scenario Outline: Successful user registration with valid details
    Given I am on the registration page
    When I enter a valid "<username>", "<password>", and "<confirm password>"
    And I click on the register button
    Then I should see a success message
    And I should be redirected to the login page

    Examples:
      | username   | password          | confirm password  |
      | MarcoX101  | Napoli@2024!sole  | Napoli@2024!sole  |
      | ElisaM909  | Firenze#2023*arte | Firenze#2023*arte |
      | DavideZ456 | Genova*2025@mare  | Genova*2025@mare  |

  Scenario Outline: Invalid user registration with existing users
    Given I am on the registration page
    When I enter existing users "<username>", "<password>", and "<confirm password>"
    And I click on the register button
    Then I should see an error message
    And I should remain in the register page

    Examples:
      | username  | password   | confirm password |
      | gloria123 | Gloria@123 | Gloria@123       |
      | jay123    | Jay@123    | Jay@123          |
      | manny123  | Manny@123  | Manny@123        |