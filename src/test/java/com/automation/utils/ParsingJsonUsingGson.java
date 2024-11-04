package com.automation.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ParsingJsonUsingGson {
    public static void main(String[] args) throws Exception {
        String content = RestAssuredUtils.getDataFromJsonFile("Json_Gson_Example.json");
        JsonElement jsonParser = JsonParser.parseString(content);
        JsonObject jsonObject = jsonParser.getAsJsonObject();
        System.out.println(jsonObject.get("bookStoreId").getAsInt());
        System.out.println(jsonObject.get("bookStoreName").getAsString());
        JsonArray jsonArray = jsonObject.get("books").getAsJsonArray();

        for(int i =0;i<jsonArray.size();i++){
            System.out.println("----------Book "+ (i+1) + "-------------");
            System.out.println("Title: " + jsonArray.get(i).getAsJsonObject().get("bookName").getAsString());
            System.out.println("Price: " + jsonArray.get(i).getAsJsonObject().get("price").getAsInt());
            System.out.println("Is Available: " + jsonArray.get(i).getAsJsonObject().get("isAvailable").getAsBoolean());
        }

    }
}
