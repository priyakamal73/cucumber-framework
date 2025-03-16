@Ignore
Feature: Digest authentication

  As a user, I want to login by using the digest authentication

  Scenario Outline: Valid digest authentication
    Given I am on the digest auth page
    When I enter the valid "<username>" and "<password>" credentials
    Then I must see the success message on the page

    Examples:
      | username | password |
      | admin    | admin    |