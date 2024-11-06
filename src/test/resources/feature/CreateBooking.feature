Feature: Validate Create Booking End Point

  Scenario: Verify user can create booking
    Given user wants to call "/booking" end point
    And set header "Content-Type" to "application/json"
    And set request body from file "create_booking.json"
#    And set request body from file "create_booking.json" as pojo "CreateBookingRequestPojo"
    When user performs post call
    Then verify status code is 200
#    And verify response body has same data as request
    And verify response body has field "booking.firstname" is "Ashwani Kumar"
    And verify response schema is "create_booking_schema.json"

  Scenario: Verify user can get list of booking
    Given user wants to call "/booking" end point
    When user performs get call
    Then verify status code is 200

  Scenario: Create and update booking
    Given user wants to call "/booking" end point
    And set header "Content-Type" to "application/json"
    And set request body from file "create_booking.json" as pojo "CreateBookingRequestPojo"
    When user performs post call
    Then verify status code is 200
    And verify response body has same data as request
    And verify response schema is "create_booking_schema.json"
    And store the "bookingid" from the response
    Given user wants to call "/auth" end point
    And set header "Content-Type" to "application/json"
    And set request body from file "create_token.json" as pojo "CreateTokenPojo"
    And user performs post call
    Then verify status code is 200
    And store the "token" from the response
    Given user wants to call "/booking/" end point with "bookingid"
    And set header "Content-Type" to "application/json"
    And set header value "token" in "Cookie"
    And set request body from file "update_booking.json" as pojo "CreateBookingRequestPojo"
    And user performs put call
    Then verify status code is 200
    And verify response body has field "additionalneeds" is "Room in the top floor"

    
