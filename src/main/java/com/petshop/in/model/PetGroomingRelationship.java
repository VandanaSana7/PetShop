package com.petshop.in.model;

import com.petshop.in.dto.PetGroomingRelationshipId;

import jakarta.persistence.*;

@Entity
@Table(name = "pet_grooming_relationship")
public class PetGroomingRelationship {
    @EmbeddedId
    private PetGroomingRelationshipId id;
    
    @ManyToOne
    @JoinColumn(name = "pet_id", referencedColumnName = "pet_id",insertable = false, updatable = false)
    private Pets pet;

    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "service_id",insertable = false, updatable = false)
    private GroomingServices groomingservices;

    
    
	public PetGroomingRelationshipId getId() {
		return id;
	}

	public Pets getPet() {
		return pet;
	}

	public GroomingServices getGroomingservices() {
		return groomingservices;
	}

	@Override
	public String toString() {
		return "\nPetGroomingRelationship [id=" + id + ", pet=" + pet + ", groomingservices=" + groomingservices + "]";
	}

    
}
