package com.meahead.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.meahead.model.CustomUser;

@Repository
public interface UserRepository extends CrudRepository<CustomUser, Long> {
	@Query(value="SELECT * FROM customUser u WHERE u.email like %:email%", nativeQuery=true)
	public CustomUser findUserByEmail(@Param("email") String email);
	
	@Query(value="SELECT * FROM customUser u WHERE u.id = :id", nativeQuery=true)
	public Optional<CustomUser> findById(@Param("id") Long id);
}
