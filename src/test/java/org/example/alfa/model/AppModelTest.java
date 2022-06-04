package org.example.alfa.model;

import org.example.alfa.ResponseForTest;
import org.example.alfa.controller.CurrencyClient;
import org.example.alfa.controller.GifApiClient;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@TestPropertySource("classpath:application.properties")
@ContextConfiguration
public class AppModelTest extends AbstractTestNGSpringContextTests {

    @Value("${currency.api.key}")
    private String currencyApiKey;
    @Value("${gif.api.key}")
    private String gifApiKey;
    @Value("${target.currency}")
    private String targetCurrency;

    @InjectMocks
    private AppModel appModel;
    @Mock
    private CurrencyClient currencyClient;
    @Mock
    private GifApiClient gifApiClient;

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(appModel, "currencyApiKey", currencyApiKey);
        ReflectionTestUtils.setField(appModel, "gifApiKey", gifApiKey);
        ReflectionTestUtils.setField(appModel, "targetCurrency", targetCurrency);
    }

    @Test(description = "Проверка сравнения курсов")
    public void getGifTagTest() {
        Assert.assertEquals(appModel.getGifTag(65.0, 70.0), "broke");
        Assert.assertEquals(appModel.getGifTag(70.0, 65.0), "rich");
    }

    @Test(description = "Проверка парсинга тела ответа от сервиса валют (предыдущий день)")
    public void requestYesterdayRateTest() {
        String yesterdayDate = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        ResponseForTest responseForTest = new ResponseForTest();
        Mockito.when(currencyClient.getAllYesterdayRates(yesterdayDate, currencyApiKey))
                .thenReturn(responseForTest.getRateResponse("yesterdayRate.json"));
        Assert.assertEquals(appModel.requestYesterdayRate(), 65.350005);
    }

    @Test(description = "Проверка парсинга тела ответа от сервиса валют (текущий день)")
    public void requestTodayRateTest() {
        ResponseForTest responseForTest = new ResponseForTest();
        Mockito.when(currencyClient.getAllLatestRates(currencyApiKey))
                .thenReturn(responseForTest.getRateResponse("todayRate.json"));
        Assert.assertEquals(appModel.requestTodayRate(), 63.360006);
    }

    @Test(description = "Проверка парсинга тела ответа от сервиса гифок")
    public void requestGifIdTest() {
        ResponseForTest responseForTest = new ResponseForTest();
        Mockito.when(gifApiClient.getRandomGif(gifApiKey, "rich"))
                .thenReturn(responseForTest.getGifResponse("gifId.json"));
        Assert.assertEquals(appModel.requestGifId("rich"), "aoLEKgswfXQpm5HYvo");
    }
}