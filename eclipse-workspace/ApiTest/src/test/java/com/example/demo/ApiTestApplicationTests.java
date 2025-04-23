package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.ForexRate;
import com.example.demo.reposirtory.ForexRateRepository;
import com.example.demo.scheduler.ForexScheduler;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class ApiTestApplicationTests {
		 @Mock
		    private ForexRateRepository forexRateRepository;

		    @Mock
		    private RestTemplate restTemplate;

		    @Mock
		    private ObjectMapper mapper;

		    @InjectMocks
		    private ForexScheduler forexScheduler;

		    @Test
		    void testFetchUsd() throws Exception {
		        // 準備假資料（模擬 Taifex 回傳的 JSON 字串）
		        Map<String, String> mockData = new HashMap<>();
		        mockData.put("USD/NTD", "32.123");
		        mockData.put("Date", "20250420");

		        Object[] mockArray = new Object[] { mockData };

		        // 模擬 RestTemplate 回傳的 JSON 字串
		        when(restTemplate.getForEntity(anyString(), eq(String.class)))
		                .thenReturn(ResponseEntity.ok("[{\"USD/NTD\":\"32.123\",\"Date\":\"20250420\"}]"));

		        // 模擬 ObjectMapper 解析 JSON 字串為 Java 陣列
		        when(mapper.readValue(anyString(), eq(Object[].class))).thenReturn(mockArray);

		        // 執行方法
		        forexScheduler.fetchUsd();

		        // 驗證是否有呼叫儲存到 repository
		        verify(forexRateRepository, times(1)).save(any(ForexRate.class));
		    }
}
