package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
	
	public Page<SaleReportDTO> report(String minDateRequest, String maxDateRequest, String nameRequest, Pageable pageable){
		
		LocalDate maxDate = maxDateValidation(maxDateRequest);		
		LocalDate minDate = minDateValidation(minDateRequest, maxDate);
		
		Page<SaleReportDTO> result = repository.report(minDate, maxDate, nameRequest, pageable);
		return result;
	}
	
	public List<SaleSummaryDTO> summary(String minDateRequest, String maxDateRequest){
		
		LocalDate maxDate = maxDateValidation(maxDateRequest);		
		LocalDate minDate = minDateValidation(minDateRequest, maxDate);
		
		List<SaleSummaryDTO> result = repository.summary(minDate, maxDate);
		return result;
	}

	private LocalDate minDateValidation(String minDateRequest, LocalDate maxDate) {
		LocalDate minDate;
		if(minDateRequest.isBlank()) {
			
			minDate = maxDate.minusYears(1L);
		}
		else {
			minDate = LocalDate.parse(minDateRequest);
		}
		return minDate;
	}

	private LocalDate maxDateValidation(String maxDateRequest) {
		LocalDate maxDate; 

		if(maxDateRequest.isBlank()) {
			maxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}
		else {
			maxDate = LocalDate.parse(maxDateRequest);
		}
		return maxDate;
	}
}
