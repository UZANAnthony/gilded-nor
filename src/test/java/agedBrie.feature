Feature: Aged Brie Update

  Scenario: Aged Brie item has a quality between 0 and 49
    Given I have an Aged Brie
    And I set its quality as 10
    And I set its sellin as 20
    When I update the inventory
    Then its quality is 11
    And its sellin is 19

  Scenario: Aged Brie item has a quality of 50
    Given I have an Aged Brie
    And I set its quality as 50
    And I set its sellin as 20
    When I update the inventory
    Then its quality is 50
    And its sellin is 19