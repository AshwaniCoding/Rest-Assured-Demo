package com.automation1;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class RestAssuredDemoGet {

    public static void main(String[] args) {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        RequestSpecification reqSpecification = RestAssured.given();
        reqSpecification.when().log().all().get("/booking/233")
                .then().log().all().assertThat().statusCode(200);
    }

}
