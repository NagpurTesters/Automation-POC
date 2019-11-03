

@Test
Feature: search flights source to destination

 Scenario Outline: Login To Application
    Given open yatra application
    When click on flight icon
    And user enter Source name
    And user enter Destination name
    And user enter Depart date 
    And click on Search Button
    Then verify count search result
    

    Examples: 
      | Source  | Destination|
      | delhi   |   Mumbai   |
