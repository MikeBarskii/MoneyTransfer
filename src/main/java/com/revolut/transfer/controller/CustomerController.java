package com.revolut.transfer.controller;

import com.google.inject.Inject;
import com.revolut.transfer.service.CustomerService;
import com.revolut.transfer.util.JsonUtil;
import spark.Spark;

import static spark.Spark.path;

public class CustomerController {

    @Inject
    public CustomerController(CustomerService customerService) {
        path("/api/users", () -> Spark.get("", (req, res) -> customerService.getCustomers(), JsonUtil.json()));
    }

}
