package com.revolut.transfer.service.impl;

import com.revolut.transfer.dao.CustomerDao;
import com.revolut.transfer.model.Customer;
import com.revolut.transfer.service.CustomerService;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private CustomerDao customerDao;

    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public void addCustomers(Collection<Customer> customers) throws SQLException {
        customerDao.create(customers);
    }

    @Override
    public void addCustomer(Customer customer) throws SQLException {
        customerDao.create(customer);
    }

    @Override
    public List<Customer> getCustomers() throws SQLException {
        return customerDao.queryForAll();
    }

}
