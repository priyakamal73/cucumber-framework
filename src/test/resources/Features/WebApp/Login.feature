@WebApp @Smoke @Regression
Feature: User login

  As a registered user
  I want to login my account
  So that I can access the site
  Also test that already existing users cannot register again

  Scenario Outline: Successful login by valid users
    Given I am on the login page
    When I enter valid "<username>" and "<password>"
    And click on the login button
    Then I should see a Success message
    And I must be redirected to the secure area page
    And when I click on logout button
    Then I must be back to the login page

    Examples:
      | username  | password      |
      | gloria123 | Gloria@123    |
      | stella    | stella@123456 |
      | manny123  | Manny@123     |

  Scenario Outline: User login with valid username and invalid password
    Given I am on the login page
    When I enter valid"<username>" and invalid "<password>"
    And click on the login button
    Then I must see an error message
    And remain on the login page

    Examples:
      | username | password     |
      | practice | practice@123 |
      | test123  | test@123     |
      | admin123 | admin@123    |