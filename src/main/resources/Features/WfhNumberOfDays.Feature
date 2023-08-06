Feature: Get Number of Days WFH Taken
  Background: Open Chrome Browser
    Given Verify Chrome Browser is Open

  Scenario: Login Via SSO
    Given You browse for mis portal "https://mis.geminisolutions.com/Account/Login"
    When You login there via SSO
    Then type-in "ayush.saxena@geminisolutions.com" and "Shivam@1097" microsoft sign-in window

  Scenario: Get Number of Days WFH Applied
    Given Verify you're on dashboard page of MIS
    Then Get the number of days of Work from Home you applied in the month