Feature: goRest API Scenarios

  Scenario: GET users - good request
    Given I get the response from the endpoint
    Then I get the response code equals to 200

  Scenario: GET user - bad request
    Given I get the response from the endpoint with id 27
    Then I get the response code equals to 404

  Scenario: DELETE user - good request
    Given I DELETE a user by id 350
    Then I get the response code equals to 204

  Scenario: DELETE user - bad request
    Given I DELETE a user by id 27
    Then I get the response code equals to 404