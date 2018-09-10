package com.revolut.transfer.db;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.revolut.transfer.dao.AccountDao;
import com.revolut.transfer.dao.CustomerDao;
import com.revolut.transfer.dao.impl.AccountDaoImpl;
import com.revolut.transfer.dao.impl.CustomerDaoImpl;
import com.revolut.transfer.model.Account;
import com.revolut.transfer.model.Customer;
import com.revolut.transfer.model.Transfer;
import com.revolut.transfer.service.AccountService;
import com.revolut.transfer.service.CustomerService;
import com.revolut.transfer.service.impl.AccountServiceImpl;
import com.revolut.transfer.service.impl.CustomerServiceImpl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class DbInit {
    private static final List<Class> ENTITY_TABLES = Arrays.asList(Customer.class, Account.class, Transfer.class);
    private static final List<Customer> DEFAULT_CUSTOMERS = Arrays.asList(
            new Customer("Mike", "Barskiy", "+79818403837", "mikebarskiy@gmail.com"),
            new Customer("Ekaterina", "Khodosova", "+79818257940", "khodosova@mail.ru"),
            new Customer("Alexey", "Ivashin", "+79992003433", "ors95@yandex.ru"),
            new Customer("Maria", "Baeva", "+79818504877", "maria.baeva@gmail.com"),
            new Customer("Alexandra", "Ondrina", "+79527903939", "ondrina.a@yandex.ru")
    );

    private static final List<Account> DEFAULT_ACCOUNTS = Arrays.asList(
            new Account(DEFAULT_CUSTOMERS.get(0), BigDecimal.valueOf(10_000)),
            new Account(DEFAULT_CUSTOMERS.get(1), BigDecimal.valueOf(25_000)),
            new Account(DEFAULT_CUSTOMERS.get(2), BigDecimal.valueOf(50_000)),
            new Account(DEFAULT_CUSTOMERS.get(3), BigDecimal.valueOf(100_000)),
            new Account(DEFAULT_CUSTOMERS.get(4), BigDecimal.valueOf(250_000))
    );

    public static void initEntityTables(ConnectionSource connectionSource) throws SQLException {
        createEntityTables(connectionSource);
        fillEntityTablesByDefault(connectionSource);
    }

    private static void createEntityTables(ConnectionSource connectionSource) throws SQLException {
        for (Class<?> entityClass : ENTITY_TABLES)
            TableUtils.createTableIfNotExists(connectionSource, entityClass);
    }

    private static void fillEntityTablesByDefault(ConnectionSource connectionSource) throws SQLException {
        fillCustomerTableByDefault(connectionSource);
        fillAccountTableByDefault(connectionSource);
    }

    private static void fillCustomerTableByDefault(ConnectionSource connectionSource) throws SQLException {
        CustomerDao customerDao = new CustomerDaoImpl(connectionSource);
        CustomerService customerService = new CustomerServiceImpl(customerDao);
        customerService.addCustomers(DEFAULT_CUSTOMERS);
    }

    private static void fillAccountTableByDefault(ConnectionSource connectionSource) throws SQLException {
        AccountDao accountDao = new AccountDaoImpl(connectionSource);
        AccountService accountService = new AccountServiceImpl(accountDao);
        accountService.addAccounts(DEFAULT_ACCOUNTS);
    }
}
