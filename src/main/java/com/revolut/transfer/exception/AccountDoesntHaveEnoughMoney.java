package com.revolut.transfer.exception;

import com.revolut.transfer.model.Account;

import java.math.BigDecimal;

public class AccountDoesntHaveEnoughMoney extends Exception {
    public AccountDoesntHaveEnoughMoney(Account account, BigDecimal amount) {
        super("Account with id: " + account.getId() + " doesn't have enough money: " + amount + " " + account.getCurrency());
    }
}
