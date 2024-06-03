package com.meahead.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meahead.dto.HospitalDto;
import com.meahead.model.Hospital;
import com.meahead.repository.HospitalRepository;
import com.meahead.repository.SpecializationRepository;

import lombok.Data;

@Data
@Service
public class HospitalService {

	@Autowired
	HospitalRepository hr;

	@Autowired
	SpecializationRepository sr;

	public Iterable<HospitalDto> getHospitals() {
		Iterable<Hospital> hospitals = hr.findAll();
		List<HospitalDto> hospitalsDto = new ArrayList<HospitalDto>();

		hospitals.forEach(x -> {
			hospitalsDto.add(new HospitalDto(x.getId(), x.getName()));
		});
		return hospitalsDto;
	}
	
	public Iterable<Hospital> getHospitalsAround(String name){
		Hospital hospital = hr.findHospitalByName(name);
		Iterable<Hospital> hospitals = hr.findHospitalsAround(hospital.getName());
		return hospitals;
	}
	
	public Hospital reserverPlace(Long id) throws Exception{
		Optional<Hospital> hospital = hr.findById(id);
		if(hospital.isPresent()) {	
			var h = hospital.get();
			if(h.getBeds() < 1) {
				throw new Exception("Non disponible");
			}
			h.setBeds(h.getBeds()-1);
			hr.save(h);
		}
		return hospital.get();
	}
	
}
