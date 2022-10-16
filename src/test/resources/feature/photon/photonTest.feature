Feature: Login feature test
  As a user i want to test the login functionality of photon

  Scenario: Login to the application with valid user credentials
    Given User launch the application
    When User enter the mobile number default
    And User enter the otp received
    Then Check if the user is on the order list page
    And User select the sarjapura store
    And User navigate to first order
    Then User logout of the application

  Scenario Outline: Login to the application with valid user credentials
    Given User launch the application
    When User enter the <mobile> number
    And User enter the <otp> received
    Then Check if the user is on the order list page
    And User select the sarjapura store
    And User navigate to first order
    Then User logout of the application
   Examples: 
     | mobile  		| otp    |
     | 9945053050 | 123456 |
     | 9491349777 | 123456 |
