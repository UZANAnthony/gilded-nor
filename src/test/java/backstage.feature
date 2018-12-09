Feature: Backstage Update

  Scenario: Backstage pass item has a sell in above 10
    Given I have a Backstage passes to a TAFKAL80ETC concert
    And I set its quality as 20
    And I set its sellin as 11
    When I update the inventory
    Then its quality is 21
    And its sellin is 10

  Scenario: Backstage pass item has a sell in between 6 and 10
    Given I have a Backstage passes to a TAFKAL80ETC concert
    And I set its quality as 20
    And I set its sellin as 10
    When I update the inventory
    Then its quality is 22
    And its sellin is 9

  Scenario: Backstage pass item has a sell in between 1 and 5
    Given I have a Backstage passes to a TAFKAL80ETC concert
    And I set its quality as 20
    And I set its sellin as 5
    When I update the inventory
    Then its quality is 23
    And its sellin is 4

  Scenario: Backstage pass item has a sell in of 0
    Given I have a Backstage passes to a TAFKAL80ETC concert
    And I set its quality as 20
    And I set its sellin as 0
    When I update the inventory
    Then its quality is 0
    And its sellin is 0

  Scenario: Backstage pass item has a sell in between 1 and 5 and a quality of 49
    Given I have a Backstage passes to a TAFKAL80ETC concert
    And I set its quality as 49
    And I set its sellin as 4
    When I update the inventory
    Then its quality is 50
    And its sellin is 3