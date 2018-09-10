package com.revolut.transfer;

import com.revolut.transfer.service.RestService;
import com.revolut.transfer.service.impl.BaseRestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        RestService service = new BaseRestService(4040);
        try {
            service.start();
        } catch (SQLException e) {
            log.error("An exception occurred!");
            e.printStackTrace();
        }
    }
}