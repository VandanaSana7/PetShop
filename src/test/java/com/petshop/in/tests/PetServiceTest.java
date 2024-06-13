package com.petshop.in.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

	import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

	import static org.junit.jupiter.api.Assertions.assertThrows;

	import static org.mockito.Mockito.when;
 
	import java.util.ArrayList;

	import java.util.List;

	import java.util.NoSuchElementException;

	import java.util.Optional;
 
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;

	import org.junit.jupiter.api.Test;

	import org.mockito.InjectMocks;

	import org.mockito.Mock;

	import org.mockito.MockitoAnnotations;

	import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.data.repository.CrudRepository;

import com.petshop.in.exceptions.pets.NoPetsFoundException;
import com.petshop.in.exceptions.pets.PetCategoryNotFoundException;
import com.petshop.in.exceptions.pets.PetIdNotFoundException;
import com.petshop.in.model.Pets;
import com.petshop.in.repository.PetCatogeriesRepository;
import com.petshop.in.repository.PetsRepository;
import com.petshop.in.serviceimpl.PetServiceImpl;


 
	

	public class PetServiceTest {
 
	    private static final CrudRepository<Pets, Integer> petRepository = null;
 
		@Mock

	    private PetsRepository petsRepository;
 
	    @Mock

	    private PetCatogeriesRepository petCategoriesRepository;
 
	    @InjectMocks

	    private PetServiceImpl petService;
 
	    @BeforeEach

	    public void setUp() {

	        MockitoAnnotations.initMocks(this);

	    }
 
	    @Test

	    public void testRetrieveAllPets() {

	        List<Pets> petsList = new ArrayList<>();

	        petsList.add(new Pets());

	        when(petsRepository.findAll()).thenReturn(petsList);
 
	        assertDoesNotThrow(() -> petService.retreiveAllPets());

	    }
 
	    @Test

	    public void testRetrieveAllPets_NoPetsFoundException() {

	        when(petsRepository.findAll()).thenReturn(new ArrayList<>());
 
	        assertThrows(NoPetsFoundException.class, () -> petService.retreiveAllPets());

	    }
 
	    @Test

	    public void testRetrieveById() {

	        Pets pet = new Pets();

	        //pet.setId(1);

	        Optional<Pets> optionalPet = Optional.of(pet);

	        when(petsRepository.findById(1)).thenReturn(optionalPet);
 
	        assertDoesNotThrow(() -> petService.retreiveById(1));

	    }
 
	    @Test

	    public void testRetrieveById_PetIdNotFoundException() {

	        when(petsRepository.findById(1)).thenThrow(NoSuchElementException.class);
 
	        assertThrows(PetIdNotFoundException.class, () -> petService.retreiveById(1));

	    }
 
	    @Test

	    public void testGetPetsByCategory() {

	        List<Pets> petsList = new ArrayList<>();

	        petsList.add(new Pets());

	        when(petsRepository.findByCategoryName("Category")).thenReturn(petsList);
 
	        assertDoesNotThrow(() -> petService.getPetsByCategory("Category"));

	    }
 
	    @Test

	    public void testGetPetsByCategory_PetCategoryNotFoundException() {

	        when(petsRepository.findByCategoryName("Category")).thenReturn(new ArrayList<>());
 
	        assertThrows(PetCategoryNotFoundException.class, () -> petService.getPetsByCategory("Category"));

	    }
 
	    // Similarly, you can add tests for other methods...



	}
