package com.datnh.microservices.currencyexchangeservice.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;


@RestController
public class CircuitBreakController {
	
	private Logger logger = LoggerFactory.getLogger(CircuitBreakController.class);

	@GetMapping("sample-api")
//	@Retry(name = "sample-api", fallbackMethod = "hardCodeResponse")
//	@CircuitBreaker(name = "default", fallbackMethod = "hardCodeResponse")
//	10s --> 10000 call to the sample api
//	@RateLimiter(name = "default")
	@Bulkhead(name = "sample-api")
	public String sampleApi() {
		logger.info("Sample API call received!");
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url",
				String.class);
//		return forEntity.getBody();
		return "sample-api";
	}
	
	public String hardCodeResponse(Exception ex) {
		return "Hard Code Response!!!";
	}
}
