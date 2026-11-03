Feature: Demo feature

  Background:
    Given Goto the loginpage

    When enter correct credentials
      |username|password|
      |Admin123   |admin123|
    And click on the login button
 @smoke
  Scenario: login functionality with valid data

    Then login successfull

    And profile picture display

  @regression @smoke
  Scenario: login functionality with invalid data


    Then it should throw error

    And error message display


