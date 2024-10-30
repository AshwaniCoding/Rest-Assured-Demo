package com.automation1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RestAssuredDemoAuth {
    public static void main(String[] args) throws FileNotFoundException {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        RequestSpecification reqSpecification = RestAssured.given();
        reqSpecification.contentType("application/json");

        Scanner sc = new Scanner(new FileInputStream("src/test/resources/data/create_token.json"));
        String body = sc.useDelimiter("\\Z").next();


        reqSpecification.body(body);
        Response response = reqSpecification.post("/auth");
        System.out.println(response.statusCode());
        System.out.println(response.asPrettyString());

        String token = response.jsonPath().getString("token");
        System.out.println("Extracted Token: " + token);

    }

    public static String getToken() throws FileNotFoundException {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        RequestSpecification reqSpecification = RestAssured.given();
        reqSpecification.contentType("application/json");

        Scanner sc = new Scanner(new FileInputStream("src/test/resources/data/create_token.json"));
        String body = sc.useDelimiter("\\Z").next();


        reqSpecification.body(body);
        Response response = reqSpecification.post("/auth");
        System.out.println(response.statusCode());
        System.out.println(response.asPrettyString());

        return response.jsonPath().getString("token");
    }
}
