package com.example.morgan.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.example.morgan.modal.Stock;
import com.example.morgan.repository.StockRepository;
import com.example.morgan.service.impl.StockServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class StockServiceTest {

    @InjectMocks
    private StockServiceImpl stockService;

    @Mock
    private StockRepository stockRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindStockById() {
        Stock stock = new Stock(1, "John Doe", "Engineering", 60000, 0);
        when(stockRepository.findById(1)).thenReturn(Optional.of(stock));
        Stock found = stockRepository.findById(1).orElse(null);
        assertEquals("John Doe", found.getName());
    }
}
