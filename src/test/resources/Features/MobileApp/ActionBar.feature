@MobileApp @action
Feature: Action Bars

  As a user, I would like to customize my action bar display options

  Scenario: Action Bar display options
    Given I am on the Display Options page
    When I click on the DISPLAY_HOME_AS_UP button
    Then I should see the back button next to the page title
    And if I click on the DISPLAY_HOME_AS_UP button again
    Then the back button must be hidden