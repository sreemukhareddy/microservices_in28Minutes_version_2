package com.in28minutes.microservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyConversionService service;

	@GetMapping("/from/{from}/to/{to}/{quantity}")
	public ExchangeValue getExchangeValue(@PathVariable String from, @PathVariable String to, @PathVariable Integer quantity) {
		log.info("From -> {}, TO -> {}, Quantity -> {}", from, to, quantity);
		return service.getExchangeValue(from, to, quantity);
	}
	
	@GetMapping("feign/from/{from}/to/{to}/{quantity}")
	public ExchangeValue getExchangeValueFeign(@PathVariable String from, @PathVariable String to, @PathVariable Integer quantity) {
		log.info("From -> {}, TO -> {}, Quantity -> {}", from, to, quantity);
		return service.getExchangeValueFeign(from, to, quantity);
	}
}
