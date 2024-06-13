package com.petshop.in.serviceimpl;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.Validationclass;
import com.petshop.in.exceptions.pets.*;
import com.petshop.in.model.PetCategories;
import com.petshop.in.model.PetGroomingRelationship;
import com.petshop.in.model.PetVaccinationRelationship;
import com.petshop.in.model.Pets;
import com.petshop.in.model.Transactions;
import com.petshop.in.repository.*;
import com.petshop.in.service.PetService;


@Service
public class PetServiceImpl implements PetService{
	
	@Autowired
	private PetsRepository pet_repo;
	@Autowired
	private PetGroomingRelationshipRepository pet_grooming_repo;
	@Autowired
	private PetVaccinationRelationshipRepository pet_vaccination_repo;
	@Autowired
	private PetFoodRelationshipRepository pet_food_repo;
	@Autowired
	private PetSupplierRelationshipRepository pet_supplier_repo;
	@Autowired
	private TransactionRepository transaction_repo;
	@Autowired
	private PetCatogeriesRepository petcategories_repo;

	
	public PetServiceImpl() {
		
	}

	
	
	public PetServiceImpl(PetsRepository pet_repo, PetGroomingRelationshipRepository pet_grooming_repo,
			PetVaccinationRelationshipRepository pet_vaccination_repo, PetFoodRelationshipRepository pet_food_repo,
			PetSupplierRelationshipRepository pet_supplier_repo, TransactionRepository transaction_repo) {
		this.pet_repo = pet_repo;
		this.pet_grooming_repo = pet_grooming_repo;
		this.pet_vaccination_repo = pet_vaccination_repo;
		this.pet_food_repo = pet_food_repo;
		this.pet_supplier_repo = pet_supplier_repo;
		this.transaction_repo = transaction_repo;
	}

	
	@Override
	public List<Pets> retreiveAllPets() throws NoPetsFoundException{
		List<Pets> p = pet_repo.findAll();
		if(p.isEmpty()) {
			throw new NoPetsFoundException("Validation failed: No pets are available in the pet shop"); 
		}
		return p;
	}
	
	@Override
	public Pets retreiveById(int id) throws PetIdNotFoundException{
		try {
			Pets p =pet_repo.findById(id).get();
			return p;
		}
		catch(NoSuchElementException e) {
			throw new PetIdNotFoundException("Pet id not found");
		}
		
	}

	@Override
	public List<Pets> getPetsByCategory(String category) throws PetCategoryNotFoundException {
		List<Pets> l = pet_repo.findByCategoryName(category);
		if(l.isEmpty()) {
			throw new PetCategoryNotFoundException("Validation failed: No pets in the given category");
		}
		
		return pet_repo.findByCategoryName(category);
	}

	@Override
	public List<Object> getPetsBygrooming(int pet_id) throws NoGroomingServiceException, PetIdNotFoundException {
		try {
		List<Object> result = new LinkedList<>();
		List<PetGroomingRelationship> l_pgr = pet_grooming_repo.findAll();
		Pets pet = pet_repo.findById(pet_id).get();
		ListIterator<PetGroomingRelationship> i = l_pgr.listIterator();
		while(i.hasNext()) {
			PetGroomingRelationship p = i.next();
			if(pet.getPetId()==p.getPet().getPetId()) {
				result.add(p.getPet());
				result.add(p.getGroomingservices());
			}
		}
		if(result.isEmpty()) {
			throw new NoGroomingServiceException("Validation failed: No grooming services available for this pet");
		}
		return result;
		}
		catch(NoSuchElementException e) {
			throw new PetIdNotFoundException("Validation failed: Pet id not found");
		}
		
		//return pet_grooming_repo.findGroomingServicesByPetId(pet_id);
	}
	
