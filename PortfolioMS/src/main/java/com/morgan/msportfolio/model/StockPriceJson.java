package com.morgan.msportfolio.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockPriceJson {
    @JsonProperty("Meta Data")
    private StockPriceJsonMetaData metaData;
    @JsonProperty("Time Series (1min)")
    private Map<String, StockPriceJsonMins> daily;
}