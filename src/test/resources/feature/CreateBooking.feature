Feature: Validate Create Booking End Point

  Scenario: Verify user can create booking
    Given user wants to call "/booking" end point
    And set header "Content-Type" to "application/json"
    And set request body from file "create_booking.json"
    When user performs post call
    Then verify status code is 200

  Scenario: Verify user can get list of booking
    Given user wants to call "/booking" end point
    When user performs get call
    Then verify status code is 200

  Scenario: Verify user can create booking
    Given user wants to call "/booking" end point
    And set header "Content-Type" to "application/json"
    And set request body from pojo "CreateBookingResponsePojo"
    When user performs post call
    Then verify status code is 200