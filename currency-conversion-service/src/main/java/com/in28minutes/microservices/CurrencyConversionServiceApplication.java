package com.in28minutes.microservices;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import lombok.Data;


@Data
@EnableFeignClients
@SpringBootApplication
public class CurrencyConversionServiceApplication {

	@Value("${restTemplateTimeOut}")
	private String restTemplateTimeOut;
	
	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionServiceApplication.class, args);
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
    	((SimpleClientHttpRequestFactory)restTemplate.getRequestFactory()).setConnectTimeout(Integer.valueOf(restTemplateTimeOut));
    	return restTemplate;
	}

}
