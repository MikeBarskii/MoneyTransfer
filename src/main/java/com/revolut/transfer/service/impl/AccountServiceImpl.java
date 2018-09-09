package com.revolut.transfer.service.impl;

import com.revolut.transfer.model.Account;
import com.revolut.transfer.service.AccountService;

import java.util.ArrayList;
import java.util.List;

public class AccountServiceImpl implements AccountService {

    @Override
    public List<Account> getAllAccounts() {
        return new ArrayList<>();
    }

    @Override
    public Account getAccount(long id) {
        return new Account();
    }

    @Override
    public void deleteAccount(long id) {
    }
}
