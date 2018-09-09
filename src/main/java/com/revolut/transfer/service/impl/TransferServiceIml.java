package com.revolut.transfer.service.impl;

import com.revolut.transfer.dao.TransferDao;
import com.revolut.transfer.model.Transfer;
import com.revolut.transfer.service.TransferService;

import java.util.List;

public class TransferServiceIml implements TransferService {

    private TransferDao transferDao;

    public TransferServiceIml(TransferDao transferDao) {
        this.transferDao = transferDao;
    }

    @Override
    public Transfer getTransfer(long id) {
        return null;
    }

    @Override
    public List<Transfer> getTransfers() {
        return null;
    }
}
