package com.petshop.in.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petshop.in.model.Employees;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Integer>{
	//Employees getAllEmployees();
	Employees findById(int employee_id);

	Employees findByFirstName(String firstName);

	Optional<Employees> findByLastName(String lastName);

	Employees findByFirstNameAndLastName(String firstName, String lastName);

	List<Employees> findByPosition(String position);

}
