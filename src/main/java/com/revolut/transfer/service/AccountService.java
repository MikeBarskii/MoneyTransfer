package com.revolut.transfer.service;

import com.revolut.transfer.model.Account;

import java.util.List;

public interface AccountService {

    List<Account> getAllAccounts();

    Account getAccount(long id);

    void deleteAccount(long id);
}
