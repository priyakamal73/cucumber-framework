@MobileApp
Feature: Appium key events

  Scenario: Perform key events in appium
    Given I am on the text fields page
    When I click on the first line of the editable text area
    And I use the key events to press the keys and type the text
    Then the text must be seen successfully