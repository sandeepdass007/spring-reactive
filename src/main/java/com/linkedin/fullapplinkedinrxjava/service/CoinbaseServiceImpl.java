package com.linkedin.fullapplinkedinrxjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.linkedin.fullapplinkedinrxjava.model.CoinBaseResponse;

import reactor.core.publisher.Mono;

@Component
public class CoinbaseServiceImpl implements CoinbaseService {

	@Autowired
	private WebClient webClient;

	@Override
	public Mono<CoinBaseResponse> getCryptoPrice(String priceName) {
		return webClient
			.get()
			.uri("https://api.coinbase.com/v2/prices/{cryptoName}/buy", priceName)
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.flatMap(clientResponse -> clientResponse.bodyToMono(CoinBaseResponse.class));
	}

}
