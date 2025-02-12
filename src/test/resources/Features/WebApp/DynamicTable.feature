@WebApp
Feature: Dynamic Table handling

  As a user, I want to get the CPU load of Chrome process before and after refresh
  Also compare it with the specified value

  Scenario: Get the CPU load of chrome process
    Given I am on the dynamic table page
    When I get the CPU load of chrome process from the table
    Then compare it with the value in the yellow label, the values must match
    And when I refresh the page and compare the values again
    Then the values must still match