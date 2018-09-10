package com.revolut.transfer.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.revolut.transfer.dao.CustomerDao;
import com.revolut.transfer.model.Customer;

import java.sql.SQLException;
import java.util.List;

public class CustomerDaoImpl extends BaseDaoImpl<Customer, Long> implements CustomerDao {

    public CustomerDaoImpl() throws SQLException {
        super(new JdbcPooledConnectionSource("jdbc:h2:mem:moneytransfer"), Customer.class);
    }

    @Override
    public List<Customer> findBySurname(String surname) throws SQLException {
        return super.queryForEq("surname", surname);
    }
}
