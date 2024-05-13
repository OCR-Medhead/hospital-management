package com.meahead.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.meahead.model.Specialization;

@Repository
public interface SpecializationRepository extends CrudRepository<Specialization, Long>{

}