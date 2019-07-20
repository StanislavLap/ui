Feature: Test category

  @e2e
  Scenario: Test choose main category
    Given Open chrome
    When I go YM and click on button with category
    Then Show correct category title
    Then Chrome should be closed