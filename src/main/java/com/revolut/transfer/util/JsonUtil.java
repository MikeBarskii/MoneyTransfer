package com.revolut.transfer.util;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class JsonUtil {
    private JsonUtil() {
    }

    private static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static ResponseTransformer json() {
        return JsonUtil::toJson;
    }

    public static <T> T convertToObject(String json, Class<T> modelClass){
        return new Gson().fromJson(json, modelClass);
    }

}
