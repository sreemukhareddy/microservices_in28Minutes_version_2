package com.in28minutes.microservices;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyConversionService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CurrencyExchangeProxy currencyExchangeProxy;
	
	public ExchangeValue getExchangeValue(String from, String to, int quantity) {
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<ExchangeValue> responseEntity = restTemplate.getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", ExchangeValue.class, uriVariables);
		if(responseEntity != null && responseEntity.getStatusCodeValue() == 200) {
			ExchangeValue exchangeValue = responseEntity.getBody();
			BigDecimal multiply = exchangeValue.getConversionMultiple().multiply(new BigDecimal(quantity));
			exchangeValue.setQuantity(new BigDecimal(quantity));
			exchangeValue.setTotalCalculatedAmount(multiply);
			return exchangeValue;
		}
		return null;
	}
	
	public ExchangeValue getExchangeValueFeign(String from, String to, int quantity) {
		ExchangeValue exchangeValue = currencyExchangeProxy.retrieveExchangeValue(from, to);
		if(exchangeValue != null ) {
			BigDecimal multiply = exchangeValue.getConversionMultiple().multiply(new BigDecimal(quantity));
			exchangeValue.setQuantity(new BigDecimal(quantity));
			exchangeValue.setTotalCalculatedAmount(multiply);
			return exchangeValue;
		}
		return null;
	}
}
