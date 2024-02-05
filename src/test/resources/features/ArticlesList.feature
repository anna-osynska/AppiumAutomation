Feature: Check interaction with articles list
  Check saving articles to list and removing articles

  Background:
    When I search for word "Java"
    And I open first article
    And I click save button
    And I add article to new "My list" list

  Scenario: Save articles to list
    When I open newly created list
    Then article should be present in the list

  Scenario: Delete article from list
    When I open newly created list
    And I swipe to delete article from list
    Then list should be empty

