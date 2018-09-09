package com.revolut.transfer.db;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.revolut.transfer.dao.impl.CustomerDaoImpl;
import com.revolut.transfer.model.Customer;
import com.revolut.transfer.service.CustomerService;
import com.revolut.transfer.service.impl.CustomerServiceImpl;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class DbInit {
    private static final List<Class> ENTITY_TABLES = Arrays.asList(Customer.class);

    public static void initEntityTables(ConnectionSource connectionSource) throws SQLException {
        createEntityTables(connectionSource);
        fillEntityTablesByDefault(connectionSource);
    }

    private static void createEntityTables(ConnectionSource connectionSource) throws SQLException {
        for (Class<?> entityClass : ENTITY_TABLES)
            TableUtils.createTable(connectionSource, entityClass);
    }

    private static void fillEntityTablesByDefault(ConnectionSource connectionSource) throws SQLException {
        CustomerService customerService = new CustomerServiceImpl(new CustomerDaoImpl(connectionSource));
        Customer customer = new Customer("Mike", "Barskiy", "+7", "m");
        customerService.addCustomer(customer);
    }
}
