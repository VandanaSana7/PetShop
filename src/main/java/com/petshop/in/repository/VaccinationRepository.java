package com.petshop.in.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.petshop.in.model.Employees;
import com.petshop.in.model.Vaccinations;

@Repository
public interface VaccinationRepository extends JpaRepository<Vaccinations, Integer>{
	@Query("select v from Vaccinations v where v.available=1")
	List<Vaccinations> findByAvailable();
	@Query("select v from Vaccinations v where v.available=0")
	List<Vaccinations> findByUnavailable();


}
