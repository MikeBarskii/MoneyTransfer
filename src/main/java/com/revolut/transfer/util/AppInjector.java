package com.revolut.transfer.util;

import com.google.inject.AbstractModule;
import com.revolut.transfer.dao.AccountDao;
import com.revolut.transfer.dao.CustomerDao;
import com.revolut.transfer.dao.TransferDao;
import com.revolut.transfer.dao.impl.AccountDaoImpl;
import com.revolut.transfer.dao.impl.CustomerDaoImpl;
import com.revolut.transfer.dao.impl.TransferDaoImpl;
import com.revolut.transfer.service.AccountService;
import com.revolut.transfer.service.CustomerService;
import com.revolut.transfer.service.TransferService;
import com.revolut.transfer.service.impl.AccountServiceImpl;
import com.revolut.transfer.service.impl.CustomerServiceImpl;
import com.revolut.transfer.service.impl.TransferServiceIml;

public class AppInjector extends AbstractModule {
    @Override
    protected void configure() {
        bind(TransferService.class).to(TransferServiceIml.class);
        bind(TransferDao.class).to(TransferDaoImpl.class);

        bind(AccountService.class).to(AccountServiceImpl.class);
        bind(AccountDao.class).to(AccountDaoImpl.class);

        bind(CustomerService.class).to(CustomerServiceImpl.class);
        bind(CustomerDao.class).to(CustomerDaoImpl.class);
    }

}
