Feature: Check different actions
  Check tap, swipe, rotation, run in the background

  Scenario: Check tap action
    When I search for word "Java"
    And I tap on first article
    Then share option should be present

  Scenario: Check swipe functionality
    When I swipe to footer
    Then footer should be shown

  Scenario: Check run in the background
    When I search for word "Java"
    And double tap on first result
    And app runs in the background
    Then article with name "Java" should be present

  Scenario: Check devise rotation
    When I get first article title
    And I rotate devise
    And I get first article title again
    Then the same article should be shown




