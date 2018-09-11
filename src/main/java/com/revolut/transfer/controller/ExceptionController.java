package com.revolut.transfer.controller;
import com.revolut.transfer.exception.AccountDoesntHaveEnoughMoney;
import com.revolut.transfer.exception.TransferTheSameAccountException;
import org.eclipse.jetty.http.HttpStatus;

import static spark.Spark.exception;
import static spark.Spark.notFound;

public class ExceptionController {

    public ExceptionController() {
        exception(AccountDoesntHaveEnoughMoney.class, (ex, req, res) -> {
            res.status(HttpStatus.BAD_REQUEST_400);
            res.body(ex.getMessage());
        });

        exception(TransferTheSameAccountException.class, (ex, req, res) -> {
            res.status(HttpStatus.BAD_REQUEST_400);
            res.body(ex.getMessage());
        });

        notFound((req, res) -> {
            res.type("application/json");
            return "{\"code\":\"404\",\"message\":\"Not Found\"}";
        });
    }
}
