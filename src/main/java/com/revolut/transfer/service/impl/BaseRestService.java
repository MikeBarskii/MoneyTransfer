package com.revolut.transfer.service.impl;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.j256.ormlite.support.ConnectionSource;
import com.revolut.transfer.controller.AccountController;
import com.revolut.transfer.controller.CustomerController;
import com.revolut.transfer.controller.ExceptionController;
import com.revolut.transfer.controller.TransferController;
import com.revolut.transfer.dao.AccountDao;
import com.revolut.transfer.dao.CustomerDao;
import com.revolut.transfer.dao.TransferDao;
import com.revolut.transfer.db.DbInit;
import com.revolut.transfer.service.RestService;
import com.revolut.transfer.util.AppInjector;
import org.apache.log4j.Logger;
import spark.Spark;

import java.sql.SQLException;

public class BaseRestService implements RestService {
    private static final Logger log = Logger.getLogger(BaseRestService.class);
    private static final int DEFAULT_PORT = 4567;

    private int port = DEFAULT_PORT;

    public BaseRestService() {
    }

    public BaseRestService(int port) {
        this.port = port;
    }

    @Override
    public void start() {
        Spark.port(port);
        initInjectorInstances();

        try {
            DbInit.initEntityTables();
        } catch (SQLException e) {
            log.error("Something went wrong");
            e.printStackTrace();
        }
        log.info("The application is launched. Port: " + port);
    }

    @Override
    public void stop() {
        Spark.stop();
    }

    private void initInjectorInstances() {
        Injector injector = Guice.createInjector(new AppInjector());
        injector.getInstance(ConnectionSource.class);

        injector.getInstance(TransferController.class);
        injector.getInstance(TransferDao.class);
        injector.getInstance(TransferServiceIml.class);

        injector.getInstance(CustomerController.class);
        injector.getInstance(CustomerDao.class);
        injector.getInstance(CustomerServiceImpl.class);

        injector.getInstance(AccountController.class);
        injector.getInstance(AccountDao.class);
        injector.getInstance(AccountServiceImpl.class);

        injector.getInstance(ExceptionController.class);
    }
}
