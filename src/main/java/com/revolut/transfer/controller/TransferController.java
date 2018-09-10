package com.revolut.transfer.controller;

import com.google.inject.Inject;
import com.revolut.transfer.model.Transfer;
import com.revolut.transfer.service.TransferService;
import com.revolut.transfer.util.JsonUtil;

import static spark.Spark.*;

public class TransferController {
    private static final String APPLICATION_JSON_TYPE = "application/json";

    @Inject
    public TransferController(final TransferService transferService) {
        path("/api/transfers", () -> {
            get("", (req, res) -> transferService.getTransfers());

            post("/create", APPLICATION_JSON_TYPE, (req, res) -> {
                Transfer transfer = JsonUtil.convertToTransfer(req.body());
                transferService.createTransfer(transfer);
                return res;
            });
        });
    }
}
