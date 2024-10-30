package com.automation;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RestAssuredDemoPost {
    public static void main(String[] args) throws FileNotFoundException {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        RequestSpecification reqSpecification = RestAssured.given();
        reqSpecification.contentType("application/json");

        Scanner sc = new Scanner(new FileInputStream("src/test/resources/data/create_booking.json"));
        String body = sc.useDelimiter("\\Z").next();

        reqSpecification.body(body);
        Response response = reqSpecification.post("/booking");
        System.out.println(response.statusCode());
        System.out.println(response.asPrettyString());
    }
}
