package com.in28minutes.microservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {

	@GetMapping("/sample-api")
	//@Retry(name = "default") // if name = default, then this method will be invoked 3 times if it fails
	//@Retry(name = "sample-api", fallbackMethod = "fallBackForSampleApi") // if we want to configure our own retries, we need to configure resilience4j.retry.instances.sample-api.maxRetryAttempts=5 in application.properties
	@CircuitBreaker(name = "default", fallbackMethod = "fallBackForSampleApi")
	public String sample() {
		return new RestTemplate().getForEntity("http://localhost:80080/dummy-api", String.class).getBody();
	}
	
	public String fallBackForSampleApi(Exception ex) {
		return "issue is there with dummy-api";
	}
}