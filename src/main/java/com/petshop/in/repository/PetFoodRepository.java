package com.petshop.in.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petshop.in.model.PetFood;

@Repository
public interface PetFoodRepository extends JpaRepository<PetFood, Integer>{

	List<PetFood> findByType(String type);

	List<PetFood> findByName(String name);

	List<PetFood> findByBrand(String brand);
}
