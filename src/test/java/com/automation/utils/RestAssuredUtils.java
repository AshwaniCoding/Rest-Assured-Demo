package com.automation.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RestAssuredUtils {

    static RequestSpecification requestSpecification = RestAssured.given();
    static Response response;
    static String endPoint;

    public static void setEndPoint(String endPoint){
        RestAssuredUtils.endPoint = endPoint;
    }

    public static void setHeader(String key, String value){
        requestSpecification.header(key,value);
    }

    public static void setBody(String filePath){
        try {
            requestSpecification.body(getDataFromJsonFile(filePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setBody(Object pojo){
        try {
            requestSpecification.body(pojo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void get(){
        requestSpecification.log().all();
        response = requestSpecification.get(endPoint);
        response.then().log().all();
    }

    public static void post(){
        requestSpecification.log().all();
        response = requestSpecification.post(endPoint);
        response.then().log().all();
    }

    public static void put(){
        requestSpecification.log().all();
        response = requestSpecification.put(endPoint);
        response.then().log().all();
    }

    public static int getStatusCode(){
        return response.getStatusCode();
    }

    public static String getDataFromJsonFile(String fileName) throws FileNotFoundException {
        String jsonFolderPath = "src/test/resources/data/";
        Scanner sc = new Scanner(new FileInputStream(jsonFolderPath + fileName));
        String body = sc.useDelimiter("\\Z").next();
        return body;
    }

}
