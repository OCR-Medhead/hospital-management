package com.meahead.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.meahead.model.Hospital;
import com.meahead.repository.HospitalRepository;


public class HospitalRepositoryTest {

    @Mock
    HospitalRepository hs;

    Hospital hospital;

    ArrayList<Hospital> listOfHospital;

	@BeforeEach
    public void setUp() {
    	listOfHospital = new ArrayList<>();
        hospital = new Hospital();
        hospital.setId((long) 1);
        hospital.setName("Test");
        hospital.setBeds(20);
        listOfHospital.add(hospital);

        MockitoAnnotations.openMocks(this);

        when(hs.findHospitalByName(anyString())).thenReturn(hospital);
    }

    @Test
    public void should_return_hospital_beds() {
    	Hospital getHospital = hs.findHospitalByName(anyString());
         assertThat(getHospital.getBeds()).isEqualTo(20);
    }

    @Test
    public void should_return_hospital_name() {
    	Hospital getHospital = hs.findHospitalByName(anyString());
         assertThat(getHospital.getName()).isEqualTo("Test");
    }
    
}