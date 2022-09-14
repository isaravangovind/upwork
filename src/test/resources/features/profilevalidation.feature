Feature: Upwork Freelancer Profile Validation Based on a Keyword


  @hometask
  Scenario Outline: Upwork
    Given Launch upwork application
#    When Focus onto "<Search>"
#    And Enter "<Skill Keyword>" into the search and select from drop down
#    Then Get all FreeLancer Info
#    And Assert at least one attribute contains "<Skill Keyword>"
#    When Click on random freelancer's title
    Then Assert Selected Freelancer details Matches with Freelancer Details in First Page
    And Assert at least one attribute contains "<Skill Keyword>" in profilepage

    Examples:
      | Search                     | Skill Keyword |
      | professionals and agencies | data science  |