Feature: Get the list of People Working in QA
  Background: Open Chrome Browser
    Given Verify Chrome Browser is Open

  Scenario: Login Via SSO
    Given You browse for mis portal "https://mis.geminisolutions.com/Account/Login"
    When You login there via SSO
    Then type-in "email@geminisolutions.com" and "password" microsoft sign-in window

  Scenario: Get QA Employee Data
    Given Navigate to Employee Directory on MIS
    When Set entries and type QA in Search Box
    Then Extract the Employee Name, Designation and Delivery Council
    And Store the data into JSON