package com.meahead.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="specialization")
@JsonPropertyOrder(value = {"id", "name", "hospitals"})
public class Specialization {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name="id")
	private Long id;

	@Column(name = "name")
	private String name;

	@JsonIgnoreProperties("specializations")
	@ManyToMany(mappedBy="specializations", fetch=FetchType.LAZY)
	private List<Hospital> hospitals = new ArrayList<>();

}
