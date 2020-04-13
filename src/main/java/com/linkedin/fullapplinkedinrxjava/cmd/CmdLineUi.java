package com.linkedin.fullapplinkedinrxjava.cmd;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.linkedin.fullapplinkedinrxjava.observer.ConsolePrintObserver;
import com.linkedin.fullapplinkedinrxjava.service.CoinbaseService;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

@Component
public class CmdLineUi implements CommandLineRunner {

	@Autowired	
	private CoinbaseService coinbaseService;
	
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println();
		System.out.println("LinkedIn Learning Reactive Programming with Java 8");
		System.out.println();
		
		Observable.interval(3000, TimeUnit.MILLISECONDS, Schedulers.io())
			.map(tick -> coinbaseService.getCryptoPrice("BTC-USD"))
			.subscribe(new ConsolePrintObserver());
		
		Observable.interval(3000, TimeUnit.MILLISECONDS, Schedulers.io())
			.map(tick -> coinbaseService.getCryptoPrice("ETH-USD"))
			.subscribe(new ConsolePrintObserver());
	}

}
