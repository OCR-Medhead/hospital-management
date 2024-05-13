package com.meahead.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.meahead.model.Hospital;

@Repository
public interface HospitalRepository extends CrudRepository<Hospital, Long> {

	@Query(value = "SELECT * FROM hospital h WHERE h.name like %:name%", nativeQuery = true)
	public List<Hospital> findHospitalsByName(@Param("name") String name);

	@Query(value = "SELECT * FROM hospital h WHERE h.name like %:name% ORDER by name", nativeQuery = true)
	public Hospital findHospitalByName(@Param("name") String name);
	
	@Query(value = "SELECT h.id as id, h.name as name, h.latitude as latitude, h.longitude as longitude, s.id as specializationid, s.name as specializationName FROM hospital h INNER JOIN hospital_specialization hs ON hs.hospital_id = h.id INNER JOIN specialization s ON s.id = hs.specialization_id WHERE h.name = %:hospitalName% LIMIT 1", nativeQuery = true)
	public Hospital findHospitalWithSpecializations(@Param("hospitalName") String hospitalName);

	@Query(value = """
			SELECT v2.id as id, v2.name as name, v2.latitude as latitude, v2.longitude as longitude,
			6371 * 2 * ASIN(SQRT(
			POWER(SIN((v1.latitude - ABS(v2.latitude)) * PI() / 180 / 2), 2) +
			COS(v1.latitude * PI() / 180) * COS(ABS(v2.latitude) * PI() / 180) *
			POWER(SIN((v1.longitude - v2.longitude) * PI() / 180 / 2), 2)
			)) AS distance
			FROM hospital v1
			JOIN hospital v2 ON v1.name = %:name%
			--WHERE v2.name != %:name%
			ORDER BY distance
			LIMIT 3;""", nativeQuery = true)
	public List<Hospital> findHospitalsAround(@Param("name") String name);

	
	
}