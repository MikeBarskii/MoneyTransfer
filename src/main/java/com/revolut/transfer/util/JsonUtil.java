package com.revolut.transfer.util;

import com.google.gson.Gson;
import com.revolut.transfer.model.Transfer;
import spark.ResponseTransformer;

public class JsonUtil {
    private static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static ResponseTransformer json() {
        return JsonUtil::toJson;
    }

    public static Transfer convertToTransfer(String json) {
        return new Gson().fromJson(json, Transfer.class);
    }
}
