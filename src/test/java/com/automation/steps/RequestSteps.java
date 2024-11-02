package com.automation.steps;

import com.automation.pojo.CreateBookingRequestPojo;
import com.automation.utils.ConfigReader;
import com.automation.utils.RestAssuredUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.io.FileNotFoundException;

public class RequestSteps {

    @Given("user wants to call {string} end point")
    public void userWantsToCallEndPoint(String endPoint) {
        RestAssuredUtils.setEndPoint(endPoint);
    }

    @And("set header {string} to {string}")
    public void setHeaderTo(String key, String value) {
        RestAssuredUtils.setHeader(key, value);
    }

    @And("set request body from file {string}")
    public void setRequestBodyFromFile(String filePath) throws Exception {
        String content = RestAssuredUtils.getDataFromJsonFile(filePath);
        ObjectMapper objectMapper = new ObjectMapper();
        CreateBookingRequestPojo requestPojo = objectMapper.readValue(content, CreateBookingRequestPojo.class);
        RestAssuredUtils.setBody(requestPojo);
        ConfigReader.setObject("request_pojo", requestPojo);
    }

    @When("user performs post call")
    public void userPerformsPostCall() {
        RestAssuredUtils.post();
    }

    @When("user performs get call")
    public void userPerformsGetCall() {
        RestAssuredUtils.get();
    }
}
