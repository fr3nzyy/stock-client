package com.fr3nzy.demo.stockclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

class WebClientStockClientIntegrationTest {
    private WebClient webClient = WebClient.builder().build();

    @Test
    void shouldRetrieveStockPriceFromTheService() {
        WebClientStockClient webClientStockClient = new WebClientStockClient(webClient);
        Flux<StockPrice> prices = webClientStockClient.pricesFor("SYMBOL");

        Assertions.assertNotNull(prices);
        Assertions.assertTrue(prices.take(5).count().block() > 0);
    }
}