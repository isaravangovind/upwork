Feature: Upwork Freelancer Profile Validation Based on a Keywork

  Scenario Outline: Upwork
    Given Launch upwork application
    When Focus onto "<Search>"
    And Enter "<Skill Keyword>" into the search and select from drop down
    Then Get all FreeLancer Info
    And Assert at least one attribute contains "<Skill Keyword>"
    When Click on random freelancer's title
    And Get into that freelancer's profile
    Then Assert Selected Freelancer details Matches with Freelancer Details in First Page
#    And Assert at least one attribute contains "<keyword>"

    Examples:
      | Search                     | Skill Keyword |
      | professionals and agencies | data science  |