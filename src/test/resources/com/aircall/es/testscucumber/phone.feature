@phone
Feature: Phone Automated Features

  Scenario: Perform a Call among two users
    Given A calling user logins to Aircall phone
    And A receiving user logins to Aircall phone
    When The calling user calls the receiver
    Then The receiving user is capable of accepting the call
