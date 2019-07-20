Feature: Test quantity

  @e2e
  Scenario: Tests for quantity
    Given Open chrome
    When I go YM and click on button with category
    When I click on subcategory
    When I click on bosch manufacturer
    When I select quantity 12
    Then Number items on page equals 12
    Then Chrome should be closed
