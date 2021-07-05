package com.in28minutes.microservices;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeValue implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String from;
	
	private String to;
	private BigDecimal conversionMultiple;
	
	private BigDecimal quantity;
	
	private BigDecimal totalCalculatedAmount;
	
	private Integer port;
}