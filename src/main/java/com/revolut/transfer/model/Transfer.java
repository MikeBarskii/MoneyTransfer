package com.revolut.transfer.model;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class Transfer {
    private long id;
    private Account sender;
    private Account receiver;
    private BigDecimal amount;
    private ZonedDateTime time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }

    public Transfer(long id, Account sender, Account receiver, BigDecimal amount, ZonedDateTime time) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.time = time;
    }

}
