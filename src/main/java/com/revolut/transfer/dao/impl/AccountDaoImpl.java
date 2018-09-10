package com.revolut.transfer.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.revolut.transfer.dao.AccountDao;
import com.revolut.transfer.model.Account;

import java.sql.SQLException;

public class AccountDaoImpl extends BaseDaoImpl<Account, Long> implements AccountDao {

    public AccountDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Account.class);
    }

}
