Feature: login testing with multiple set of data

  Scenario Outline: login test

    Given user got the login page
    When  Enter the login data <username>and<password>
    And press the enter button
    Then login successful with correct data
    And user get error with incorrect data

    Examples:

    |username|password|
    |Admin   |admin123|
    |Admin   |Admin4978|
    |Jack    |admin123|
    |        |        |
    |Rock    |rocky123 |