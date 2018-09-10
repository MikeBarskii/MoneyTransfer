package com.revolut.transfer.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.revolut.transfer.dao.AccountDao;
import com.revolut.transfer.model.Account;

import java.sql.SQLException;

public class AccountDaoImpl extends BaseDaoImpl<Account, Long> implements AccountDao {

    public AccountDaoImpl() throws SQLException {
        super(new JdbcPooledConnectionSource("jdbc:h2:mem:moneytransfer"), Account.class);
    }
}
