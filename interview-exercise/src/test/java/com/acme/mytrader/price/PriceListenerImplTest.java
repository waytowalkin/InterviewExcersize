package com.acme.mytrader.price;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.execution.ExecutionServiceImpl;
import com.acme.mytrader.strategy.Trade;
import com.acme.mytrader.strategy.TradingStrategy;
import junit.framework.TestCase;
import org.junit.Test;
import java.util.ArrayList;

public class PriceListenerImplTest extends TestCase {

    @Test
    public void testPriceListener() {
        String security = "IBM";
        int volume = 10;
        ExecutionService trader = new ExecutionServiceImpl();
        TradingStrategy strategy = new TradingStrategy(security, Trade.BUY, 50.0, volume);
        PriceListenerImpl price = new PriceListenerImpl(trader, strategy);
        price.priceUpdate("IBM", 50);
        int expectedSize = 5;
        ArrayList<Trade> actualTrades = strategy.getTrades();
        assertEquals(expectedSize, actualTrades.size());
    }

    @Test
    public void testPriceUpdateBuyWithZEROTrades() {
        String security = "IBM";
        int volume = 0;
        ExecutionService trader = new ExecutionServiceImpl();
        TradingStrategy strategy = new TradingStrategy(security, Trade.BUY, 50.0, volume);
        PriceListenerImpl price = new PriceListenerImpl(trader, strategy);
        price.priceUpdate("IBM", 50);
        int expectedSize = 0;
        ArrayList<Trade> actualTrades = strategy.getTrades();
        assertEquals(expectedSize, actualTrades.size());
    }


    @Test
    public void testPriceUpdateSellTrades() {

        String security = "IBM";
        int volume = 10;
        ExecutionService trader = new ExecutionServiceImpl();
        TradingStrategy strategy = new TradingStrategy(security, Trade.BUY, 50.0, volume);
        PriceListenerImpl price = new PriceListenerImpl(trader, strategy);
        price.priceUpdate("IBM", 50);
        ArrayList<Trade> buyTrades = strategy.getTrades();
        TradingStrategy strategy1 = new TradingStrategy(security, Trade.SELL, 50.0, volume);
        PriceListenerImpl price1 = new PriceListenerImpl(trader, strategy1);
        price1.priceUpdate("IBM", 50);
        int expectedSize = buyTrades.size() - 5;
        ArrayList<Trade> actualTrades = strategy1.getTrades();
        assertEquals(expectedSize, actualTrades.size());
    }


}