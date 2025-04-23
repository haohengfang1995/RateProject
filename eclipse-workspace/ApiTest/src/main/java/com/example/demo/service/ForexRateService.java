package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.model.ForexRate;


public interface ForexRateService {

	public List<ForexRate> findForexRateByDate(LocalDate startDate, LocalDate endDate);
}
