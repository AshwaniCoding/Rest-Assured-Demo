package com.automation.utils;

import com.automation.pojo.CreateBookingRequestPojo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;

public class ConfigReader {

    static Properties prop;

    public static void initConfig() {
        try {
            prop = new Properties();
            prop.load(new FileReader("src/test/resources/config/config.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }

    public static void setProperty(String key, String value) {
        prop.setProperty(key, value);
    }

    public static Object getObject(String key) {
        return prop.get(key);
    }

    public static void setObject(String key, Object value) {
        prop.put(key, value);
    }

//    // This is testing method of setObject and getObject
//    public static void main(String[] args) throws Exception {
//        ConfigReader.initConfig();
//        String content = RestAssuredUtils.getDataFromJsonFile("create_booking.json");
//        ObjectMapper objectMapper = new ObjectMapper();
//        CreateBookingRequestPojo requestPojo = objectMapper.readValue(content, CreateBookingRequestPojo.class);
//        ConfigReader.setObject("requestPojo", requestPojo);
//
//        CreateBookingRequestPojo pojo = (CreateBookingRequestPojo) ConfigReader.getObject("requestPojo");
//        System.out.println(pojo.toString());
//    }


}