@MobileApp
Feature: Appium Gestures

  Scenario: Perform click, long click and drag and drop gestures in appium
    Given I am on the Drag and Drop page
    When I long click any dot element
    And drag and drop the element
    Then the message dropped must be seen

  Scenario: Perform swipe gesture in appium
    Given I am on the photo gallery page
    When I swipe to the right
    Then the photos must be swiped to the right end