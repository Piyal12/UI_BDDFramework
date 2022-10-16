Feature: App Login

  Scenario Outline: Log in to the app as a sales rep

    Given User launch the application
    When User enter the mobile number default
    And User enter the otp received
    And Check if the user is on the order list page
    And User select the sarjapura store
    And User navigate to first order
    And User logout of the application
    Given user opens the phoenix app and dismisses the pop ups
    When user enters the <PhoneNumber> and password
    Then logs in
    Given User launch the application
    When User enter the mobile number default
    And User enter the otp received
    And Check if the user is on the order list page
    And User select the sarjapura store
    And User navigate to first order
    And User logout of the application

    Examples:
      | PhoneNumber |
      | 9830595855  |