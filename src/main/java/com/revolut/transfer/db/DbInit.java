package com.revolut.transfer.db;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.revolut.transfer.model.Account;
import com.revolut.transfer.model.Customer;
import com.revolut.transfer.model.Transfer;
import com.revolut.transfer.model.enums.Currency;
import com.revolut.transfer.service.AccountService;
import com.revolut.transfer.service.CustomerService;
import com.revolut.transfer.util.AppInjector;

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
            new Account(DEFAULT_CUSTOMERS.get(0), BigDecimal.valueOf(10_000), Currency.AUD),
            new Account(DEFAULT_CUSTOMERS.get(1), BigDecimal.valueOf(25_000), Currency.USD),
            new Account(DEFAULT_CUSTOMERS.get(2), BigDecimal.valueOf(50_000), Currency.USD),
            new Account(DEFAULT_CUSTOMERS.get(3), BigDecimal.valueOf(100_000), Currency.RUB),
            new Account(DEFAULT_CUSTOMERS.get(4), BigDecimal.valueOf(250_000), Currency.CAD)
    );

    public static void initEntityTables() throws SQLException {
        createEntityTables();
        fillEntityTablesByDefault();
    }

    private static void createEntityTables() throws SQLException {
        Injector injector = Guice.createInjector(new AppInjector());
        ConnectionSource connectionSource = injector.getInstance(ConnectionSource.class);

        for (Class<?> entityClass : ENTITY_TABLES)
            TableUtils.createTableIfNotExists(connectionSource, entityClass);
    }

    private static void fillEntityTablesByDefault() throws SQLException {
        fillCustomerTableByDefault();
        fillAccountTableByDefault();
    }

    private static void fillCustomerTableByDefault() throws SQLException {
        Injector injector = Guice.createInjector(new AppInjector());
        CustomerService customerService = injector.getInstance(CustomerService.class);
        for (Customer customer: DEFAULT_CUSTOMERS)
            customerService.addCustomer(customer);
        //customerService.addCustomers(DEFAULT_CUSTOMERS);
    }

    private static void fillAccountTableByDefault() throws SQLException {
        Injector injector = Guice.createInjector(new AppInjector());
        AccountService accountService = injector.getInstance(AccountService.class);
        accountService.addAccounts(DEFAULT_ACCOUNTS);
    }
}
