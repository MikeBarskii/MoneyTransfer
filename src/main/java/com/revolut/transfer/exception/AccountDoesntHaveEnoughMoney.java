package com.revolut.transfer.exception;

import java.math.BigDecimal;

public class AccountDoesntHaveEnoughMoney extends Exception {
    public AccountDoesntHaveEnoughMoney(long accountId, BigDecimal amount) {
        super("Account with id: " + accountId + " doesn't have enough money: " + amount);
    }
}
