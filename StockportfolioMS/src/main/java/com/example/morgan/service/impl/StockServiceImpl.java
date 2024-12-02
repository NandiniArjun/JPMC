package com.example.morgan.service.impl;

import java.util.List;

import com.example.morgan.modal.Stock;
import com.example.morgan.repository.StockRepository;
import com.example.morgan.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StockServiceImpl implements StockService
{

    @Autowired
    private StockRepository stockRepository;

    @Override
    public Stock createStock(Stock stock)
    {
        return stockRepository.save(stock);
    }

    @Override
    public void updateStock(int id, Stock stock)
    {
        stockRepository.updateStockByID(stock.getSymbol(), stock.getName(), stock.getCurrentPrice(),
                 stock.getPreviousPrice(), id);
    }

    @Override
    public Stock getStock(int id)
    {
        return stockRepository.findById(id).get();
    }

    @Override
    public List<Stock> getStocks()
    {
        return stockRepository.findAll();
    }

    @Override
    public void deleteStock(int id)
    {
        stockRepository.deleteById(id);
    }

    @Override
    public boolean isStockExist(int id)
    {
        int count = stockRepository.ifStockExistCount(id);

        return (count >= 1) ? true : false;
   }
}
