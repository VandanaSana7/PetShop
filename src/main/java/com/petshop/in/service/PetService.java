package com.petshop.in.service;

import java.util.List;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.pets.*;
import com.petshop.in.model.Pets;
import com.petshop.in.model.Transactions;


public interface PetService {
	List<Pets> retreiveAllPets() throws NoPetsFoundException;
	
	Pets retreiveById(int id) throws PetIdNotFoundException;
	
	List<Pets> getPetsByCategory(String category) throws PetCategoryNotFoundException;
	
	List<Object> getPetsBygrooming(int pet_id) throws NoGroomingServiceException, PetIdNotFoundException;
	
	List<Object> getPetsByvaccination(int pet_id) throws NoVaccinationException, PetIdNotFoundException;
	
	List<Object[]> getPetsByPetFood(int pet_id) throws NoFoodInfoException, PetIdNotFoundException;
	
	List<Object[]> getSupplierByPetId(int pet_id) throws NoSupplierFoundException, PetIdNotFoundException;
	
	SuccessResponse addPets(Pets pet) throws PetCannotBeAddedException, MismatchDataTypeException, PetAlreadyExistsException;
	
	SuccessResponse updatePets(Pets pet,int id);
	
	Transactions getTransactionByPetId(int pet_id) throws NoTransactionFoundException, PetIdNotFoundException;
}