	@Override
	public List<Object> getPetsByvaccination(int pet_id) throws NoVaccinationException, PetIdNotFoundException{
		try {
			List<Object> result = new LinkedList<>();
			List<PetVaccinationRelationship> l_pvr = pet_vaccination_repo.findAll();
			Pets pet = pet_repo.findById(pet_id).get();
			ListIterator<PetVaccinationRelationship> i = l_pvr.listIterator();
			while(i.hasNext()) {
				PetVaccinationRelationship p = i.next();
				if(pet.getPetId()==p.getPet().getPetId()) {
					result.add(p.getPet());
					result.add(p.getVaccination());
				}
			}
			if(result.isEmpty()) {
				throw new NoVaccinationException("Validation failed: no vaccination for the given pet");
			}
			return result;
		}
		catch(NoSuchElementException e) {
			throw new PetIdNotFoundException("Validation failed: Pet id not found");
		}
		
		//return pet_vaccination_repo.findVaccinationsByPetId(pet_id);
	}
	
	@Override
	public List<Object[]> getPetsByPetFood(int pet_id) throws NoFoodInfoException, PetIdNotFoundException{
		List<Object[]> l = pet_food_repo.findFoodDetailsByPetId(pet_id);
		try {
		if(l.isEmpty()) {
			throw new NoFoodInfoException("Validation failed:no pet food info found for the given pet");
		}
		}
		catch(NoSuchElementException e) {
			throw new PetIdNotFoundException("Validation failed: Pet id not found");
		}
		
		
		return l;
	}
	
	@Override
	
	public List<Object[]> getSupplierByPetId(int pet_id) throws NoSupplierFoundException, PetIdNotFoundException{
		List<Object[]> l = pet_supplier_repo.findSupplierByPetId(pet_id);
		try {
		if(l.isEmpty()) {
			throw new NoSupplierFoundException("Validation failed:no pet suppliers found for the given pet");
		}
		}
		catch(NoSuchElementException e) {
			throw new PetIdNotFoundException("Validation failed: Pet id not found");
		}
		
		
		
		return l;
		
	}
	@Override

	public SuccessResponse addPets(Pets pet) throws PetCannotBeAddedException, MismatchDataTypeException, PetAlreadyExistsException{
		try {
			
			if(Validationclass.ValidationInt(pet.getName())==false) {
				throw new MismatchDataTypeException("Name should be string");
			}
			if(Validationclass.ValidationInt(pet.getBreed())==false) {
				throw new MismatchDataTypeException("breed should be string");
			}
			if(Validationclass.ValidationInt(pet.getDescription())==false) {
				throw new MismatchDataTypeException("Description should be text");
			}
			if(Validationclass.ValidationInt(pet.getImageUrl())==false) {
				throw new MismatchDataTypeException("URL should be string");
			}
			
//			if(!(Integer.valueOf(pet.getAge()) instanceof Integer)) {
//				throw new MismatchDataTypeException("Age should be int");
//			}
			List<Pets> petnames = pet_repo.findByCategoryName(pet.getCategory().getName());
			if(petnames.isEmpty()) {
				petcategories_repo.save(pet.getCategory());
				//throw new PetAlreadyExistsException("Pet Already exists");
			}
			
			Pets p = pet_repo.save(pet);
			SuccessResponse s = new SuccessResponse();
			s.setMessage("Data added"+p);
			s.setStatus("Success");
			s.setTimestamp(LocalDate.now());
			return s;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			throw new PetCannotBeAddedException("Validation failed:Pet cannot be added"+e.getMessage());
		}
        
    }

//	public SuccessResponse addPets(Pets pet) throws PetCannotBeAddedException, MismatchDataTypeException, PetAlreadyExistsException{
//		try {
//			
//			if(Validationclass.ValidationInt(pet.getName())==false) {
//				throw new MismatchDataTypeException("Name should be string");
//			}
//			if(Validationclass.ValidationInt(pet.getBreed())==false) {
//				throw new MismatchDataTypeException("breed should be string");
//			}
//			if(Validationclass.ValidationInt(pet.getDescription())==false) {
//				throw new MismatchDataTypeException("Description should be text");
//			}
//			if(Validationclass.ValidationInt(pet.getImageUrl())==false) {
//				throw new MismatchDataTypeException("URL should be string");
//			}
//			
////			if(!(Integer.valueOf(pet.getAge()) instanceof Integer)) {
////				throw new MismatchDataTypeException("Age should be int");
////			}
//			List<Pets> petnames = pet_repo.findByCategoryName(pet.getCategory().getName());
//			System.out.println(petnames);
//			if(petnames.isEmpty()) {
//				petcategories_repo.save(pet.getCategory());
//				//throw new PetAlreadyExistsException("Pet Already exists");
//			}
//			
//			Pets p = pet_repo.save(pet);
//			SuccessResponse s = new SuccessResponse();
//			s.setMessage("Data added"+p);
//			s.setStatus("Success");
//			s.setTimestamp(LocalDate.now());
//			return s;
//		}
//		catch(Exception e) {
//			System.out.println(e.getMessage());
//			throw new PetCannotBeAddedException("Validation failed:Pet cannot be added"+e.getMessage());
//		}
//        
//    }

