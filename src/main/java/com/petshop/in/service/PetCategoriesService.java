package com.petshop.in.service;

import java.util.List;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.model.PetCategories;

public interface PetCategoriesService {
	
	public List<PetCategories> retreiveAllCategories();
	public PetCategories retreivePetCategoriesId(int categoryId);
	public PetCategories addCategory(PetCategories petCate) throws MismatchDataTypeException;
	List<PetCategories> retreivePetCategories(String name);
	SuccessResponse updateCategoryName(int categoryId, PetCategories category) throws MismatchDataTypeException;

}
