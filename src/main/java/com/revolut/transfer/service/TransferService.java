package com.revolut.transfer.service;

import com.revolut.transfer.exception.AccountDoesntHaveEnoughMoney;
import com.revolut.transfer.exception.TransferTheSameAccountException;
import com.revolut.transfer.model.Transfer;

import java.sql.SQLException;
import java.util.Collection;

public interface TransferService {

    Collection<Transfer> getTransfers() throws SQLException;

    Transfer getTransfer(long transferId) throws SQLException;

    void createTransfers(Collection<Transfer> transfers) throws SQLException;

    void createTransfer(Transfer transfer) throws SQLException, TransferTheSameAccountException, AccountDoesntHaveEnoughMoney;
}
