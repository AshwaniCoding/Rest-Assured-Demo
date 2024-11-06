package com.automation.steps;

import com.automation.pojo.CreateBookingRequestPojo;
import com.automation.pojo.CreateBookingResponsePojo;
import com.automation.utils.ConfigReader;
import com.automation.utils.RestAssuredUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.Assert;

public class ResponseSteps {

    @Then("verify status code is {int}")
    public void verifyStatusCodeIs(int statusCode) {
        Assert.assertEquals(statusCode, RestAssuredUtils.getStatusCode());
    }

    @And("verify response body has same data as request")
    public void verifyResponseBodyHasSameDataAsRequest() {
        Response response = RestAssuredUtils.getResponse();
        CreateBookingResponsePojo responsePojo = response.as(CreateBookingResponsePojo.class);
        CreateBookingRequestPojo requestPojo = (CreateBookingRequestPojo) ConfigReader.getObject("request_pojo");
        Assert.assertTrue(requestPojo.equals(responsePojo.getBooking()));
    }

    @And("verify response body has field {string} is {string}")
    public void verifyResponseBodyHasFieldIs(String jsonPath, String value) {
        Assert.assertEquals(value, RestAssuredUtils.getResponseFieldValue(jsonPath));
    }

    @And("verify response schema is {string}")
    public void verifyResponseSchemaIs(String fileName) {
        Response response = RestAssuredUtils.getResponse();
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("data/"+fileName));
    }

    @And("store the {string} from the response")
    public void storeTheFromTheResponse(String key) {
        ConfigReader.setProperty(key,RestAssuredUtils.getResponseFieldValue(key));
    }

    @And("verify response body has field {string}")
    public void verifyResponseBodyHasField(String jsonPath) {
        Assert.assertTrue(RestAssuredUtils.isFieldAvailable(jsonPath));
    }

    @And("verify response body has not field {string}")
    public void verifyResponseBodyHasNotField(String field) {
        Assert.assertFalse(RestAssuredUtils.isFieldAvailable(field));
    }
}
