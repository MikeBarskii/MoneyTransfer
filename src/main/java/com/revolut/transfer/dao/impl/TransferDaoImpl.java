package com.revolut.transfer.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.revolut.transfer.dao.TransferDao;
import com.revolut.transfer.model.Transfer;

import java.sql.SQLException;

public class TransferDaoImpl extends BaseDaoImpl<Transfer, Long> implements TransferDao {

    protected TransferDaoImpl() throws SQLException {
        super(new JdbcPooledConnectionSource("jdbc:h2:mem:moneytransfer"), Transfer.class);
    }
}