	@Override
	public SuccessResponse updatePets(Pets pet,int id) {
		Pets p = pet_repo.findById(id).get();
		p.setAge(pet.getAge());
		p.getCategory().setName(pet.getCategory().getName());
		p.setBreed(pet.getBreed());
		p.setDescription(pet.getDescription());
		p.setImageUrl(pet.getImageUrl());
		p.setName(pet.getName());
		p.setPrice(pet.getPrice());
		
		pet_repo.save(p);
		SuccessResponse s = new SuccessResponse();
		s.setMessage("Data updated\n"+p);
		s.setStatus("Success");
		s.setTimestamp(LocalDate.now());
		return s;
	}
	@Override
	public Transactions getTransactionByPetId(int pet_id) throws NoTransactionFoundException, PetIdNotFoundException{
		try {
			List<Transactions> l = transaction_repo.findAll();
			Transactions t = null;
			Iterator<Transactions> i = l.listIterator();
			while(i.hasNext()) {
				Transactions t1 = (Transactions) i.next();
				if(t1.getPet().getPetId()==pet_id) {
					t=t1;
				}
			}
			if(t==null) {
				throw new NoTransactionFoundException("Validation failed: No transaction found for the given pet");
			}
			return t;
		}
		catch(NoSuchElementException e) {
			throw new PetIdNotFoundException("Validation failed: Pet id not found");
		}
		
		
	}



//	@Override
//	public SuccessResponse addPets(Pets pet) throws PetCannotBeAddedException, MismatchDataTypeException, PetAlreadyExistsException 
//	{
//		// TODO Auto-generated method stub
//		return null;
//	}
	
//	public SuccessResponse addPets(Pets pet) throws PetCannotBeAddedException, MismatchDataTypeException, PetAlreadyExistsException {
//        try {
//            // Check if the category exists in the petcategories table
//            List<PetCategories> petCategories = petcategories_repo.findByName(pet.getCategory().getName());
//            if (petCategories.isEmpty()) {
//                // If category does not exist, i am saving in the petcategories table
//                petcategories_repo.save(pet.getCategory());
//            } else {
//                // If category already exists, getting the existing data and setting it with the pet
//                pet.setCategory(petCategories.get(0));
//            }
//
//            Pets savedPet = pet_repo.save(pet);
//
//            SuccessResponse successResponse = new SuccessResponse();
//            successResponse.setMessage("Data added: " + savedPet);
//            successResponse.setStatus("Success");
//            successResponse.setTimestamp(LocalDate.now());
//            return successResponse;
//        } catch (Exception e) {
//            
//            throw new PetCannotBeAddedException("Validation failed: Pet cannot be added. Reason: " + e.getMessage());
//        }
    }

	


	
	
	


