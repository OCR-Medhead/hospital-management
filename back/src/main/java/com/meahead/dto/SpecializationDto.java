package com.meahead.dto;

import lombok.Data;

@Data
public class SpecializationDto {
	private Long id;
	private String name;
	
	public SpecializationDto(Long id, String name) {
		this.id = id;
		this.name = name;
	}
}
