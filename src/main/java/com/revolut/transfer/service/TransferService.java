package com.revolut.transfer.service;

import com.revolut.transfer.model.Transfer;

import java.util.List;

public interface TransferService {

    Transfer getTransfer(long id);

    List<Transfer> getTransfers();

}
