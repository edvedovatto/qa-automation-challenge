Feature: Checkout

  Scenario: Successful checkout with valid information
    Given the user is logged in
    And the user has a product in the cart
    When the user completes checkout with valid information
    Then the checkout should be completed successfully

  Scenario: Checkout with missing required information
    Given the user is logged in
    And the user has a product in the cart
    When the user tries to checkout without filling required information
    Then an error message should be displayed on checkout