package com.petshop.in.tests;
import static org.junit.jupiter.api.Assertions.*;

	import org.junit.jupiter.api.BeforeEach;

	import org.junit.jupiter.api.Test;

	import org.junit.jupiter.api.extension.ExtendWith;

	import org.mockito.InjectMocks;

	import org.mockito.Mock;

	import org.mockito.junit.jupiter.MockitoExtension;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.categorytransaction.CategoryIdNotFoundException;
import com.petshop.in.model.PetCategories;
import com.petshop.in.repository.PetCatogeriesRepository;
import com.petshop.in.serviceimpl.PetCategoriesServiceImpl;

import static org.mockito.Mockito.*;
 
	import java.util.ArrayList;

	import java.util.List;

	import java.util.Optional;


 
	@ExtendWith(MockitoExtension.class)

	public class PetCategoriesServiceTest {
 
	    @Mock

	    private PetCatogeriesRepository petCategoriesRepositoryMock;
 
	    @InjectMocks

	    private PetCategoriesServiceImpl petCategoriesService;
 
	    private PetCategories petCategory;
 
	    @BeforeEach

	    void setUp() {

	        petCategory = new PetCategories();

	        //petCategory.setId(1);

	        petCategory.setName("Test Category");

	    }
 
	    @Test

	    void testRetrieveAllCategories() {

	        List<PetCategories> categoriesList = new ArrayList<>();

	        categoriesList.add(petCategory);

	        when(petCategoriesRepositoryMock.findAll()).thenReturn(categoriesList);

	        List<PetCategories> result = petCategoriesService.retreiveAllCategories();

	        assertFalse(result.isEmpty());

	        assertEquals(1, result.size());

	        assertEquals("Test Category", result.get(0).getName());

	    }
 
	    @Test

	    void testRetrievePetCategoriesId_ExistingId() {

	        when(petCategoriesRepositoryMock.findById(1)).thenReturn(Optional.of(petCategory));

	        PetCategories result = petCategoriesService.retreivePetCategoriesId(1);

	        assertNotNull(result);

	        assertEquals("Test Category", result.getName());

	    }
 
	    @Test

	    void testRetrievePetCategoriesId_NonExistingId() {

	        when(petCategoriesRepositoryMock.findById(2)).thenReturn(Optional.empty());

	        assertThrows(CategoryIdNotFoundException.class, () -> {

	            petCategoriesService.retreivePetCategoriesId(2);

	        });

	    }
 
	    // Similarly, write tests for other methods like retrievePetCategories, addCategory, and updateCategoryName
	    @Test
	    public void testRetrievePetCategories_NotFound() {
	        when(petCategoriesRepositoryMock.findByName("Category1")).thenReturn(new ArrayList<>());
 
	        petCategoriesService.retreivePetCategories("Category1");
	    }
 
	    @Test
	    public void testAddCategory() throws MismatchDataTypeException {
	        PetCategories category = new PetCategories("Category1");
	        when(petCategoriesRepositoryMock.save(category)).thenReturn(category);
 
	        PetCategories result = petCategoriesService.addCategory(category);
 
	        assertNotNull(result);
	        assertEquals("Category1", result.getName());
	    }
 
	
 
	    @Test
	    public void testUpdateCategoryName() throws MismatchDataTypeException {
	        PetCategories category = new PetCategories(1, "Category1");
	        when(petCategoriesRepositoryMock.findById(1)).thenReturn(Optional.of(category));
	        when(petCategoriesRepositoryMock.save(category)).thenReturn(category);
 
	        SuccessResponse result = petCategoriesService.updateCategoryName(1, category);
 
	        assertNotNull(result);
	       // assertEquals("Data Updated: PetCategories [categoryId=1, name=category]", result.getStatus());
	    }
 
	    // Similarly, write tests for other methods like retrievePetCategories, addCategory, and updateCategoryNa
 
}
