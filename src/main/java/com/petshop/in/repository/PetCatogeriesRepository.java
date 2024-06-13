package com.petshop.in.repository;


import java.util.Optional;



import java.util.List;

import java.util.List; 

import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.petshop.in.model.PetCategories;

import jakarta.transaction.Transactional;

@Repository
public interface PetCatogeriesRepository extends JpaRepository<PetCategories, Integer>{
	
	 
    @Transactional
    @Modifying
    @Query("UPDATE PetCategories p SET p.name = :categoryName WHERE p.id = :categoryId")
    void updateCategoryNameById(int categoryId, String categoryName);




	//Optional<PetCategories> findByName(String name);
    List<PetCategories> findByName(String name);





}