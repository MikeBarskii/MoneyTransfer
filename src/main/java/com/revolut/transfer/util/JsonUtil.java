package com.revolut.transfer.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.revolut.transfer.model.Customer;
import com.revolut.transfer.model.Transfer;
import spark.ResponseTransformer;

import java.io.IOException;

public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    private static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static ResponseTransformer json() {
        return JsonUtil::toJson;
    }

    public static Transfer convertToTransfer(String json) throws IOException {
        return mapper.readValue(json, Transfer.class);
    }

    public static Customer convertToCustomer(String json) throws IOException {
        return mapper.readValue(json, Customer.class);
    }
}
