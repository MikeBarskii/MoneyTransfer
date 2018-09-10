package com.revolut.transfer.service;

import com.revolut.transfer.model.Transfer;

import java.sql.SQLException;
import java.util.Collection;

public interface TransferService {

    Collection<Transfer> getTransfers() throws SQLException;

    Transfer getTransfer(long transferId);

    void addTransfers(Collection<Transfer> transfers) throws SQLException;

    void addTransfer(Transfer transfer) throws SQLException;
}
