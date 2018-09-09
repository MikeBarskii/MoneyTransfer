package com.revolut.transfer.controller;

import com.revolut.transfer.service.TransferService;

import static com.revolut.transfer.util.JsonUtil.json;
import static spark.Spark.get;
import static spark.Spark.path;

public class TransferController {
    private TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    public void configureRoutes() {
        path("/api/transfers", () -> {
            get("", (req, res) -> transferService.getTransfers(), json());

            get("/:id", (req, res) -> {
                Long transferId = Long.valueOf(req.params(":id"));
                return transferService.getTransfer(transferId);
            }, json());

        });
    }

}
