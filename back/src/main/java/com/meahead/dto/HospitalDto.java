package com.meahead.dto;

import lombok.Data;

@Data
public class HospitalDto {
	private Long id;
	private String name;
	
	public HospitalDto(Long id, String name) {
		this.id = id;
		this.name = name;
	}
}
