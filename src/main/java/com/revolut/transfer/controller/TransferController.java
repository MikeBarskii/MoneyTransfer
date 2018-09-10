package com.revolut.transfer.controller;

import com.revolut.transfer.model.Transfer;
import com.revolut.transfer.service.TransferService;
import com.revolut.transfer.util.JsonUtil;

import static spark.Spark.*;

public class TransferController implements RouteController {
    private static final String APPLICATION_JSON_TYPE = "application/json";

    private TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @Override
    public void configureRoutes() {
        path("/api/transfers", () -> {
            get("", (req, res) -> transferService.getTransfers());

            post("/create", APPLICATION_JSON_TYPE, (req, res) -> {
                Transfer transfer = JsonUtil.convertToTransfer(req.body());
                transferService.addTransfer(transfer);
                return res;
            });
        });
    }
}
