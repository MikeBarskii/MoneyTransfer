package com.revolut.transfer;

import com.revolut.transfer.service.RestService;
import com.revolut.transfer.service.impl.BaseRestService;

import static com.revolut.transfer.util.JsonUtil.json;
import static spark.Spark.get;

public class Application {
    public static void main(String[] args) {
        RestService service = new BaseRestService(4567);
        service.start();

        get("/hello/:name", (req, res) -> "Hello, " + req.params(":name"), json());
    }
}