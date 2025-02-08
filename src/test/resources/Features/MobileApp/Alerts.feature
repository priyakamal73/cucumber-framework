@MobileApp
Feature: Alerts in appium

  Scenario: Testing alerts in appium
    Given I am on the alerts page
    When I choose a specific alert type
    And perform alert operations
    Then the operations must be executed successfully