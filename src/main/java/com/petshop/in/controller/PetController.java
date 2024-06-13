package com.petshop.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.pets.*;
import com.petshop.in.model.Pets;
import com.petshop.in.model.Transactions;
import com.petshop.in.serviceimpl.PetServiceImpl;



@RestController
@RequestMapping("api/v1/pets")
public class PetController {
	@Autowired
	PetServiceImpl pet_service;
	
	@GetMapping
	public ResponseEntity<List<Pets>> retreiveAll() throws NoPetsFoundException{
		return new ResponseEntity<>(pet_service.retreiveAllPets(),HttpStatus.OK);
	}
	
	@GetMapping("/pet/{pet_id}")
	public ResponseEntity<Pets> retreiveByPetId(@PathVariable("pet_id") int pet_id) throws PetIdNotFoundException{
		return new ResponseEntity<>(pet_service.retreiveById(pet_id),HttpStatus.OK);
	}
	
	@GetMapping("/{category}")
	public ResponseEntity<List<Pets>> retreiveByCategory(@PathVariable("category") String category) throws PetCategoryNotFoundException {
		return new ResponseEntity<>(pet_service.getPetsByCategory(category),HttpStatus.OK);
	}
	

	@GetMapping("/grooming_services/{pet_id}")
	public ResponseEntity<Object> retreiveByGroomingServices(@PathVariable("pet_id") int pet_id) throws NoGroomingServiceException, PetIdNotFoundException {
		//return pet_service.getPetsBygrooming(pet_id);
		return new ResponseEntity<>(pet_service.getPetsBygrooming(pet_id),HttpStatus.OK);
	}
	
	@GetMapping("/vaccinations/{pet_id}")
	public ResponseEntity<List<Object>> retreiveByVaccination(@PathVariable("pet_id") int pet_id) throws NoVaccinationException, PetIdNotFoundException {
		return new ResponseEntity<>(pet_service.getPetsByvaccination(pet_id),HttpStatus.OK);
	}
	
	@GetMapping("/food_info/{pet_id}")
	public ResponseEntity<List<Object[]>> retreiveByPetFood(@PathVariable("pet_id") int pet_id) throws NoFoodInfoException, PetIdNotFoundException {
		return new ResponseEntity<>(pet_service.getPetsByPetFood(pet_id),HttpStatus.OK);
	}
	
	@GetMapping("/suppliers/{pet_id}")
	public ResponseEntity<List<Object[]>> retreiveByPetsupplier(@PathVariable("pet_id") int pet_id) throws NoSupplierFoundException, PetIdNotFoundException {
		return new ResponseEntity<>(pet_service.getSupplierByPetId(pet_id),HttpStatus.OK);
	}
	
	@GetMapping("/transaction_history/{pet_id}")
	public ResponseEntity<Transactions> retreiveByTransaction(@PathVariable("pet_id") int pet_id) throws NoTransactionFoundException, PetIdNotFoundException {
		return new ResponseEntity<>(pet_service.getTransactionByPetId(pet_id),HttpStatus.OK);
	}
	
	@PostMapping("/add")
	 public SuccessResponse addPet(@RequestBody Pets pet) throws PetCannotBeAddedException, MismatchDataTypeException, PetAlreadyExistsException
	 {	
		return pet_service.addPets(pet);
	 }
	
	@PutMapping("/update/{pet_id}")
	 public SuccessResponse updatePet(@RequestBody Pets pet,@PathVariable("pet_id") int pet_id)
	 {
		return pet_service.updatePets(pet,pet_id);
	 }
	
}
