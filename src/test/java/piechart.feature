Feature: Piechart Item Frequency

  Scenario: the user has an inventory and add an item
    Given the user has an inventory
    And a list containing item frequency
    When the user buys an item
    Then the item frequency list is updated