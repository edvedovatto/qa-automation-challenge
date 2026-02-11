Feature: Login validation

  Scenario Outline: Login validation
    Given the user is on the login page
    When the user logs in with "<username>" and "<password>"
    Then the login result message should be "<message>"

    Examples:
      | username          | password       | message                                                             |
      | standard_user     | secret_sauce   | Products                                                            |
      | locked_out_user   | secret_sauce   | Epic sadface: Sorry, this user has been locked out.                 |
      | invalid_user      | wrong_password | Epic sadface: Username and password do not match any user in this service |