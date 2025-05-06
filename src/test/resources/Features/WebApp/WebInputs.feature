@WebApp @Regression
Feature: Test web input fields

  Scenario Outline: Successful data for all the input fields
    Given I am on the web inputs page
    When I enter valid "<number>", "<text>", "<password>" and "<date>"
    And click on display inputs button
    Then I must see the "<number>", "<text>", "<password>" and "<date>" that I entered as output

    Examples:
      | number | text                                 | password             | date       |
      | 100000 | Hi there! This is the text input box | supersecretpassword! | 22-01-2025 |