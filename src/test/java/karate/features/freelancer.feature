Feature: Freelancer Details

  Background:
    * url 'http://localhost:8080/freelancer'

  Scenario: testing the post call for freelancer Details to add
    Given path 'add'
    When method post
    Then status 400
