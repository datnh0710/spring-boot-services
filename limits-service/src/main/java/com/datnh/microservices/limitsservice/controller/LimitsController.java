package com.datnh.microservices.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datnh.microservices.limitsservice.bean.Limits;
import com.datnh.microservices.limitsservice.configuration.Configuration;

@RestController
@RequestMapping(path = "/api/v0")
public class LimitsController {
	
	@Autowired
	private Configuration configuration;
	
	@GetMapping(path = "/limits")
	public Limits retrieveLimits() {
		return new Limits(configuration.getMinimun(), configuration.getMaximun());
//		return new Limits(10, 100);
		
	}
	

}
