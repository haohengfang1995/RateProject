package com.example.demo.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;




@Data
@Document("forex_rate")
public class ForexRate {
	
	@Id
	private String id;
	private double rate;
	private LocalDate timestamp;

}
