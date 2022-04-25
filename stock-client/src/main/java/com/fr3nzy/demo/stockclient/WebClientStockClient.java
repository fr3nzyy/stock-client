package com.fr3nzy.demo.stockclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.IOException;

@Slf4j
public class WebClientStockClient {
    private WebClient webClient;

    public WebClientStockClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<StockPrice> pricesFor(String symbol) {
        return webClient.get().uri("http://localhost:8080/stock/{symbol}", symbol)
                .retrieve()
                .bodyToFlux(StockPrice.class)
                .retry(5)
                .doOnError(IOException.class, e -> log.error(e.getMessage()));
    }
}
