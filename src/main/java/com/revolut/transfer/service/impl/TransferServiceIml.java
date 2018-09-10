package com.revolut.transfer.service.impl;

import com.google.inject.Inject;
import com.revolut.transfer.dao.TransferDao;
import com.revolut.transfer.exception.AccountDoesntHaveEnoughMoney;
import com.revolut.transfer.exception.TransferTheSameAccountException;
import com.revolut.transfer.model.Account;
import com.revolut.transfer.model.Transfer;
import com.revolut.transfer.service.AccountService;
import com.revolut.transfer.service.TransferService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class TransferServiceIml implements TransferService {
    private TransferDao transferDao;
    private AccountService accountService;

    @Inject
    public TransferServiceIml(TransferDao transferDao, AccountService accountService) {
        this.transferDao = transferDao;
        this.accountService = accountService;
    }

    @Override
    public Collection<Transfer> getTransfers() throws SQLException {
        return transferDao.queryForAll();
    }

    @Override
    public Transfer getTransfer(long transferId) throws SQLException {
        return transferDao.queryForId(transferId);
    }

    public void createTransfers(Collection<Transfer> transfers) throws SQLException {
        transferDao.create(transfers);
    }

    @Override
    public void createTransfer(Transfer transfer) throws SQLException, TransferTheSameAccountException, AccountDoesntHaveEnoughMoney {
        if (transfer.getSenderId() == transfer.getReceiverId()) {
            throw new TransferTheSameAccountException();
        }

        Account sender = accountService.getAccount(transfer.getSenderId());
        Account receiver = accountService.getAccount(transfer.getReceiverId());

        if (!isAccountContainEnoughMoney(sender, transfer.getAmount())) {
            throw new AccountDoesntHaveEnoughMoney(sender);
        }

        changeBalanceForAccounts(sender, receiver, transfer.getAmount());
        transferDao.create(transfer);
    }

    private void changeBalanceForAccounts(Account sender, Account receiver, BigDecimal amount) throws SQLException {
        BigDecimal newSenderBalance = sender.getBalance().subtract(amount);
        sender.setBalance(newSenderBalance);

        BigDecimal newReceiverBalance = receiver.getBalance().add(amount);
        receiver.setBalance(newReceiverBalance);

        accountService.updateAccounts(new ArrayList<>(Arrays.asList(sender, receiver)));
    }

    private boolean isAccountContainEnoughMoney(Account account, BigDecimal amount) {
        return account.getBalance().compareTo(amount) > 0;
    }
}
