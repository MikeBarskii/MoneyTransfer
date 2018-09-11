package com.revolut.transfer.service.impl;

import com.google.inject.Inject;
import com.revolut.transfer.dao.TransferDao;
import com.revolut.transfer.exception.AccountDoesntHaveEnoughMoney;
import com.revolut.transfer.exception.TransferTheSameAccountException;
import com.revolut.transfer.model.Account;
import com.revolut.transfer.model.Transfer;
import com.revolut.transfer.model.enums.Currency;
import com.revolut.transfer.service.AccountService;
import com.revolut.transfer.service.TransferService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.Collection;

public class TransferServiceIml implements TransferService {
    private final TransferDao transferDao;
    private final AccountService accountService;

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
    public Transfer createTransfer(Transfer transfer) throws SQLException,
            TransferTheSameAccountException, AccountDoesntHaveEnoughMoney {
        if (transfer.getSenderId() == transfer.getReceiverId()) {
            throw new TransferTheSameAccountException();
        }

        Account sender = accountService.getAccount(transfer.getSenderId());
        Account receiver = accountService.getAccount(transfer.getReceiverId());

        if (!isAccountContainEnoughMoney(sender, transfer.getAmount())) {
            throw new AccountDoesntHaveEnoughMoney(sender, transfer.getAmount());
        }

        changeBalanceForAccounts(sender, receiver, transfer.getAmount());
        return transferDao.createIfNotExists(transfer);
    }

    private void changeBalanceForAccounts(Account sender, Account receiver, BigDecimal amount) throws SQLException {
        changeSenderBalance(sender, amount);

        double exchangeRate = Currency.exchangeRate(sender.getCurrency(), receiver.getCurrency());
        changeReceiverBalance(receiver, amount, exchangeRate);
    }

    private void changeSenderBalance(Account sender, BigDecimal amount) throws SQLException {
        BigDecimal newSenderBalance = sender.getBalance().subtract(amount).setScale(2, RoundingMode.CEILING);
        sender.setBalance(newSenderBalance);
        accountService.updateAccount(sender);
    }

    private void changeReceiverBalance(Account receiver, BigDecimal amount, double exchangeRate) throws SQLException {
        BigDecimal amountAfterExchange = amount.multiply(BigDecimal.valueOf(exchangeRate));
        BigDecimal newReceiverBalance = receiver.getBalance().add(amountAfterExchange).setScale(2, RoundingMode.CEILING);
        receiver.setBalance(newReceiverBalance);
        accountService.updateAccount(receiver);
    }

    private boolean isAccountContainEnoughMoney(Account account, BigDecimal amount) {
        return account.getBalance().compareTo(amount) > 0;
    }
}
