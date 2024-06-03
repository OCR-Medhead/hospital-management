package com.meahead.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="hospital")
@JsonPropertyOrder(value = {"id", "name", "latitude", "longitude", "specializations", "beds"})
public class Hospital {

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="id")
	private Long id;
	@Column(name="name")
	private String Name;
	@Column(name="latitude")
	private Float Latitude;
	@Column(name="longitude")
	private Float Longitude;
	@Column(name="beds")
	private Integer Beds;
	
	@JoinTable(name="hospital_specialization", 
				joinColumns = @JoinColumn(name="hospital_id"),
				inverseJoinColumns=@JoinColumn(name="specialization_id"))
	@JsonIgnoreProperties("hospitals")
	@ManyToMany(fetch=FetchType.LAZY)
	private List<Specialization> specializations;
}
