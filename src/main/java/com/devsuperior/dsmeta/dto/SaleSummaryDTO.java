package com.devsuperior.dsmeta.dto;


public class SaleSummaryDTO {
	
	private String name;
	private Double total;
	
	public SaleSummaryDTO() {
	}

	public SaleSummaryDTO(String name, Double total) {
		this.name = name;
		this.total = total;
	}

	public String getName() {
		return name;
	}

	public Double getTotal() {
		return total;
	}
}
