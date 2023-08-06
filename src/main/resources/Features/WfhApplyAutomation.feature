Feature: TickerTape Page Automation
Background: Open Chrome Browser
  Given Verify Chrome Browser is Open

  Scenario: Login Via SSO
    Given You browse for mis portal "https://mis.geminisolutions.com/Account/Login"
    When You login there via SSO
    Then type-in "ayush.saxena@geminisolutions.com" and "Shivam@1097" microsoft sign-in window

  Scenario Outline: Apply for Work From Home
    When You click over the Leave Management button followed by Apply button
    Then Apply for Work from Home on window with "<Reason>"

    Examples:
      | Reason |
      | Due to Imposition of Section 144 in Gurgaon |



