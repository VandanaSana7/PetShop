package com.petshop.in.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petshop.in.dto.EmployeePetRelationId;
import com.petshop.in.model.EmployeePetRelationship;

@Repository
public interface EmployeePetRelationshipRepository extends JpaRepository<EmployeePetRelationship, EmployeePetRelationId>{

}
