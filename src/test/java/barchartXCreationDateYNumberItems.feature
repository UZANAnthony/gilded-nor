Feature: Barchart X = Creation Date ; Y = Number of items

  Scenario: The user adds an item
    Given the user has an inventory
    When the user buys an item
    Then the barchart 2 (X= creation date and Y = number of items) is updated

  Scenario: The user removes an item
    Given the user has an inventory
    When the user sells an item
    Then the barchart 2 (X= creation date and Y = number of items) is updated