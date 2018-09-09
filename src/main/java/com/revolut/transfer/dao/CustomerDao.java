package com.revolut.transfer.dao;

import com.j256.ormlite.dao.Dao;
import com.revolut.transfer.model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDao extends Dao<Customer, Long> {
    List<Customer> findBySurname(String surname) throws SQLException;
}
