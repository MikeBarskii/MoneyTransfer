package com.revolut.transfer.service;

import com.revolut.transfer.model.Account;

import java.sql.SQLException;
import java.util.Collection;

public interface AccountService {

    Collection<Account> getAccounts() throws SQLException;

    Account getAccount(long accountId) throws SQLException;

    void addAccounts(Collection<Account> accounts) throws SQLException;

    void addAccount(Account account) throws SQLException;

    void updateAccounts(Collection<Account> accounts) throws SQLException;

    void updateAccount(Account account) throws SQLException;

    void deleteAccount(Account account) throws SQLException;

    void deleteAccount(long id) throws SQLException;
}
