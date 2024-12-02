package com.morgan.msportfolio.controller;

import com.morgan.msportfolio.model.StockPriceJson;
import com.morgan.msportfolio.service.PortfolioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class PortfolioController {

    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping("/real-time-stock-prices/{symbol}")
    public Mono<StockPriceJson> getPositionAndMarketValue(@PathVariable String symbol) {
        log.info("Start -> : Fetching data from Alpha Vantage : <- Start");
        return portfolioService.getAlphaVantageData(symbol);
    }
}
