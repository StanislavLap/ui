Feature: Test quantity

  Scenario: Tests for quantity
    Given Open chrome
    When I go YM and click on button with category
    When I click on subcategory
    When I click on bosch manufacturer
    When I change view to list
    Then View changed
    Then Chrome should be closed
