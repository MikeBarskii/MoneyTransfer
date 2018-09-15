package com.revolut.transfer;

import com.revolut.transfer.service.RestService;
import com.revolut.transfer.service.impl.BaseRestService;

public class Application {

    public static void main(String[] args) {
        RestService service = new BaseRestService();
        service.start();
    }
}