Feature: Test manufacturer

  Scenario: Test Bosch
    Given Open chrome
    When I go YM and click on button with category
    When I click on subcategory
    When I click on bosch manufacturer
    Then Show correct bosch manufacturer title
    Then Chrome should be closed
