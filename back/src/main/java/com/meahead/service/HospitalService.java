package com.meahead.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meahead.dto.HospitalDto;
import com.meahead.model.Hospital;
import com.meahead.repository.HospitalRepository;
//import com.meahead.repository.SpecializationRepository;
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

	public Iterable<Hospital> getHospitalWithSpecialization() {
		return hr.findAll();
	}
	
	public Iterable<HospitalDto> getHospitalsByName(String name) {
		Iterable<Hospital> hospitals = hr.findHospitalsByName(name);
		List<HospitalDto> hospitalsDto = new ArrayList<HospitalDto>();

		hospitals.forEach(x -> {
			hospitalsDto.add(new HospitalDto(x.getId(), x.getName()));
		});
		return hospitalsDto;
	}
	
	public Hospital findHospitalWithSpecializations(String hospitalName){
		Hospital hospital = hr.findHospitalWithSpecializations(hospitalName);
		return hospital;
	}
	
	public Iterable<Hospital> getHospitalsAround(String name){
		Hospital hospital = hr.findHospitalByName(name);
		Iterable<Hospital> hospitals = hr.findHospitalsAround(hospital.getName());
		return hospitals;
	}
	
}
