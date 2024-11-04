package com.automation.steps;

import com.automation.pojo.CreateBookingRequestPojo;
import com.automation.utils.ConfigReader;
import com.automation.utils.RestAssuredUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
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
        String body = RestAssuredUtils.getDataFromJsonFile(filePath);
        RestAssuredUtils.setBody(filePath);
    }

    @When("user performs post call")
    public void userPerformsPostCall() {
        RestAssuredUtils.post();
    }

    @When("user performs get call")
    public void userPerformsGetCall() {
        RestAssuredUtils.get();
    }

    @When("user wants to call {string} end point with {string}")
    public void userWantsToCallEndPointWith(String endPoint, String key) {
        RestAssuredUtils.setEndPoint(endPoint + ConfigReader.getProperty(key));
    }

    @And("set header value {string} in {string}")
    public void setHeaderValueIn(String value, String key) {
        RestAssuredUtils.setHeader(key, "token=" + ConfigReader.getProperty(value));
    }

    @And("set request body from file {string} as pojo {string}")
    public void setRequestBodyFromFileAsPojo(String filename, String pojoClassName) throws Exception {
        String content = RestAssuredUtils.getDataFromJsonFile(filename);
        ObjectMapper objectMapper = new ObjectMapper();

        Class<?> pojoClass = Class.forName("com.automation.pojo." + pojoClassName);

        Object requestPojo = objectMapper.readValue(content, pojoClass);
        RestAssuredUtils.setBody(requestPojo);
        ConfigReader.setObject("request_pojo", requestPojo);
    }

    @And("user performs put call")
    public void userPerformsPutCall() {
        RestAssuredUtils.put();
    }
}
