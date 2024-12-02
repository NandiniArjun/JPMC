package com.example.morgan.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.example.morgan.modal.Stock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class StockRepositoryTest {

    @Autowired
    private StockRepository stockRepository;

    @Test
    public void testSaveStock() {
        Stock stock = new Stock(1, "Joe", "IBM", 0, 0);
        Stock savedStock = stockRepository.save(stock);
        assertNotNull(savedStock);
        assertEquals("Joe", savedStock.getName());
    }

    @Test
    public void testGetStock() {
        Stock stock = new Stock(1, "Joe", "IBM", 0, 0);
        Stock savedStock = stockRepository.save(stock);
        Stock fetchedEmployee = stockRepository.findById(savedStock.getId()).orElse(null);
        assertNotNull(fetchedEmployee);
        assertEquals(savedStock.getId(), fetchedEmployee.getId());
    }

    @Test
    public void testGetListOfStocks() {
        Stock stock1 = new Stock(1, "Joe", "IBM", 0, 0);
        Stock stock2 = new Stock(2, "Deol", "IBM", 0, 0);
        stockRepository.save(stock1);
        stockRepository.save(stock2);
        List<Stock> stocks = stockRepository.findAll();
        assertNotNull(stocks);
        assertEquals(2, stocks.size());
    }

    @Test
    public void testUpdateStock() {
        Stock stock = new Stock(0, "Joe", "IBM", 0, 0);
        stockRepository.save(stock);
        stock.setName("John Smith");
        stockRepository.updateStockByID(stock.getSymbol(), stock.getName(), stock.getCurrentPrice(), stock.getPreviousPrice(), stock.getId());
        Stock updatedStock = stockRepository.findById(stock.getId()).orElse(null);
        assertNotNull(updatedStock);
        assertEquals("John Smith", updatedStock.getName());
    }

    @Test
    public void testDeleteStock() {
        Stock stock = new Stock(0, "Joe", "IBM", 0, 0);
        stockRepository.save(stock);
        stockRepository.deleteById(stock.getId());
        Stock deletedStock = stockRepository.findById(stock.getId()).orElse(null);
        assertNull(deletedStock);
    }

    @Test
    public void testStockCount() {
        Stock stock = new Stock(0, "Joe", "IBM", 0, 0);
        stockRepository.save(stock);
        int count = stockRepository.ifStockExistCount(stock.getId());
        assertEquals(1, count);
    }
}
