Feature: Historic Buy Sell

  Scenario: The user buys an item
    Given the user has an inventory
    When the user buys an item
    Then the item is added to the list
    And the item is added to the history

  Scenario: The user sells an item
    Given the user has an inventory
    When the user sells an item
    Then the item is removed from the list
    And the sale is added to the history