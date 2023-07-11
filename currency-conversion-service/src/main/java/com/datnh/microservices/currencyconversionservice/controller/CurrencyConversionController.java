package com.datnh.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.datnh.microservices.currencyconversionservice.bean.CurrencyConversion;
import com.datnh.microservices.currencyconversionservice.proxy.CurrencyExchangeProxy;


@Configuration(proxyBeanMethods = false)
class RestTemplateConfiguration {
    
    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}

@RestController
//@RequestMapping(path = "/api/v0")
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyExchangeProxy currencyExchangeProxy;
	
	@Autowired
	private RestTemplate restTemplate;

	// /currency-conversion/from/USD/to/VND/quantity/10
	@GetMapping(path = "/currency-conversion/from/{from_currency}/to/{to_currency}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(@PathVariable String from_currency,
			@PathVariable String to_currency, @PathVariable BigDecimal quantity) {

		HashMap<String, String> urlVariables = new HashMap<String, String>();

		urlVariables.put("from_currency", from_currency);
		urlVariables.put("to_currency", to_currency);

		// calling the currency exchange service via restAPI
		String url = "http://localhost:8000/currency-exchange/from/{from_currency}/to/{to_currency}";
		//String url = "http://localhost:8000/api/v0/currency-exchange/from/{from_currency}/to/{to_currency}";
		ResponseEntity<CurrencyConversion> responseEntity = restTemplate.getForEntity(
				url,
				CurrencyConversion.class, urlVariables);

		CurrencyConversion currencyConversion = responseEntity.getBody();

		return new CurrencyConversion(currencyConversion.getId(), from_currency, to_currency, quantity,
				currencyConversion.getConversionMultiple(),
				quantity.multiply(currencyConversion.getConversionMultiple()), currencyConversion.getEnviroment() + "rest template");
	}

	// /currency-conversion/from/USD/to/VND/quantity/10
	@GetMapping(path = "/currency-conversion-feign/from/{from_currency}/to/{to_currency}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from_currency,
			@PathVariable String to_currency, @PathVariable BigDecimal quantity) {

		

		// calling the currency exchange service via feign
		CurrencyConversion currencyConversion = currencyExchangeProxy.retrieveExchangeValue(from_currency, to_currency);

		return new CurrencyConversion(currencyConversion.getId(), from_currency, to_currency, quantity,
				currencyConversion.getConversionMultiple(),
				quantity.multiply(currencyConversion.getConversionMultiple()), currencyConversion.getEnviroment() + " feign");
	}

}
