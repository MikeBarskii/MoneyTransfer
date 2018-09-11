package com.revolut.transfer.controller;

import com.google.inject.Inject;
import com.revolut.transfer.service.AccountService;

import static com.revolut.transfer.util.JsonUtil.json;
import static spark.Spark.*;

public class AccountController {
    @Inject
    public AccountController(AccountService accountService) {
        path("/accounts", () -> {
            get("/", (req, res) -> accountService.getAccounts(), json());

            get("/:id", (req, res) -> {
                long accountId = Long.valueOf(req.params(":id"));
                return accountService.getAccount(accountId);
            }, json());

            after("/*", (req, res) -> res.type("application/json"));
        });
    }

}
