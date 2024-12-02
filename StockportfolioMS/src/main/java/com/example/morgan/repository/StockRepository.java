package com.example.morgan.repository;

import com.example.morgan.modal.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    @Modifying
    @Transactional
    @Query(value = "Update Stock set symbol = :symbol, name = :name, currentPrice = :currentPrice, previousPrice = :previousPrice where id = :id")
    void updateStockByID(String symbol, String name, double currentPrice, double previousPrice, int id);

    @Query(value = "Select count(*) from Stock where id = :id")
    int ifStockExistCount(int id);

    @Query(value = "Select sum(currentPrice) from Stock where symbol = :symbol")
    double findTotalPortfolioValueBySymbol(String symbol);
}