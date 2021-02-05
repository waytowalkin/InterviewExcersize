package com.acme.mytrader.strategy;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TradingStrategyTest {
    @Test
    public void testTradingStrategy() {
        String security = "IBM";
        int volume = 10;

        TradingStrategy strategy = new TradingStrategy(security, Trade.BUY, 50.0, volume);

        strategy.executeTradingStrategy();

        int expectedSize = 5;
        ArrayList<Trade> actualTrades = strategy.getTrades();

        assertEquals(expectedSize, actualTrades.size());

        ArrayList<Trade> expectedTrades = new ArrayList<>();

        int i = 0;
        double priceDelta = 10.0;
        double previousPrice = 50.0;

        while (i < expectedSize) {

            if (i != 0) {
                previousPrice = previousPrice - priceDelta;
            }

            Trade tempTrade = new Trade(security, Trade.BUY, previousPrice, volume);
            expectedTrades.add(tempTrade);

            i++;
        }

        assertEquals(expectedTrades, actualTrades);

    }

    @Test
    public void testTradingStrategyWithBuyWithZEROTrades() {
        String security = "IBM";
        int volume = 0;

        TradingStrategy strategy = new TradingStrategy(security, Trade.BUY, 50.0, volume);

        strategy.executeTradingStrategy();

        int expectedSize = 0;
        ArrayList<Trade> actualTrades = strategy.getTrades();

        assertEquals(expectedSize, actualTrades.size());

        ArrayList<Trade> expectedTrades = new ArrayList<>();

    }




    @Test
    public void testTradingStrategyWithBuyTrades() {
        String security = "IBM";
        int volume = 10;

        TradingStrategy strategy = new TradingStrategy(security, Trade.BUY, 50.0, volume);

        strategy.executeTradingStrategy();

        int expectedSize = 5;
        ArrayList<Trade> actualTrades = strategy.getTrades();

        assertEquals(expectedSize, actualTrades.size());

        ArrayList<Trade> expectedTrades = new ArrayList<>();

    }

    @Test
    public void testTradingStrategyWithSellZeroTrades() {
        String security = "IBM";
        int volume = 0;
        TradingStrategy strategy1 = new TradingStrategy(security, Trade.BUY, 50.0, volume);

        ArrayList<Trade> buyTrades = strategy1.getTrades();

        TradingStrategy strategy = new TradingStrategy(security, Trade.SELL, 50.0, volume);

        strategy.executeTradingStrategy();

        int expectedSize = buyTrades.size()-0;
        ArrayList<Trade> actualTrades = strategy.getTrades();

        assertEquals(expectedSize, actualTrades.size());
    }


    @Test
    public void testTradingStrategyWithSellTrades() {
        String security = "IBM";
        int volume = 10;
        TradingStrategy strategy1 = new TradingStrategy(security, Trade.BUY, 50.0, volume);

        ArrayList<Trade> buytrades = strategy1.getTrades();

        TradingStrategy strategy = new TradingStrategy(security, Trade.SELL, 50.0, volume);

        strategy.executeTradingStrategy();

        int expectedSize = buytrades.size()-5;
        ArrayList<Trade> actualTrades = strategy.getTrades();

        assertEquals(expectedSize, actualTrades.size());
    }

}
