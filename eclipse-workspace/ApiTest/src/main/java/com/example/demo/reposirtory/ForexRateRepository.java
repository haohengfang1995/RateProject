package com.example.demo.reposirtory;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ForexRate;



@Repository
public interface ForexRateRepository extends  MongoRepository<ForexRate, String>{
	List<ForexRate> findByTimestampBetween(LocalDate start, LocalDate end);
}
