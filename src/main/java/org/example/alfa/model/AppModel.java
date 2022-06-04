package org.example.alfa.model;

import org.example.alfa.controller.CurrencyClient;
import org.example.alfa.controller.GifApiClient;
import org.example.alfa.controller.GifShowClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class AppModel {

    @Value("${currency.api.key}")
    private String currencyApiKey;
    @Value("${gif.api.key}")
    private String gifApiKey;
    @Value("${target.currency}")
    private String targetCurrency;

    @Autowired
    private CurrencyClient currencyClient;
    @Autowired
    private GifApiClient gifApiClient;
    @Autowired
    private GifShowClient gifShowClient;


    public Resource getResultGif() {
        Double todayRate = requestTodayRate();
        Double yesterdayRate = requestYesterdayRate();

        return requestGifFile(getGifTag(todayRate, yesterdayRate));
    }

    public String getGifTag(Double todayRate, Double yesterdayRate) {
        String tag = "broke";
        if (todayRate >= yesterdayRate)
            tag = "rich";
        return tag;
    }

    public Double requestYesterdayRate() {
        String yesterdayDate = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        RateJSON responseBody = currencyClient.getAllYesterdayRates(yesterdayDate, currencyApiKey);
        Double rate = responseBody.getRate(targetCurrency);
        return Optional.ofNullable(responseBody.getRate(targetCurrency)).orElseThrow();
    }

    public Double requestTodayRate() {
        RateJSON responseBody = currencyClient.getAllLatestRates(currencyApiKey);
        return Optional.ofNullable(responseBody.getRate(targetCurrency)).orElseThrow();
    }

    public Resource requestGifFile(String tag) {
        return gifShowClient.showGif(requestGifId(tag));
    }

    public String requestGifId(String tag) {
        GifJSON responseBody = gifApiClient.getRandomGif(gifApiKey, tag);
        return Optional.ofNullable(responseBody.getGifId()).orElseThrow();
    }
}
