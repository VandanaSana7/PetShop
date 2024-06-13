package com.petshop.in.dto;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PetFoodRelationshipId implements Serializable {

    @Column(name = "pet_id")
    private int petId;

    @Column(name = "food_id")
    private int foodId;
    
    // Constructors, getters, setters, equals, hashCode methods...
}
