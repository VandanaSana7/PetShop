package com.petshop.in.serviceimpl;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.Validationclass;
import com.petshop.in.exceptions.categorytransaction.CategoryAddException;
import com.petshop.in.exceptions.categorytransaction.CategoryIdNotFoundException;
import com.petshop.in.exceptions.categorytransaction.CategoryNotFoundException;
import com.petshop.in.exceptions.foodcategoryaddress.CategoryNameNotFoundException;
import com.petshop.in.model.PetCategories;
import com.petshop.in.repository.PetCatogeriesRepository;
import com.petshop.in.service.PetCategoriesService;

@Service
public class PetCategoriesServiceImpl implements PetCategoriesService {
	
			
		@Autowired
		PetCatogeriesRepository PetCatogeries_repo;
			
		// GET all Categories
			@Override
			public List<PetCategories> retreiveAllCategories(){
				List<PetCategories> pc = PetCatogeries_repo.findAll();
				if(pc.isEmpty())
					
					throw new CategoryNotFoundException("Validation failed");
			return pc;
		    	}
			//return PetCatogeries_repo.findAll();
			
		
			//Get By Cutsomer Id
		@Override
		public PetCategories retreivePetCategoriesId(int categoryId) {
				
				try {
					PetCategories pc = PetCatogeries_repo.findById(categoryId).get();
					return pc;
				}
				catch(NoSuchElementException e) {
			    		throw new CategoryIdNotFoundException("Category id not found");
			    	}
			}



		//Get By Category Name
		@Override
		public List<PetCategories> retreivePetCategories(String name) {
				try {
					List<PetCategories> pc = PetCatogeries_repo.findByName(name);
					return pc;
				}
				catch(NoSuchElementException e)
				{
					throw new CategoryNameNotFoundException("Category name not found");
				}
			}



			

			/*
			//post
			public PetCategories addCategory(PetCategories petCate) {
				
				PetCategories pc = PetCatogeries_repo.save(petCate);
				if(pc.())
				throw new CategoryNotFoundException("Validation failed");
				return pc;
			    	}

				
				//return PetCatogeries_repo.save(petCate);
			}*/
			

			
			//add
		@Override
		public PetCategories addCategory(PetCategories petCategory) throws MismatchDataTypeException {
			    try {
			        // Check if the category name is a valid string
			        if (!Validationclass.ValidationInt(petCategory.getName())) {
			            throw new MismatchDataTypeException("Category name should be a String");
			        }
			        
			        // Save the category
			        PetCategories addedCategory = PetCatogeries_repo.save(petCategory);
			        
			        if (addedCategory == null) {
			            throw new CategoryAddException("Failed to add category");
			        }
			        
			        return addedCategory;
			    } catch (Exception e) {
			        throw new CategoryAddException("Failed to add category");
			    }
			}
			
			
			
			


	
		       

			
//			//update 
//			public SuccessResponse updateCategoryName(int categoryId, String categoryName) throws MismatchDataTypeException {
//			    try {
//			        PetCategories existingCategory = PetCatogeries_repo.findById(categoryId).get();     //orElse(null);
//			        
//			        if (existingCategory != null) {
//			            if (!Validationclass.ValidationInt(existingCategory.getName())) {
//			                throw new MismatchDataTypeException("Category name should be String");
//			            }
//			            existingCategory.setName(categoryName);
//			            PetCatogeries_repo.save(existingCategory);
//			            
//			            SuccessResponse s = new SuccessResponse();
//			            s.setMessage("Data Updated: " + existingCategory);
//			            s.setStatus("Success");
//			            s.setTimestamp(LocalDate.now());
//			            return s;
//			        } else {
//			            throw new CategoryIdNotFoundException("Invalid Category Id");
//			        }
//			    } catch (NoSuchElementException ex) {
//			        throw new CategoryIdNotFoundException("Invalid Category Id");
//			    }
//			}
			
			//update
			@Override
			public SuccessResponse updateCategoryName(int categoryId, PetCategories category) throws MismatchDataTypeException {
			    try {
			        PetCategories existingCategory = PetCatogeries_repo.findById(categoryId).orElse(null);
			        
			        if (existingCategory != null) {
			            if (!Validationclass.ValidationInt(category.getName())) {
			                throw new MismatchDataTypeException("Category name should be a non-empty string");
			            }
			            existingCategory.setName(category.getName());
			            PetCatogeries_repo.save(existingCategory);
			            
			            SuccessResponse s = new SuccessResponse();
			            s.setMessage("Data Updated: " + existingCategory);
			            s.setStatus("Success");
			            s.setTimestamp(LocalDate.now());
			            return s;
			        } else {
			            throw new CategoryIdNotFoundException("Invalid Category Id");
			        }
			    } catch (NoSuchElementException ex) {
			        throw new CategoryIdNotFoundException("Invalid Category Id");
			    }
			}


			

		}

 




			
			
			
