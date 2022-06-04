package org.example.alfa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class RateJSON {

    @JsonProperty("rates")
    private Map<String, Double> rates;

    public Double getRate(String currency) {
        return rates.get(currency);
    }

}


