package com.linkedin.fullapplinkedinrxjava.observer;

import java.time.LocalDateTime;

import com.linkedin.fullapplinkedinrxjava.model.CoinBaseResponse;

import io.reactivex.observers.DefaultObserver;
import reactor.core.publisher.Mono;

public class ConsolePrintObserver extends DefaultObserver<Object> {

	@Override
	public void onNext(Object t) {
		Mono<CoinBaseResponse> responseMono = (Mono<CoinBaseResponse>) t;
		responseMono.subscribe(coinBaseResponse -> {
			System.out.println(String.format("%s %s Buy Price: $%s %s", LocalDateTime.now(),
					coinBaseResponse.getData().getBase(),
					coinBaseResponse.getData().getAmount(),
					coinBaseResponse.getData().getCurrency()));
		});
	}

	@Override
	public void onError(Throwable e) {
		e.printStackTrace();
	}

	@Override
	public void onComplete() {
		System.out.println("Completed");
	}

}
