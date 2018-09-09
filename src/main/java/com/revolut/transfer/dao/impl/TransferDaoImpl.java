package com.revolut.transfer.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.revolut.transfer.dao.TransferDao;
import com.revolut.transfer.model.Transfer;

import java.sql.SQLException;

public class TransferDaoImpl extends BaseDaoImpl<Transfer, Long> implements TransferDao {
    public TransferDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Transfer.class);
    }
}
