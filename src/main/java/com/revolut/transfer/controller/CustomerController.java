package com.revolut.transfer.controller;

import com.google.inject.Inject;
import com.revolut.transfer.service.CustomerService;

import static com.revolut.transfer.util.JsonUtil.json;
import static spark.Spark.*;

public class CustomerController {

    @Inject
    public CustomerController(CustomerService customerService) {
        path("/customers", () -> {
            get("/", (req, res) -> customerService.getCustomers(), json());

            after("/*", (req, res) -> res.type("application/json"));
        });

    }

}
