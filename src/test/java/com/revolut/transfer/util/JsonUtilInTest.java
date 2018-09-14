package com.revolut.transfer.util;

import com.google.gson.Gson;

public class JsonUtilInTest {
    private JsonUtilInTest() {
    }

    public static String toJson(Object o) {
        return new Gson().toJson(o);
    }

    public static <T> T convertToObject(String json, Class<T> modelClass){
        return new Gson().fromJson(json, modelClass);
    }

    public static <T> T convertToObject(byte[] json, Class<T> modelClass){
       return convertToObject(new String(json), modelClass);
    }
}
