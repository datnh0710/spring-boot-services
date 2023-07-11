package com.datnh.microservices.currencyexchangeservice.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datnh.microservices.currencyexchangeservice.bean.CurrencyExchange;
import com.datnh.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;

@RestController
//@RequestMapping(path = "/api/v0")
public class CurrencyExchangeController {
	
	private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
	
	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepository;
	
	@Autowired
	private Environment environment;

	//path = "/currency-exchange/from/USD/to/VND"
	@GetMapping(path = "/currency-exchange/from/{from_currency}/to/{to_currency}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from_currency, @PathVariable String to_currency) {
		
		logger.info("retrieveExchangeValue is called with {} to {}", from_currency, to_currency);
		
//		CurrencyExchange currencyExchange = new CurrencyExchange(10001L, "USD", "VND", BigDecimal.valueOf(23.5), "");
		CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from_currency, to_currency);
		if(currencyExchange == null) {
			throw new RuntimeException("Unable to Find data for " + from_currency + " to " + to_currency );
		}
		String port = environment.getProperty("local.server.port");
		
		currencyExchange.setEnviroment(port);
		
		
		return currencyExchange;
	}
	
	
	
	
	
}
