package com.petshop.in.model;

import com.petshop.in.dto.PetFoodRelationshipId;

import jakarta.persistence.*;

@Entity
@Table(name = "pet_food_relationship")
public class PetFoodRelationship {

    @EmbeddedId
    private PetFoodRelationshipId id;
    
    
    @ManyToOne
    @JoinColumn(name = "pet_id", referencedColumnName = "pet_id",insertable = false, updatable = false)
    private Pets pet;

    @ManyToOne
    @JoinColumn(name = "food_id", referencedColumnName = "food_id",insertable = false, updatable = false)
    private PetFood food;

	public PetFoodRelationshipId getId() {
		return id;
	}

	public Pets getPet() {
		return pet;
	}

	public PetFood getFood() {
		return food;
	}

    


	
	
    
}
