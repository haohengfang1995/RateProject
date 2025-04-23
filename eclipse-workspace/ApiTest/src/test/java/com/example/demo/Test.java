package com.example.demo;



import java.util.Map;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
@SpringBootTest
public class Test {

  
	@org.junit.jupiter.api.Test
    void testRealApiIsAvailable() {
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://openapi.taifex.com.tw/v1/DailyForeignExchangeRates";

		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		try {
		Object [] data = mapper.readValue(response.getBody(), Object[].class);
		Object lastObj = data[data.length - 1];
        Map<String, Object> lastItem = (Map<String, Object>) lastObj;
        System.out.println(lastItem);
        System.out.println(lastItem.get("Date"));
        System.out.println(lastItem.get("USD/NTD"));
        
//		for (Object obj : data) {
//		    Map<String, Object> item = (Map<String, Object>) obj;
//		    System.out.println(item);
//		}
		}catch (Exception e) {
			e.printStackTrace();
		}
//        System.out.println(response.getBody());
	}
}
