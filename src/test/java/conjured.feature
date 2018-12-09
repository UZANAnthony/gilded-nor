Feature: Conjured Update

  Scenario: Conjured item has a sell in above 0
    Given I have a Conjured Mana Cake
    And I set its quality as 20
    And I set its sellin as 20
    When I update the inventory
    Then its quality is 18
    And its sellin is 19

  Scenario: Conjured item has a sell in of 0
    Given I have a Conjured Mana Cake
    And I set its quality as 20
    And I set its sellin as 0
    When I update the inventory
    Then its quality is 16
    And its sellin is 0

  Scenario: Conjured item has a sell in above 0 and a quality of 1
    Given I have a Conjured Mana Cake
    And I set its quality as 1
    And I set its sellin as 20
    When I update the inventory
    Then its quality is 0
    And its sellin is 19