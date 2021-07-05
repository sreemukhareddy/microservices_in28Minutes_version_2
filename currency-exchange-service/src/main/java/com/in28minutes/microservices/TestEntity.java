package com.in28minutes.microservices;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class TestEntity {

	@Id
	@GeneratedValue
	private Long id;
	
	private String createdBy;
	private String date;
}
