package com.datnh.microservices.currencyconversionservice.proxy;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.datnh.microservices.currencyconversionservice.bean.CurrencyConversion;


//@FeignClient(name = "currency-exchange", url = "localhost:8000/api/v0")
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {
	
	
//	@GetMapping(path = "/api/v0/currency-exchange/from/{from_currency}/to/{to_currency}")
	@GetMapping(path = "currency-exchange/from/{from_currency}/to/{to_currency}")
	public CurrencyConversion retrieveExchangeValue(@PathVariable String from_currency, @PathVariable String to_currency);

}
