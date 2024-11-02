package com.automation.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateBookingRequestPojo {

    String firstname;
    String lastname;
    int totalprice;
    boolean depositpaid;
    BookingDate bookingdates;
    String additionalneeds;

}
