package com.automation.steps;

import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class Hooks {

    @Before
    public void setUp(){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }
}
