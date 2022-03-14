package com.acme.mytrader.strategy;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceSource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
public class TradingStrategy implements Runnable {

    private final PriceListener listener;

    private final String stock;

    private PriceSource priceSource;

    public TradingStrategy(PriceListener listener, String stock) {
        this.listener = listener;
        this.stock = stock;
    }

    //server thread.
    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        while (true) {
            double newValue = 54.00;
            //monitor stock prices and update the listener,
            //if stock has moved out of range.
            listener.priceUpdate(stock, newValue);
        }
    }

    public static void main(String[] args) {
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(cores);
        //Instantiate the class object from third party library.
        ExecutionService stockService = null;
        String stockName = "ACME:EUR";
        PriceListener listener = new PriceClient(stockName, 55.0, stockService);
        TradingStrategy stockServer = new TradingStrategy(listener, stockName);
        executorService.submit(stockServer);
    }

}
