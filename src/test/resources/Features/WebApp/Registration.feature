@WebApp
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
      | username     | password         | confirm password |
      | PtolemyPutin | ptolemyputin!123 | ptolemyputin!123 |
      | NikomaTravis | nikomatravis!123 | nikomatravis!123 |
      | Xenokram     | xenokram!123     | xenokram!123     |

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