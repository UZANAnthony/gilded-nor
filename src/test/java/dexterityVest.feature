Feature: Dexterity Vest Update

  Scenario: Dexterity Vest item has a sell in above 0
    Given I have a +5 Dexterity Vest
    And I set its quality as 40
    And I set its sellin as 20
    When I update the inventory
    Then its quality is 39
    And its sellin is 19

  Scenario: Dexterity Vest item has a sell in of 0
    Given I have a +5 Dexterity Vest
    And I set its quality as 40
    And I set its sellin as 0
    When I update the inventory
    Then its quality is 38
    And its sellin is 0