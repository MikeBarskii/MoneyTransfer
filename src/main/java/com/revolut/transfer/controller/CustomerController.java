package com.revolut.transfer.controller;

import com.google.inject.Inject;
import com.revolut.transfer.service.CustomerService;
import spark.Spark;

import static com.revolut.transfer.util.JsonUtil.json;
import static spark.Spark.path;

public class CustomerController {

    @Inject
    public CustomerController(CustomerService customerService) {
        path("/api/customers", () -> Spark.get("/", (req, res) -> customerService.getCustomers(), json()));
    }

}
