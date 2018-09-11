package com.revolut.transfer.controller;

import com.google.inject.Inject;
import com.revolut.transfer.service.AccountService;
import org.eclipse.jetty.http.HttpStatus;

import static com.revolut.transfer.util.JsonUtil.json;
import static spark.Spark.get;
import static spark.Spark.path;

public class AccountController {
    @Inject
    public AccountController(AccountService accountService) {
        path("/accounts", () -> {
            get("/", (req, res) -> accountService.getAccounts(), json());

            get("/:id", (req, res) -> {
                if (!isNumeric(req.params(":id"))) {
                    res.status(HttpStatus.BAD_REQUEST_400);
                    return res;
                }

                long accountId = Long.valueOf(req.params(":id"));
                return accountService.getAccount(accountId);
            });
        });
    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
