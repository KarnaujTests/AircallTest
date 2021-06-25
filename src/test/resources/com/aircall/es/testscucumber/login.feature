@sanity
Feature: Santiy Aircall functionalities

  Scenario: Log in to Aircall phone
    Given A calling user that navigates to Aircall phone
    When the calling user logins to the page
    Then the calling user navigates through the onboarding flow
