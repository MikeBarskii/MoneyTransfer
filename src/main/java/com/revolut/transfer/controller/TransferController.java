package com.revolut.transfer.controller;

import com.google.inject.Inject;
import com.revolut.transfer.model.Transfer;
import com.revolut.transfer.service.TransferService;
import com.revolut.transfer.util.JsonUtil;

import static com.revolut.transfer.util.JsonUtil.json;
import static spark.Spark.*;

public class TransferController {

    @Inject
    public TransferController(final TransferService transferService) {
        path("/transfers", () -> {
            get("/", (req, res) -> transferService.getTransfers(), json());

            get("/:id", (req, res) -> {
                long transferId = Long.valueOf(req.params(":id"));
                return transferService.getTransfer(transferId);
            }, json());

            post("/create", (req, res) -> {
                Transfer transfer = JsonUtil.convertToTransfer(req.body());
                return transferService.createTransfer(transfer);
            }, json());

            after("/*", (req, res) -> res.type("application/json"));
        });
    }

}
