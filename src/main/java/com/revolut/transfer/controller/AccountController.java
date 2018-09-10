package com.revolut.transfer.controller;

import com.google.inject.Inject;
import com.revolut.transfer.service.AccountService;
import com.revolut.transfer.util.JsonUtil;
import spark.Spark;

import static spark.Spark.get;
import static spark.Spark.path;

public class AccountController {
    @Inject
    public AccountController(AccountService accountService) {
        path("/api/accounts", () -> {
            Spark.get("", (req, res) -> accountService.getAccounts(), JsonUtil.json());

            get("/:id", (req, res) -> {
                long accountId = Long.valueOf(req.params(":id"));
                return accountService.getAccount(accountId);
            });
        });
    }
}
