package com.revolut.transfer.exception;

public class TransferTheSameAccountException extends Exception {
    @Override
    public String getMessage() {
        return "Can't send money to the same account!";
    }
}
