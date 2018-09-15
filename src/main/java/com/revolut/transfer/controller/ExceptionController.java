package com.revolut.transfer.controller;

import com.google.gson.JsonObject;
import com.revolut.transfer.exception.AccountDoesntHaveEnoughMoney;
import com.revolut.transfer.exception.TransferTheSameAccountException;
import org.eclipse.jetty.http.HttpStatus;

import static spark.Spark.exception;
import static spark.Spark.notFound;

public class ExceptionController {

    public ExceptionController() {
        exception(AccountDoesntHaveEnoughMoney.class, (ex, req, res) -> {
            res.status(HttpStatus.BAD_REQUEST_400);
            res.type("application/json");
            res.body(getJsonException(ex.getMessage()));
        });

        exception(TransferTheSameAccountException.class, (ex, req, res) -> {
            res.status(HttpStatus.BAD_REQUEST_400);
            res.type("application/json");
            res.body(getJsonException(ex.getMessage()));
        });

        notFound((req, res) -> {
            res.status(HttpStatus.NOT_FOUND_404);
            res.type("application/json");
            res.body(getNotFoundJsonException());
            return res.body();
        });
    }

    private String getJsonException(String message) {
        JsonObject json = new JsonObject();
        json.addProperty("status", "ERROR");
        json.addProperty("message", message);
        return json.toString();
    }

    private String getNotFoundJsonException() {
        JsonObject json = new JsonObject();
        json.addProperty("status", "ERROR");
        json.addProperty("code", HttpStatus.NOT_FOUND_404);
        json.addProperty("message", "Not Found");
        return json.toString();
    }
}
