package com.revolut.transfer.service.impl;

import com.revolut.transfer.dao.TransferDao;
import com.revolut.transfer.model.Transfer;
import com.revolut.transfer.service.TransferService;

import java.sql.SQLException;
import java.util.Collection;

public class TransferServiceIml implements TransferService {
    private TransferDao transferDao;

    public TransferServiceIml(TransferDao transferDao) {
        this.transferDao = transferDao;
    }

    @Override
    public Collection<Transfer> getTransfers() throws SQLException {
        return transferDao.queryForAll();
    }

    @Override
    public Transfer getTransfer(long transferId) {
        return null;
    }

    @Override
    public void addTransfers(Collection<Transfer> transfers) throws SQLException {
        transferDao.create(transfers);
    }

    @Override
    public void addTransfer(Transfer transfer) throws SQLException {
        transferDao.create(transfer);
    }
}
