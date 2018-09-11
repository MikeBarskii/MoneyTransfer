package com.revolut.transfer.model.enums;

public enum Currency {
    AUD(1.41),
    CAD(1.32),
    EUR(0.86),
    RUB(70.51),
    UAH(28.27),
    USD(1);

    private final double rate;

    Currency(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }

    public static double exchangeRate(Currency from, Currency to) {
        return to.getRate()/from.getRate();
    }
}
