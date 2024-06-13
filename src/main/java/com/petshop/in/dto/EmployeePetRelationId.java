package com.petshop.in.dto;

import java.io.Serializable; 

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class EmployeePetRelationId implements Serializable{
	

	@Column(name = "pet_id")
	private int pet_id;
	
	@Column(name = "Employee_id")
	private int Employee_id;

}
