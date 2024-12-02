package com.morgan.msportfolio.service;

import com.morgan.msportfolio.exception.NoSuchDataExistsException;
import com.morgan.msportfolio.model.StockPriceJson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PortfolioService {

    @Value("${alphavantage.api.key}")
    private String apiKey;

    private final WebClient webClient;

    public PortfolioService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://www.alphavantage.co").build();
    }

    public Mono<StockPriceJson> getAlphaVantageData(String symbol) {
        return this.webClient.get().uri("/query", uriBuilder -> uriBuilder.queryParam("function", "TIME_SERIES_INTRADAY")
                        .queryParam("interval", "1min")
                        .queryParam("symbol", symbol)
                        .queryParam("apikey", apiKey)
                        .build())
                .retrieve().bodyToMono(StockPriceJson.class)
                .onErrorMap(NoSuchDataExistsException.class,
                        e -> new NoSuchDataExistsException("Failed to retrieve stock details"));
    }
}
