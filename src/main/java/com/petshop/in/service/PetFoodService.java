package com.petshop.in.service;

import java.util.List;

import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.model.PetFood;

public interface PetFoodService {

	public List<PetFood> retreiveAllPetFood();
	public PetFood getFoodById(int foodId);
	public List<PetFood> getPetFoodByType(String type);
	public List<PetFood> getPetFoodByName(String name);
	public List<PetFood> getPetFoodByBrand(String brand);
	public SuccessResponse addFood(PetFood petfood);
	public SuccessResponse updateFood(int food_id, PetFood updatedPetFoods);
	public SuccessResponse updateQuantity(int food_id, PetFood food);
}
