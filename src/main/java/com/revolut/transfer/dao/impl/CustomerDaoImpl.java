package com.revolut.transfer.dao.impl;

import com.google.inject.Inject;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.revolut.transfer.dao.CustomerDao;
import com.revolut.transfer.model.Customer;

import java.sql.SQLException;

public class CustomerDaoImpl extends BaseDaoImpl<Customer, Long> implements CustomerDao {

    @Inject
    public CustomerDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Customer.class);
    }
}
