package com.revolut.transfer.controller;

import com.revolut.transfer.service.AccountService;

import static com.revolut.transfer.util.JsonUtil.json;
import static spark.Spark.get;
import static spark.Spark.path;

public class AccountController implements RouteController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void configureRoutes() {
        path("/api/accounts", () -> {
            get("", (req, res) -> accountService.getAccounts(), json());

            get("/:id", (req, res) -> {
                long accountId = Long.valueOf(req.params(":id"));
                return accountService.getAccount(accountId);
            });
        });
    }
}
