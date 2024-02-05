@allure
Feature: Check search functionality
  Check search results in iOS, Android apps

  Scenario: Check search results
    When I search for word "Java"
    Then article with title "Java (programming language)" should be shown

  Scenario: Check no result found
    When I search for word "ttrreeff"
    Then "No results" text should be shown

  Scenario: Check clearing search results
    When I search for word "Java"
    And I clear search line
    Then Cancel button should be absent
