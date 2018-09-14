package com.revolut.transfer.entity;

import com.revolut.transfer.model.enums.Currency;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CurrencyTest {

    @Test
    public void testCheckCurrencyEquality() {
        assertEquals(Currency.USD, Currency.USD);
    }

    @Test
    public void testCheckExchangeRateBetweenSameCurrencies() {
        assertEquals(1, Currency.exchangeRate(Currency.USD, Currency.USD), 0.0);
    }

    @Test
    public void testCheckExchangeRateBetweenDifferenctCurrencies() {
        assertEquals(1.32, Currency.exchangeRate(Currency.USD, Currency.CAD), 0.0);
    }
}
