Feature: Test quantity

  Scenario: Tests for quantity
    Given Open chrome
    When I go YM and click on button with category
    When I click on subcategory
    When I click on bosch manufacturer
    When I change price range from 10000 to 20000
    Then Price range changed
    Then Chrome should be closed
