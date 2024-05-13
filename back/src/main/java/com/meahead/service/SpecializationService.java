package com.meahead.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meahead.dto.SpecializationDto;
import com.meahead.model.Specialization;
import com.meahead.repository.SpecializationRepository;

@Service
public class SpecializationService {

	@Autowired
	SpecializationRepository sr;
	
	public Iterable<SpecializationDto> getSpecializations() {
		Iterable<Specialization> specialization = sr.findAll();
		List<SpecializationDto> specializationsDto = new ArrayList<SpecializationDto>();
		
		specialization.forEach(x-> {
			specializationsDto.add(new SpecializationDto(x.getId(), x.getName()));
		});
		
		return specializationsDto;
	}

	public Iterable<Specialization> getSpecializationsWithHospital() {
		return sr.findAll();
	}

}
