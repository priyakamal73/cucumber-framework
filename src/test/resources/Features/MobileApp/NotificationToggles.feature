@MobileApp @toggle
Feature: Notification toggles

  As a user, I want to toggle on/off my airplane settings

  Scenario: Toggle on airplane
    Given I open my notifications
    When I click on the airplane toggle
    Then the airplane must be turned on