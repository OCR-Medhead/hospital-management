package com.meahead.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.meahead.dto.HospitalDto;
import com.meahead.dto.SpecializationDto;
import com.meahead.model.Hospital;
import com.meahead.model.Specialization;
import com.meahead.service.HospitalService;
import com.meahead.service.SpecializationService;

// ATTENTION - Controller attends une view, pas un RestController (Pb de circular view)
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
	

	@GetMapping("/specializations")
	public Iterable<SpecializationDto> getSpecialization() {
		return sr.getSpecializations();
	}
	
	@GetMapping("/hospitals/reserver/{id}")
	@ResponseBody
	public ResponseEntity reserverPlace(@PathVariable("id") final Long id) throws Exception {
		try{
			var h = hs.reserverPlace(id);
			return ResponseEntity.status(HttpStatus.OK).body(h);
		}catch(Exception ex) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
}
