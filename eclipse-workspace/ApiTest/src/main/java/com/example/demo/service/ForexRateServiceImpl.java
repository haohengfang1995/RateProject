package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ForexRate;
import com.example.demo.reposirtory.ForexRateRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ForexRateServiceImpl implements ForexRateService{
	
	@Autowired
	private final ForexRateRepository forexRateRepository;

	@Override
	public List<ForexRate> findForexRateByDate(LocalDate startDate, LocalDate endDate) {
		return forexRateRepository.findByTimestampBetween(startDate, endDate);
	}

}
