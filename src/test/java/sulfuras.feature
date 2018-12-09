Feature: Sulfuras Update

  Scenario: Sulfuras item already has a quality of 80
    Given I have a Sulfuras, Hand of Ragnaros
    And I set its quality as 80
    And I set its sellin as 20
    When I update the inventory
    Then its quality is 80
    And its sellin is 0

  Scenario: Sulfuras item has a quality different of 80
    Given I have a Sulfuras, Hand of Ragnaros
    And I set its quality as 40
    And I set its quality as 20
    When I update the inventory
    Then its quality is 80
    And its sellin is 0