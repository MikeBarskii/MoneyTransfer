package com.revolut.transfer.controller;

import com.revolut.transfer.service.CustomerService;

import static com.revolut.transfer.util.JsonUtil.json;
import static spark.Spark.get;
import static spark.Spark.path;

public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void configureRoutes() {
        path("/api/users", () -> {
            get("", (req, res) -> customerService.getCustomers(), json());

        });
    }

}
