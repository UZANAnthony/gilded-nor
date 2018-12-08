Feature: Inventory Item

  Scenario: sulfuras item update
    Given I have a new inventory
    Then the quality of Sulfuras item is 80
    When I update the inventory
    Then the quality of Sulfuras item is 80