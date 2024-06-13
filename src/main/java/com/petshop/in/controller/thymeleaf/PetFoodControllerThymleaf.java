package com.petshop.in.controller.thymeleaf;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.AddressAndSuppliers.AddressIdNotFoundException;
import com.petshop.in.exceptions.categorytransaction.CategoryAddException;
import com.petshop.in.exceptions.foodcategoryaddress.NoPetFoodFoundException;
import com.petshop.in.model.PetFood;
import com.petshop.in.service.PetFoodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class PetFoodControllerThymleaf {

    @Autowired
    private PetFoodService petFoodService;

    @GetMapping("/petfood/all")
    public String getAllPetFood(Model model) {
        List<PetFood> petFoods = petFoodService.retreiveAllPetFood();
        model.addAttribute("petFoods", petFoods);
        return "PetFoodList";
    }
    
    @GetMapping("/petfood/PetIdForm")
	public String showPetFoodIdForm() {
		return "PetFoodByIdForm";
	}

    @GetMapping("/petfood/{foodId}")
    public String getPetFoodById(@RequestParam("foodId") Integer foodId, Model model) throws NoPetFoodFoundException{
        PetFood petFood = petFoodService.getFoodById(foodId);
        model.addAttribute("pet_Food", petFood);
        return "PetFoodById";
    }
    
    //................********************........................//
    
    
    @GetMapping("/petfood/PetTypeForm")
    public String showPetFoodTypeForm() {
        return "PetFoodByTypeForm";
    }

    @GetMapping("/petfood/byType")
    public String getPetFoodByType(@RequestParam("pet_Type") String type, Model model) throws MismatchDataTypeException {
        List<PetFood> petFoods = petFoodService.getPetFoodByType(type);
        model.addAttribute("pet_Type", petFoods);
        return "PetFoodByType";
    }
    
    //..............**********......................

    
    @GetMapping("/petfood/PetNameForm")
    public String showPetFoodNameForm() {
        return "PetFoodByNameForm";
    }

    @GetMapping("/petfood/byName")
    public String getPetFoodByName(@RequestParam("pet_Type") String type, Model model) throws MismatchDataTypeException {
        List<PetFood> petFoods = petFoodService.getPetFoodByName(type);
        model.addAttribute("pet_Type", petFoods);
        return "PetFoodByName";
    }
    
    
    //....................**********...................
    
    @GetMapping("/petfood/PetBrandForm")
    public String showPetFoodBrandForm() {
        return "PetFoodByBrandForm";
    }

    @GetMapping("/petfood/byBrand")
    public String getPetFoodByBrand(@RequestParam("pet_Type") String type, Model model) throws MismatchDataTypeException {
        List<PetFood> petFoods = petFoodService.getPetFoodByBrand(type);
        model.addAttribute("pet_Type", petFoods);
        return "PetFoodByBrand";
    }
    //...............**************................

    @GetMapping("/petfood/addForm")
    public String showAddPetFoodForm() {
        return "PetFoodAddForm";
    }

    @PostMapping("/petfood/add")
    public String addFood(@ModelAttribute("category") PetFood category, Model model) throws MismatchDataTypeException{
        
            SuccessResponse sucres = petFoodService.addFood(category);
            model.addAttribute("added_Category", sucres);
            return "PetFoodAdd";
        
       }
  //...............**************................
    @GetMapping("/petfood/updateForm")
    public String showPostPetFoodupdateForm() {
        return "PetFoodUpdateIdform";
    }

    @GetMapping("/petfood/update")
    public String updateFood(@RequestParam("foodId") Integer foodId, Model model) throws MismatchDataTypeException{
        PetFood food = petFoodService.getFoodById(foodId);
        model.addAttribute("FoodId", food);
        return "PetFoodUpdateForm";
    }

    @GetMapping("/petfood/updatedetails")
    public String updateFoodForm(@RequestParam("foodId") Integer foodId, Model model, @ModelAttribute("updateFood") PetFood updatedPetFood) throws MismatchDataTypeException {
        SuccessResponse sucres = petFoodService.updateFood(foodId, updatedPetFood);
        model.addAttribute("updateFood", sucres);
        return "PetFoodUpdate";
    }


    
    

}
