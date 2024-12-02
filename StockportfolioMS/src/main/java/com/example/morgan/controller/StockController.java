package com.example.morgan.controller;

import java.util.List;

import com.example.morgan.exception.StockNotfoundException;
import com.example.morgan.modal.Stock;
import com.example.morgan.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@Slf4j
public class StockController
{
    @Autowired
    private StockService stockService;

    @RequestMapping(value = "/stocks", method = RequestMethod.POST)
    public ResponseEntity<Object> createStock(@RequestBody Stock stock)
    {
        log.info("Start :  Stock Creation ");
        stock = stockService.createStock(stock);

        return new ResponseEntity<>(stock.getId(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/stocks/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateStock(@PathVariable("id") int id,
                                                 @RequestBody Stock stock)
    {
        log.info("Start :  Stock Update");
        boolean isStockExist = stockService.isStockExist(id);
        if (isStockExist)
        {
            stockService.updateStock(id, stock);
            return new ResponseEntity<>("Stock is updated successsfully", HttpStatus.OK);
        }
        else
        {
            throw new StockNotfoundException();
        }
    }

    @RequestMapping(value = "/stocks/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getStock(@PathVariable("id") int id)
    {
        log.info("Start :  Stock Retrieve");
        boolean isStockExist = stockService.isStockExist(id);
        if (isStockExist)
        {
            Stock stock = stockService.getStock(id);
            return new ResponseEntity<>(stock, HttpStatus.OK);
        }
        else
        {
            throw new StockNotfoundException();
        }
    }

    @RequestMapping(value = "/stocks", method = RequestMethod.GET)
    public ResponseEntity<Object> getStocks()
    {
        log.info("Start :  Stocks list");
        List<Stock> stockList = stockService.getStocks();
        return new ResponseEntity<>(stockList, HttpStatus.OK);
    }

    @RequestMapping(value = "/stocks/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteStock(@PathVariable("id") int id)
    {
        log.info("Start :  Stock Delete");
        boolean isStockExist = stockService.isStockExist(id);
        if (isStockExist)
        {
            stockService.deleteStock(id);
            return new ResponseEntity<>("Stock is deleted successsfully", HttpStatus.OK);
        }
        else
        {
            throw new StockNotfoundException();
        }
    }
}
