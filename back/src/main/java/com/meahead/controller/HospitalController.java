package com.meahead.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.meahead.dto.HospitalDto;
import com.meahead.dto.SpecializationDto;
import com.meahead.model.Hospital;
import com.meahead.model.Specialization;
//import com.meahead.model.Specialization;
import com.meahead.service.HospitalService;
//import com.meahead.service.SpecializationService;
import com.meahead.service.SpecializationService;

// ATTENTION - Controller attends une view, RestController non (Pb de circular view)
@CrossOrigin(origins="*")
@RestController
public class HospitalController {

	@Autowired
	HospitalService hs;

	@Autowired
	SpecializationService sr;

	@GetMapping("/hospitals")
	public Iterable<HospitalDto> getHospitals() {
		return hs.getHospitals();
	}
	
	@GetMapping("/hospitals/around/{name}")
	public Iterable<Hospital> getHospitalsAround(@PathVariable("name") final String name){
		return hs.getHospitalsAround(name);
	}
	
//	@GetMapping("/hospitals/{name}")
//	public Iterable<HospitalDto> getHospitalsByName(@PathVariable("name") final String name){
//		return hs.getHospitalsByName(name);
//	}
//	
	@GetMapping("/hospital/{hospitalName}")
	public Hospital findHospitalSpecialization(@PathVariable("hospitalName") final String hospitalName){
		return hs.findHospitalWithSpecializations(hospitalName);
	}
	
	@GetMapping("/specializations")
	public Iterable<SpecializationDto> getSpecialization() {
		return sr.getSpecializations();
	}
	
//	@GetMapping("/hospitals/specialization")
//	public Iterable<Hospital> getHospitalWithSpe() {
//		return hs.getHospitalWithSpecialization();
//	}

//
//	@GetMapping("/specializations/hospitals")
//	public Iterable<Specialization> getSpecializationsWithHospitals(){
//		return sr.getSpecializationsWithHospital();
//	}
		
}
