package com.in28minutes.microservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private ExchangeValueRepository repository;
	
	@Autowired
	private Environment environment;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue getCurrencyConversion(@PathVariable String from, @PathVariable String to) {
		log.info("from -> {}, to -> {}", from, to);
		ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);
		if(exchangeValue == null) {
			throw new RuntimeException("Issue");
		}
		Integer port = Integer.valueOf(environment.getProperty("local.server.port"));
		exchangeValue.setPort(port);
		return exchangeValue;
	}
}
