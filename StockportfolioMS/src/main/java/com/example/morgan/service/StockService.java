package com.example.morgan.service;

import com.example.morgan.modal.Stock;

import java.util.List;

public interface StockService {
    public abstract Stock createStock(Stock stock);

    public abstract void updateStock(int id, Stock stock);

    public abstract Stock getStock(int id);

    public abstract List<Stock> getStocks();

    public abstract void deleteStock(int id);

    public abstract boolean isStockExist(int id);
}
