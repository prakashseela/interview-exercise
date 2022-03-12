package com.acme.mytrader.strategy;

import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceSource;

import java.util.ArrayList;
import java.util.List;

public class StockServer implements Runnable, PriceSource {

    private final List<PriceListener> listeners_ = new ArrayList<>();
    private final Object lock = new Object();

    private PriceListener listener;

    private PriceSource priceSource;

    public StockServer(PriceListener listener) {
        this.listener = listener;
    }

    @Override
    public void addPriceListener(PriceListener listener) {
        synchronized (lock) {
            listeners_.add(listener);
        }
    }

    @Override
    public void removePriceListener(PriceListener listener) {
        synchronized (lock) {
            listeners_.remove(listener);
        }
    }

    public List<PriceListener> getListeners() {
        return listeners_;
    }

    //server thread.
    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        while (true) {
            double newValue = 54.00;
            //monitor stock prices and update the listener,
            //if stock has moved out of range.


            listener.priceUpdate("ACME:EUR", newValue);
        }
    }
}
