package com.in28minutes.microservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsServiceApplicationController {
	
	@Autowired
	private Limits limits;

	@GetMapping("/limits")
	public Limits retrieveLimits() {
		return new Limits(100, 200);
	}
	
	@GetMapping("/limitsbean")
	public Limits retrieveLimitsBean() {
		return limits;
	}
}
