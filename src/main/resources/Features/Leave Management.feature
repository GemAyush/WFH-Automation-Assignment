Feature: Leave Management Automation
Background: Open Chrome Browser
  Given Verify Chrome Browser is Open

  @Login
  Scenario: Login Via SSO
    Given You browse for mis portal "https://mis.geminisolutions.com/Account/Login"
    When You login there via SSO
    Then type-in "email@geminisolutions.com" and "password" microsoft sign-in window

  @ApplyForWorkFromHome
  Scenario Outline: Apply for Work From Home
    When You click over the Leave Management button followed by Apply button
    Then Apply for Work from Home on window with "<Reason>"

    Examples:
      | Reason |
      | Due to Imposition of Section 144 in Gurgaon |

  @GetNumberOfWFHApplied
  Scenario: Get Number of Days WFH Applied
    Given Verify you're on dashboard page of MIS
    Then Get the number of days of Work from Home you applied in the month

  @GetQAEmployeeData
  Scenario: Get QA Employee Data
    Given Navigate to Employee Directory on MIS
    When Set entries and type QA in Search Box
    Then Extract the Employee Name, Designation and Delivery Council
    And Store the data into JSON
