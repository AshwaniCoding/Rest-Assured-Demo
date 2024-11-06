Feature: Verify token is generated

  Background:
    Given user wants to call "/auth" end point
    And set header "Content-Type" to "application/json"

  Scenario: Verify token is generated with valid credentials
    And set request body from file "create_token.json"
    When user performs post call
    Then verify status code is 200
    And verify response body has field "token"

  Scenario Outline: Verify token is not generated with invalid credentials
    And set the request body from file "create_token.json" with "<username>" and "<password>"
    When user performs post call
    Then verify status code is 200
    And verify response body has not field "token"

    Examples:
    | username | password    |
    | admin    | invalid     |
    | Admin    | password123 |
    | admin    | PASSWORD123 |
    | invalid  | invalid123  |