package com.revolut.transfer.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtilInTest {
    private JsonUtilInTest() {
    }

    public static String toJson(Object o) {
        return new Gson().toJson(o);
    }

    public static <T> T convertToObject(String json, Class<T> modelClass) {
        return new Gson().fromJson(json, modelClass);
    }

    public static <T> T convertToObject(byte[] json, Class<T> modelClass) {
        return convertToObject(new String(json), modelClass);
    }

    public static String getJsonProperty(String json, String property) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(json);
        return jsonObject.get(property).getAsString();
    }

    public static String getJsonProperty(byte[] json, String property) {
        return getJsonProperty(new String(json), property);
    }
}
