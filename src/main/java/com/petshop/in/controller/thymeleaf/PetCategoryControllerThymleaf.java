package com.petshop.in.controller.thymeleaf;


import com.petshop.in.exceptions.*;
import com.petshop.in.exceptions.categorytransaction.CategoryAddException;
import com.petshop.in.exceptions.categorytransaction.CategoryIdNotFoundException;
import com.petshop.in.model.PetCategories;
import com.petshop.in.service.PetCategoriesService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
 
import java.util.List;
 
@Controller

public class PetCategoryControllerThymleaf {
 
    @Autowired

    PetCategoriesService petCategoriesService;
 
    

    @GetMapping("/petcategories")

    public String getAllCategories(Model model) {

        List<PetCategories> categories = petCategoriesService.retreiveAllCategories();

        model.addAttribute("categories", categories);

        return "PetCategorylist"; // Assuming the name of the Thymeleaf template is categories-list.html

    }


    @GetMapping("/petcategories/Form")
  	public String showCategoryIdForm() {

  		return "PetCategoryByIdForm";

  	}
 
    @GetMapping("/petcategories/categoryId")
    public String getCategoryById(@RequestParam("categoryId") Integer categoryId, Model model) {

        PetCategories category = petCategoriesService.retreivePetCategoriesId(categoryId);

        model.addAttribute("CategoryById", category);

        return "PetCategoryById"; // Assuming the name of the Thymeleaf template is category-details.html

    }

 
    @GetMapping("/categories/addForm")
    public String showAddCategoryForm() {
        return "PetCategoryAddForm";
    }

    @PostMapping("/categories/add")
    public String addCategory(@ModelAttribute("category") PetCategories category, Model model) throws MismatchDataTypeException ,CategoryAddException{
        
            PetCategories sucres = petCategoriesService.addCategory(category);
            model.addAttribute("AddCategory", sucres);
            return "PetCategoryAdd";
        
        }
    //.............************................
    
    @GetMapping("/petcategories/updateidForm")
    public String showPostPetCategoryupdateForm() {
        return "PetCategoryUpdateIdform";
    }
    
    @GetMapping("/petcategories/updateForm")
    public String showUpdateCategoryForm(@RequestParam("categoryId") Integer categoryId, Model model) throws MismatchDataTypeException, CategoryIdNotFoundException {
        PetCategories category = petCategoriesService.retreivePetCategoriesId(categoryId);
        model.addAttribute("CategoryId", category);
        return "PetCategoryUpdateForm";
    }

    @GetMapping("/petcategories/update")
    public String updateCategoryName(@RequestParam("categoryId") Integer categoryId,
                                     @ModelAttribute("updateCategory") PetCategories category, Model model) throws MismatchDataTypeException, CategoryIdNotFoundException {
        SuccessResponse response = petCategoriesService.updateCategoryName(categoryId, category);
        model.addAttribute("UpdateCategory", response);
        return "PetCategoryUpdate";
    }

 

}

