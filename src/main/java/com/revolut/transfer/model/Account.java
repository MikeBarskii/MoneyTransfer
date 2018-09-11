package com.revolut.transfer.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.revolut.transfer.model.enums.Currency;

import java.math.BigDecimal;

@DatabaseTable(tableName = "accounts")
public class Account {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, canBeNull = false)
    private Customer customer;

    @DatabaseField
    private BigDecimal balance;

    @DatabaseField()
    private Currency currency;

    public Account() {
    }

    public Account(Customer customer, BigDecimal balance, Currency currency) {
        this.customer = customer;
        this.balance = balance;
        this.currency = currency;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setCurrency(String currency) {
        this.currency = Currency.valueOf(currency);
    }

    @Override
    public String toString() {
        return "Account {" +
                "customer= " + customer +
                ", currency= " + currency.toString() +
                '}';
    }
}
