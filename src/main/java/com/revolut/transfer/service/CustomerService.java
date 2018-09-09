package com.revolut.transfer.service;

import com.revolut.transfer.model.Customer;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface CustomerService {

    void addCustomers(Collection<Customer> customers) throws SQLException;

    void addCustomer(Customer customer) throws SQLException;

    List<Customer> getCustomers() throws SQLException;
}
