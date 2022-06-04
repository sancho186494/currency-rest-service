package org.example.alfa.controller;

import org.example.alfa.model.RateJSON;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="currency-client", url="${currency.api}")
public interface CurrencyClient {

    @GetMapping("/api/latest.json")
    RateJSON getAllLatestRates(@RequestParam String app_id);

    @GetMapping("/api/historical/{date}.json")
    RateJSON getAllYesterdayRates(@PathVariable String date, @RequestParam String app_id);
}
