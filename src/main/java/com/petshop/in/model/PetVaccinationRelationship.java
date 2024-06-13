package com.petshop.in.model;

import com.petshop.in.dto.PetVaccinationRelationId;

import jakarta.persistence.*;

@Entity
@Table(name = "pet_vaccination_relationship")
public class PetVaccinationRelationship {
    
	@EmbeddedId
	private PetVaccinationRelationId id;
	
	@ManyToOne
    @JoinColumn(name = "pet_id", referencedColumnName = "pet_id",insertable = false, updatable = false)
    private Pets pet;

    @ManyToOne
    @JoinColumn(name = "vaccination_id", referencedColumnName = "vaccination_id",insertable = false, updatable = false)
    private Vaccinations vaccination;

	public PetVaccinationRelationId getId() {
		return id;
	}

	public Pets getPet() {
		return pet;
	}

	public Vaccinations getVaccination() {
		return vaccination;
	}

	
    
    
}

