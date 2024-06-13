package com.petshop.in.serviceimpl;

import java.time.LocalDate;
//import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
//import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.Validationclass;
import com.petshop.in.exceptions.foodcategoryaddress.NoPetFoodFoundException;
import com.petshop.in.exceptions.foodcategoryaddress.PetFoodBrandNotFoundException;
import com.petshop.in.exceptions.foodcategoryaddress.PetFoodIdNotFoundException;
import com.petshop.in.exceptions.foodcategoryaddress.PetFoodNameNotFoundException;
import com.petshop.in.exceptions.foodcategoryaddress.PetFoodNotFoundException;
import com.petshop.in.exceptions.foodcategoryaddress.PetFoodQuantityNotFoundException;
import com.petshop.in.model.Employees;
import com.petshop.in.model.PetFood;
import com.petshop.in.model.Pets;
import com.petshop.in.repository.PetFoodRepository;
import com.petshop.in.service.PetFoodService;

@Service
public class PetFoodServicesImpl implements PetFoodService {

	@Autowired
	PetFoodRepository petfood_repo;
	
	
	@Override
	public List<PetFood> retreiveAllPetFood() throws NoPetFoodFoundException{
		List<PetFood> petfood=petfood_repo.findAll();
		if(petfood.isEmpty())
		{
			throw new NoPetFoodFoundException("Validation Failed");
		}
		return petfood;
	}
	
	@Override
	public PetFood getFoodById(int foodId) throws PetFoodIdNotFoundException
	{
		try
		{
			PetFood petfood=petfood_repo.findById(foodId).get();
			return petfood;
		}
		catch(NoSuchElementException ex)
		{
			throw new PetFoodIdNotFoundException("Validation Failed");
		}
	}
	

	
	@Override
	public List<PetFood> getPetFoodByType(String type)throws PetFoodNameNotFoundException{
		// TODO Auto-generated method stub
		List<PetFood> petfood=petfood_repo.findByType(type);
		if(petfood.isEmpty())
			throw new PetFoodNameNotFoundException("Validation Failed");
		return petfood;
	}

	@Override
	public List<PetFood> getPetFoodByName(String name) throws PetFoodNameNotFoundException{
		// TODO Auto-generated method stub
		List<PetFood> petfood=petfood_repo.findByName(name);
		if(petfood.isEmpty())
			throw new PetFoodNameNotFoundException("Validation Failed");
		return petfood;
	}

	@Override
	
	public List<PetFood> getPetFoodByBrand(String brand) throws PetFoodBrandNotFoundException {
		// TODO Auto-generated method stub
		List<PetFood> petfood=petfood_repo.findByBrand(brand);
		if(petfood.isEmpty())
			throw new PetFoodBrandNotFoundException("Validation Failed");
		return petfood;
	}

	
	@Override
	public SuccessResponse addFood(PetFood petfood) throws PetFoodNotFoundException   {
	    try
	    {
	    	PetFood petfoodobj=petfood_repo.save(petfood);
	    	SuccessResponse sucres=new SuccessResponse();
	    	sucres.setMessage("PetFood Added Successfully"+petfoodobj);
	    	sucres.setTimestamp(LocalDate.now());
	    	sucres.setStatus(HttpStatus.ACCEPTED.toString());
		     return sucres;
		}
	    catch(Exception e)
	    {
	    	throw new PetFoodNotFoundException("Validation Failed");
	    }
		
	}

	


	@Override

	public SuccessResponse updateFood(int food_id, PetFood updatedPetFoods)

	{
		try
		{
		// TODO Auto-generated method stub
		PetFood petfood=petfood_repo.findById(food_id).get();
		if(petfood!=null)
		{

		   if(!Validationclass.ValidationInt(petfood.getName()))
		   {
			   throw new MismatchDataTypeException("Name should be String");
		   }
		   if(!Validationclass.ValidationInt(petfood.getBrand()))
		   {
			   throw new MismatchDataTypeException("Brand should be String");
		   }
		   if(!Validationclass.ValidationInt(petfood.getType()))
		   {
			   throw new MismatchDataTypeException("Type should be String");
		   }
		   

		   petfood.setName(updatedPetFoods.getName());
		   petfood.setBrand(updatedPetFoods.getBrand());
		   petfood.setType(updatedPetFoods.getType());
		   petfood.setQuantity(updatedPetFoods.getQuantity());
		   petfood.setPrice(updatedPetFoods.getPrice());
		}
		
		petfood_repo.save(petfood);
		SuccessResponse sucres=new SuccessResponse();
		sucres.setMessage("PetFood updated Successfully"+petfood);
		sucres.setTimestamp(LocalDate.now());
		sucres.setStatus(HttpStatus.ACCEPTED.toString());
		return sucres;
		
		}
		catch(NoSuchElementException | MismatchDataTypeException ex)
		{
			throw new PetFoodIdNotFoundException("Validation Failed");
		}
	}
	
    /*public PetFood updateQuantity(int food_id, PetFood food)
    {
    	PetFood petfood=petfood_repo.findById(food_id).get();
    	petfood.setQuantity(food.getQuantity());
		 petfood_repo.save(petfood);
    	return petfood;
    }*/
	
	@Override
	public SuccessResponse updateQuantity(int food_id, PetFood food) throws PetFoodQuantityNotFoundException
    {
    	try
    	{
    	PetFood petfood=petfood_repo.findById(food_id).get();
    	petfood.setQuantity(food.getQuantity());
    	
		 petfood_repo.save(petfood);
		 SuccessResponse successres=new SuccessResponse();
			successres.setMessage("PetFoodQuantity updated Successfully"+petfood);
			successres.setTimestamp(LocalDate.now());
			successres.setStatus(HttpStatus.ACCEPTED.toString());
			return successres;
    	}
    	catch(NoSuchElementException ex)
    	{
    		throw new PetFoodQuantityNotFoundException("Validation Failed[Invalid input]");
    	}
    }
}
	

