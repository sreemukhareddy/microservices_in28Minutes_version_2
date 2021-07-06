package com.in28minutes.microservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {
	
	@GetMapping("/currencyExchangeServiceFallBack")
	public String currencyExchange() {
		return "Department service is taking longer than expected. Department service might be down. Please check..!";
	}
}