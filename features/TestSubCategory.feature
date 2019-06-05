Feature: Test subcategory

  Scenario: Test choose subcategory
    Given Open chrome
    When I go YM and click on button with category
    Then Show correct category title
    When I click on subcategory
    Then Show correct subcategory title
    Then Chrome should be closed
