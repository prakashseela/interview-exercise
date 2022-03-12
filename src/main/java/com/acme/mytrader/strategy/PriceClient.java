package com.acme.mytrader.strategy;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.price.PriceListener;

public class PriceClient implements PriceListener {

    private String stock;
    private double limit;
    private ExecutionService service;

    public PriceClient(String stock, double limit, ExecutionService service){
        this.stock = stock;
        this.limit = limit;
        this.service = service;
    }

    @Override
    public void priceUpdate(String security, double price) {
        if (security.equalsIgnoreCase(stock) && price < limit){
            service.buy(security, price, 100);
        }
    }
}
