package com.petshop.in.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.petshop.in.dto.PetVaccinationRelationId;
import com.petshop.in.model.PetVaccinationRelationship;

@Repository
public interface PetVaccinationRelationshipRepository extends 
	JpaRepository<PetVaccinationRelationship, PetVaccinationRelationId>{
	
	@Query("SELECT pvr.pet,pvr.vaccination FROM PetVaccinationRelationship pvr WHERE pvr.id.pet_id = :pet_id")
	List<Object[]> findVaccinationsByPetId(int pet_id);

}
