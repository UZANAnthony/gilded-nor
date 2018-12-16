Feature: Creation Date

  Scenario: The user adds an item
    Given the user has an inventory
    When the user buys an item
    Then the item has a creation date