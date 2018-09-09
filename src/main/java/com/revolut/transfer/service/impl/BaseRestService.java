package com.revolut.transfer.service.impl;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.revolut.transfer.controller.CustomerController;
import com.revolut.transfer.controller.TransferController;
import com.revolut.transfer.dao.CustomerDao;
import com.revolut.transfer.dao.TransferDao;
import com.revolut.transfer.dao.impl.CustomerDaoImpl;
import com.revolut.transfer.dao.impl.TransferDaoImpl;
import com.revolut.transfer.db.DbInit;
import com.revolut.transfer.service.CustomerService;
import com.revolut.transfer.service.RestService;
import com.revolut.transfer.service.TransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

import java.sql.SQLException;

public class BaseRestService implements RestService {
    private static final Logger log = LoggerFactory.getLogger(BaseRestService.class);

    private final int port;
    private ConnectionSource connectionSource;

    public BaseRestService(int port) {
        this.port = port;
        try {
            connectionSource = new JdbcPooledConnectionSource("jdbc:h2:mem:moneytransfer");
            DbInit.initEntityTables(connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() throws SQLException {
        Spark.port(port);
        log.info("The application is launched. Port: {}", port);

        CustomerDao customerDao = new CustomerDaoImpl(connectionSource);
        CustomerService customerService = new CustomerServiceImpl(customerDao);
        CustomerController customerController = new CustomerController(customerService);
        customerController.configureRoutes();

        TransferDao transferDao = new TransferDaoImpl(connectionSource);
        TransferService transferService = new TransferServiceIml(transferDao);
        TransferController transferController = new TransferController(transferService);
        transferController.configureRoutes();
    }


}
