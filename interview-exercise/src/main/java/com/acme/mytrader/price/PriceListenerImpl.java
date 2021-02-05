package com.acme.mytrader.price;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.strategy.TradingStrategy;

public class PriceListenerImpl implements PriceListener {

    private ExecutionService trader;
    private TradingStrategy strategy;

    public PriceListenerImpl(ExecutionService trader, TradingStrategy strategy){
        this.trader = trader;
        this.strategy = strategy;
    }


    @Override
    public void priceUpdate(String security, double price) {
        if (strategy.getType().equalsIgnoreCase("buy") && security.equals(strategy.getSecurity()) && (price <= strategy.getThreshold())) {
            trader.buy(security, price, strategy.getVolume());
        }else{
            trader.sell(security, price, strategy.getVolume());
        }
    }
}
