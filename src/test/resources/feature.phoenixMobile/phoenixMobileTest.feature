Feature: App Login

  Scenario Outline: Log in to the app as a sales rep
    Given user opens the phoenix app and dismisses the pop ups
    When user enters the <PhoneNumber> and password
    Then logs in

    Examples:
      | PhoneNumber |
      | 9830595855  |