package com.petshop.in.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.model.Employees;
import com.petshop.in.model.PetFood;
import com.petshop.in.serviceimpl.PetFoodServicesImpl;

@RestController
@RequestMapping("/api/v1/pet_foods")
public class PetFoodController {

	@Autowired
	PetFoodServicesImpl petfood_ser;
	
	@GetMapping
	public List<PetFood> retreiveAl(){
		return petfood_ser.retreiveAllPetFood();

	}
	
	
	@GetMapping("/{food_id}")
	public PetFood retreiveFoodByfoodId(@PathVariable("food_id") int foodId){
		return petfood_ser.getFoodById(foodId);

	}
	
	
	@GetMapping("/food_type/{type}")
	public List<PetFood> getEmpByType(@PathVariable("type") String type){
		return petfood_ser.getPetFoodByType(type);

	}
	
	
	
	@GetMapping("/brand/{brand_name}")
	public List<PetFood> getEmpByBrand(@PathVariable("brand_name") String brand){
		return petfood_ser.getPetFoodByBrand(brand);

	}
	
	@GetMapping("/name/{food_name}")
    public List<PetFood> getEmpByName(@PathVariable("food_name") String name) {
        return petfood_ser.getPetFoodByName(name);
    }
	
	@PostMapping("/add")
	public SuccessResponse addFood(@RequestBody PetFood petfood)
	{
		return petfood_ser.addFood(petfood);
		
	}
	
	@PutMapping("/update/{food_id}")
	public SuccessResponse updateFood(@PathVariable("food_id") Integer foodId,@RequestBody PetFood petfood) throws Throwable 

	{
		return petfood_ser.updateFood(foodId,petfood);
	}
	
	@PutMapping("/quantity/{food_id}")
	public SuccessResponse updateQuantity(@PathVariable("food_id") Integer food_id,@RequestBody PetFood petfood)
	{
		return petfood_ser.updateQuantity(food_id, petfood);
	}
}
