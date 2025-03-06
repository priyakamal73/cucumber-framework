@WebApp
Feature: Basic authentication

  As a user, I want to login by using the basic authentication

  Scenario Outline: Valid basic authentication
    Given I am on the basic auth page
    When I enter the valid "<username>" and "<password>"
    Then I must see the success message

    Examples:
      | username | password |
      | admin    | admin    |