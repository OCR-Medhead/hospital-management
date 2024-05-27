package com.meahead;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.meahead.dto.HospitalDto;
import com.meahead.model.Hospital;
import com.meahead.model.Specialization;
import com.meahead.service.HospitalService;

@SpringBootTest
class HospitalServiceTests {

	@Autowired
	private HospitalService hs;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void checkHospitalListShouldBeGreaterThanOne() {
		Iterable<HospitalDto> hospitals = hs.getHospitals();
		int counter =0;
		for(HospitalDto h: hospitals) {
			counter++;
		}
		
		assertTrue(counter > 1);
	}		
	
	@Test
	public void checkHospitalListWithSpecializationShouldBeGreaterThanOne() {
		Iterable<Hospital> hospitals = hs.getHospitalWithSpecialization();
		int counter =0;
		for(Hospital h: hospitals) {
			counter++;
		}
		
		assertTrue(counter > 1);
	}		
	
	@Test
	public void checkHospitalsByName() {
		Iterable<HospitalDto> hospitals = hs.getHospitalsByName("Paris");
		
		int counter =0;
		for(HospitalDto h: hospitals) {
			counter++;
		}
		
		assertTrue(counter == 1);
		
	}	
	
	
}
