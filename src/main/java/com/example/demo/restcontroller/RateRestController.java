package com.example.demo.restcontroller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ForexRate;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.ForexRateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/test")
@CrossOrigin
@RequiredArgsConstructor
public class RateRestController {
	
	@Autowired
	private final ForexRateService forexRateService; 
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<ApiResponse<List<ForexRate>>> getRate(@RequestBody Map<String, String> request){
		String startDate = request.get("startDate");
		String endDate = request.get("endDate");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localStartDate = LocalDate.parse(startDate, formatter);
		LocalDate localEndDate = LocalDate.parse(endDate, formatter);
		LocalDate oneYearAgo = LocalDate.now().minusYears(1);
		LocalDate yesterDay = LocalDate.now().minusDays(1);
//		if(localStartDate.isAfter(oneYearAgo) && localEndDate.isBefore(yesterDay)) {
//			List<ForexRate> foresRates = forexRateService.findForexRateByDate(localStartDate, localEndDate);
//			return ResponseEntity.ok(ApiResponse.success("搜尋成功", foresRates));
//		}else {
//			return ResponseEntity.badRequest().body(ApiResponse.error(200, "時間輸入錯誤"));
//		}
		
		if(localStartDate.isBefore(oneYearAgo)) {
			return ResponseEntity.badRequest().body(ApiResponse.error(200, "時間不能搜尋一年前"));
		}
		if(localEndDate.isAfter(yesterDay)) {
			return ResponseEntity.badRequest().body(ApiResponse.error(200, "只能搜尋到昨天"));
		}
		List<ForexRate> foresRates = forexRateService.findForexRateByDate(localStartDate, localEndDate);
		return ResponseEntity.ok(ApiResponse.success("搜尋成功", foresRates));
		
		
		
	}
	

}
