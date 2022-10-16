Feature: Login feature test
As a Credit User, I want to view a Credit Application

  Scenario: Login to the application with valid user credentials
    Given User launches the web application
    When User enters the mobile number
    And User enters the otp
    Then Check if the user is on the credit list page
    And navigates to the Applications page
    And user navigates to the first Credit Application
    And user logs out of the application