Feature: Test category

  Scenario: Test варочные поверхности
    Given Open chrome
    When I go YM and click on button with category
    Then Show correct category title
    Then Chrome should be closed