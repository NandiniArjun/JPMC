package com.example.morgan.controller;

import com.example.morgan.modal.Stock;
import com.example.morgan.service.StockService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StockController.class)
public class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StockService stockService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
         MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetStockById() throws Exception {
        Stock stock = new Stock(1, "John Doe", "Engineering", 0, 0);

        when(stockService.isStockExist(1)).thenReturn(true);
        when(stockService.getStock(1)).thenReturn(stock);

        mockMvc.perform(MockMvcRequestBuilders.get("/stocks/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.symbol").value("Engineering"));
    }

    @Test
    public void testGetAllStocks() throws Exception {
        List<Stock> stocks = new ArrayList<>();
        stocks.add(new Stock(1, "John Doe", "Engineering", 5000, 0));
        stocks.add(new Stock(2, "Jane Doe", "HR", 4500, 0));
        when(stockService.getStocks()).thenReturn(stocks);

        mockMvc.perform(MockMvcRequestBuilders.get("/stocks"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].symbol").value("Engineering"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].previousPrice").value(5000))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Jane Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].symbol").value("HR"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].previousPrice").value(4500));
    }

    @Test
    public void testCreateStock() throws Exception {
        Stock stock = new Stock(1, "test", "test", 0, 0);

        mockMvc.perform(post("/stocks")
                    .content(objectMapper.writeValueAsString(stock))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists());
    }

    /*@Test
    public void testUpdateStock() throws Exception {
        Stock stockToUpdate = new Stock(1, "My Test", "test", 0, 0);

        when(stockService.isStockExist(1)).thenReturn(true);
        doThrow(new RuntimeException("foo")).when(stockService).updateStock(1, stockToUpdate);

        mockMvc.perform(MockMvcRequestBuilders.put("/stocks/1"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("My Test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].symbol").value("test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].previousPrice").value(0));
    }*/
}
