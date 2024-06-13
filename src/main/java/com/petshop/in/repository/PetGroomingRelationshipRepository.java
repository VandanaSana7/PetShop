package com.petshop.in.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.petshop.in.dto.PetGroomingRelationshipId;
import com.petshop.in.model.PetGroomingRelationship;
import com.petshop.in.model.Pets;

@Repository
public interface PetGroomingRelationshipRepository extends JpaRepository<PetGroomingRelationship, PetGroomingRelationshipId>{
//	@Query("SELECT pgr.pet,pgr.groomingservices FROM PetGroomingRelationship pgr WHERE pgr.id.pet_id = :pet_id")
//	List<Object[]> findGroomingServicesByPetId(int pet_id);

	

		
}
