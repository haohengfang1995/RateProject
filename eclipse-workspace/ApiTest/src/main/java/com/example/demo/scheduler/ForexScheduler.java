package com.example.demo.scheduler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.ForexRate;
import com.example.demo.reposirtory.ForexRateRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ForexScheduler {
	private static final String USD_NTD = "USD/NTD";
	private static final String API_URL = "https://openapi.taifex.com.tw/v1/DailyForeignExchangeRates";

	@Autowired
	private final ForexRateRepository forexRateRepository;

	@Autowired
	private final RestTemplate restTemplate;

	@Autowired
	private final ObjectMapper mapper;

	@Scheduled(cron = "0 0 18 * * ?")
	public void fetchUsd() {
		ResponseEntity<String> response = restTemplate.getForEntity(API_URL, String.class);//先轉String再轉Object
		try {
			Object[] data = mapper.readValue(response.getBody(), Object[].class);
			for (Object obj : data) {
				Map<String, String> item = (Map<String, String>) obj;
				double rate = Double.parseDouble(item.get(USD_NTD));
				String date = item.get("Date");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
				LocalDate localDate = LocalDate.parse(date, formatter);
				ForexRate forexRate = new ForexRate();
				forexRate.setRate(rate);
				forexRate.setTimestamp(localDate);
				forexRateRepository.save(forexRate);
				log.info("Saved USD rate: {}", rate);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
